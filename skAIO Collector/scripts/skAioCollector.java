package scripts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import scripts.Data.Vars;
import scripts.Tasks.Antiban;
import scripts.Tasks.Bank;
import scripts.Tasks.Loot;
import scripts.Tasks.WalkToLocation;
import scripts.Tasks.WorldHop;
import scripts.framework.Activity;
import scripts.framework.TaskTracker;

@ScriptManifest(authors = {
		"Stewiekidz" }, category = "Tools", name = "skAIO Collector", description = "Please be sure that you are logged in already! This script loots whatever you want to loot and then banks it. It curently only uses places that can be accessed via Webwalking. Please visit the forums for more details!")
public class skAioCollector extends Script implements Painting {

	private Activity[] tasks = new Activity[] { new Antiban(), new Bank(), new WalkToLocation(), new Loot(),
			new WorldHop() };
	private TaskTracker taskTracker = new TaskTracker(tasks);

	private void init() {
		General.useAntiBanCompliance(true);
		Mouse.setSpeed(General.random(150, 220));
		Vars.startingTile = Player.getPosition();
		Vars.lootArea = new RSArea(Vars.startingTile, Vars.lootRadius);
	}

	private void loop() {
		while (!Vars.isRunning) {
			taskTracker.runIt();
		}
	}

	@Override
	public void run() {

		Gui gui = new Gui();
		gui.setVisible(true);
		while (gui.isVisible()) {
			General.sleep(150);
		}

		init();

		loop();

	}

	// START: Code generated using Enfilade's Easel
	private final Color color1 = new Color(0, 0, 0);
	private final Font font1 = new Font("Berlin Sans FB", 0, 16);
	private static final long startTime = System.currentTimeMillis();

	@Override
	public void onPaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		long timeRan = System.currentTimeMillis() - startTime;
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("skAIO Collector v1.00", 10, 367);
		g.drawString("Runtime: " + Timing.msToString(timeRan), 11, 393);
		g.drawString("Items Collected: " + Vars.itemsCollected, 8, 421);
	}
	// END: Code generated using Enfilade's Easel

}
