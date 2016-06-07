package scripts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;

import scripts.Variables.Variables;
import scripts.framework.Looper;

@ScriptManifest(authors = {
		"Stewiekidz" }, category = "Firemaking", name = "skFiremaker", version = .01, description = "Thanks for using! Post bugs and reports in the forums thank you!")
public class skFiremaker extends Script implements Painting, MessageListening07 {

	private int start_xp;
	private int start_lvl;
	Looper loop = new Looper();

	@Override
	public void run() {
		while (Login.getLoginState() != STATE.INGAME) {
			General.sleep(500);
		}

		FireMakerGui gui = new FireMakerGui();
		gui.setVisible(true);
		while (gui.isVisible()) {
			General.sleep(250);
		}

		init();

		while (true) {
			loop.loop();
		}

	}

	private void init() {
		General.useAntiBanCompliance(true);
		Mouse.setSpeed(General.random(95, 105));
		start_xp = Skills.getXP(SKILLS.FIREMAKING);
		start_lvl = Skills.getCurrentLevel(SKILLS.FIREMAKING);
	}

	private final Color color1 = new Color(255, 102, 51, 170);
	private final Color color2 = new Color(0, 0, 0);
	private final Color color3 = new Color(255, 255, 255);
	private static final long startTime = System.currentTimeMillis();
	private final BasicStroke stroke1 = new BasicStroke(1);

	private final Font font1 = new Font("Century Gothic", 1, 14);
	private final Font font2 = new Font("Century Gothic", 0, 12);

	@Override
	public void onPaint(Graphics g1) {
		long timeRan = System.currentTimeMillis() - startTime;
		int perHour = (int) (Variables.getVars().burnedLogs * 3600000 / timeRan);
		Graphics2D g = (Graphics2D) g1;
		g.setColor(color1);
		g.fillRect(396, 163, 121, 176);
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(396, 163, 121, 176);
		g.setFont(font1);
		g.setColor(color3);
		g.drawString("skFiremaker v0.1", 401, 336);
		g.setFont(font2);
		g.drawString("Time ran: " + Timing.msToString(timeRan), 397, 185);
		g.drawString("XP/Lvl Gained: " + (Skills.getXP(SKILLS.FIREMAKING) - start_xp) + "/"
				+ (Skills.getCurrentLevel(SKILLS.FIREMAKING) - start_lvl), 397, 214);
		g.drawString("Logs Burned: " + Variables.getVars().burnedLogs + "(" + perHour + ")", 397, 243);
		g.drawString("Current State:", 397, 270);
		g.drawString("" + Variables.getVars().state, 397, 295);
	}

	@Override
	public void clanMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void duelRequestReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void personalMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serverMessageReceived(String mess) {
		if (mess.contains("can't"))
			Variables.getVars().hasLane = false;
	}

	@Override
	public void tradeRequestReceived(String arg0) {
		// TODO Auto-generated method stub

	}

}
