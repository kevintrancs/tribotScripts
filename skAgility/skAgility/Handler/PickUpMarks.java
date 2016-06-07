package scripts.skAgility.Handler;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSGroundItem;

import scripts.skAgility.skAgility;
import scripts.skAgility.Util.Task;

public class PickUpMarks extends Task {

	@Override
	public boolean activate() {

		RSGroundItem[] marks = GroundItems.findNearest("Mark of Grace");
		return marks.length > 0 && Player.getPosition().distanceTo(marks[0]) <= 5;

	}

	@Override
	public void execute() {
		
		RSGroundItem[] marks = GroundItems.findNearest("Mark of Grace");
		final int mInv = Inventory.getCount("Mark of grace");

		if (marks.length > 0) {
			if (marks[0].isOnScreen()) {
				if (DynamicClicking.clickRSGroundItem(marks[0], "Take mark of grace")) {
					Timing.waitCondition(new Condition() {

						@Override
						public boolean active() {
							return Inventory.getCount("Mark of grace") > mInv;
						}
					}, General.random(500, 1000));
					General.sleep(500,750);
					skAgility.mog++;
				}
			}

		}

	}
}
