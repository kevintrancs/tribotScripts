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
import org.tribot.api2007.Player;
import org.tribot.api2007.Login.STATE;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.data.CustomNpc;
import scripts.data.Mode;
import scripts.data.Variables;
import scripts.framework.Looper;

@ScriptManifest(authors = {
		"stewiekidz" }, category = "Thieving", name = "skThiever AIO", version = 3.0, description = "Thanks for using my AIO Theiver. Check forums for details about current features and bug reports!")
public class skThiever extends Script implements Painting {

	private int start_xp;
	private int start_lvl;
	Looper loop = new Looper();

	@Override
	public void run() {
		// wait for login first
		while (Login.getLoginState() != STATE.INGAME) {
			General.sleep(500);
		}
		// Wait for GUI fill-out
		ThieverGui gui = new ThieverGui();
		gui.setVisible(true);
		while (gui.isVisible()) {
			General.sleep(250);
		}

		init();
		while (true) { // loop
			loop.loop();
		}
	}

	private void init() {
		General.useAntiBanCompliance(true);
		Mouse.setSpeed(General.random(150, 220));
		start_xp = Skills.getXP(SKILLS.THIEVING);
		start_lvl = Skills.getCurrentLevel(SKILLS.THIEVING);
	}

	private static final long startTime = System.currentTimeMillis();
	private final Color color1 = new Color(102, 102, 102, 170);
	private final Color color2 = new Color(0, 0, 0);
	private final Color color3 = new Color(255, 255, 255);
	private final Color color4 = new Color(255, 51, 255);

	private final BasicStroke stroke1 = new BasicStroke(1);

	private final Font font1 = new Font("Century Gothic", 0, 10);
	private final Font font2 = new Font("Century Gothic", 1, 14);
	private final Font font3 = new Font("Century Gothic", 1, 10);

	@Override
	public void onPaint(Graphics g1) {
		long timeRan = System.currentTimeMillis() - startTime;
		Graphics2D g = (Graphics2D) g1;
		g.setColor(color1);
		g.fillRect(414, 196, 101, 138);
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(414, 196, 101, 138);
		g.setFont(font1);
		g.setColor(color3);
		g.drawString("Time Ran: " + Timing.msToString(timeRan), 416, 210);
		g.drawString("XP Gain: " + (Skills.getXP(SKILLS.THIEVING) - start_xp), 416, 235);
		g.drawString("Lvls: " + Skills.getCurrentLevel(SKILLS.THIEVING) + " ("
				+ (Skills.getCurrentLevel(SKILLS.THIEVING) - start_lvl) + ") ", 416, 261);
		g.drawString("Current State: ", 416, 287);
		g.drawString("" + Variables.getVars().state, 416, 309);
		g.setFont(font2);
		g.setColor(color4);
		g.drawString("sk", 419, 333);
		g.setFont(font3);
		g.setColor(color3);
		g.drawString("Thiever AIO v3.0", 436, 333);

	}

}
