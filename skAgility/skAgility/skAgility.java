package scripts.skAgility;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import scripts.skAgility.Data.Rooftops;
import scripts.skAgility.Handler.Antiban;
import scripts.skAgility.Handler.Bank;
import scripts.skAgility.Handler.EatFood;
import scripts.skAgility.Handler.PickUpMarks;
import scripts.skAgility.Handler.WalkToStartArea;
import scripts.skAgility.Rooftops.AlKharidRoof;
import scripts.skAgility.Rooftops.ArdyRoof;
import scripts.skAgility.Rooftops.CanifisRoof;
import scripts.skAgility.Rooftops.DraynorRoof;
import scripts.skAgility.Rooftops.PolliRoof;
import scripts.skAgility.Rooftops.SeersRoof;
import scripts.skAgility.Rooftops.VarrockRoof;
import scripts.skAgility.Util.Task;

@ScriptManifest(authors = {
		"Stewiekidz" }, category = "Agility", name = "skAgility Rooftops", description = "Current Verison: 2.04, Rooftop agility, please report all bugs and errors to the forums thanks :)")
public class skAgility extends Script implements Painting {

	public static int mog;
	private int start_xp;
	private int start_lvl;
	public static Rooftops rooftop;
	private ArrayList<Task> tasks = new ArrayList<Task>();

	@Override
	public void run() {
		Gui gui = new Gui();
		gui.setVisible(true);
		while (gui.isVisible()) {
			General.sleep(150);
		}

		initialize();
		loop(10, 30);
	}

	private void loop(int min, int max) {
		while (true) {
			for (Task t : tasks) {
				if (t.activate()) {
					t.execute();
					General.sleep(min, max);
				}
			}
		}
	}

	private void initialize() {
		General.useAntiBanCompliance(true);
		Mouse.setSpeed(General.random(150, 220));
		Collections.addAll(tasks, new Antiban(), new Bank(), new EatFood(), new WalkToStartArea(),
				new AlKharidRoof(), new ArdyRoof(), new CanifisRoof(), new DraynorRoof(), new SeersRoof(),
				new VarrockRoof(), new PolliRoof()); // removed new PickUpMarks()
		start_xp = Skills.getXP(SKILLS.AGILITY);
		start_lvl = Skills.getActualLevel(SKILLS.AGILITY);
	}

	// Paint
	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	private final Color color1 = new Color(102, 102, 102, 170);
	private final Color color2 = new Color(0, 51, 204, 170);
	private final Color color3 = new Color(255, 0, 0, 170);
	private final Color color4 = new Color(255, 0, 51, 170);
	private final Color color5 = new Color(255, 255, 255);
	private final Color color6 = new Color(0, 204, 0);
	private final Color color7 = new Color(0, 0, 0);
	private final BasicStroke stroke1 = new BasicStroke(1);
	private final BasicStroke stroke2 = new BasicStroke(3);
	private final BasicStroke stroke3 = new BasicStroke(4);
	private final Font font1 = new Font("Leelawadee", 0, 24);
	private final Font font2 = new Font("Leelawadee", 0, 20);
	private final Font font3 = new Font("Leelawadee", 0, 14);
	private final Font font4 = new Font("Segoe UI Black", 0, 19);
	private final Image img1 = getImage(
			"http://services.runescape.com/m=rswikiimages/en/2012/1/agility_big-13005436.png");
	private static final long startTime = System.currentTimeMillis();

	@Override
	public void onPaint(Graphics g1) {
		long timeRan = System.currentTimeMillis() - startTime;
		int xpHr = (int) ((Skills.getXP(SKILLS.AGILITY) - start_xp) * 3600000 / timeRan);
		int percentTilNextLvl = Skills.getPercentToNextLevel(SKILLS.AGILITY);

		Graphics2D g = (Graphics2D) g1;
		g.setColor(color1);
		g.fillRect(8, 345, 503, 129);
		g.setStroke(stroke1);
		g.drawRect(8, 345, 503, 129);
		g.setColor(color2);
		g.fillRect(12, 353, 189, 40);
		g.setColor(color3);
		g.setStroke(stroke2);
		g.drawRect(12, 353, 189, 40);
		g.setColor(color2);
		g.fillRect(12, 406, 189, 40);
		g.setColor(color4);
		g.setStroke(stroke3);
		g.drawRect(12, 406, 189, 40);
		g.setColor(color2);
		g.fillRect(437, 442, 71, 28);
		g.setColor(color4);
		g.setStroke(stroke2);
		g.drawRect(437, 442, 71, 28);
		g.setFont(font1);
		g.setColor(color5);
		g.drawString("Time: " + Timing.msToString(timeRan), 17, 384);
		g.setFont(font2);
		g.drawString("Xp Gained: " + (Skills.getXP(SKILLS.AGILITY) - start_xp) + "( " + xpHr + " )", 14, 441);
		g.setColor(color6);
		int greenBarWidth = (int) (373.0 * (percentTilNextLvl / 100.0));
		g.fillRect(61, 452, greenBarWidth, 18);
		g.setFont(font3);
		g.setColor(color7);
		g.drawString(" " + percentTilNextLvl + "% TTNL", 214, 467);
		g.setColor(color5);
		g.drawString("MoG: " + mog, 441, 466);
		g.setFont(font4);
		g.setColor(color2);
		g.drawString("skAgility Rooftops v2.04", 288, 334);
		g.drawImage(img1, 329, 328, null);
	}

}
