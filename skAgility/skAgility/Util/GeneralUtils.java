package scripts.skAgility.Util;

import java.util.List;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSObject;

import scripts.skAgility.skAgility;
import scripts.skAgility.Data.Obstacles;
import scripts.skAgility.Handler.Antiban;

public class GeneralUtils {

	public static void doObstacles(final List<Obstacles> obstacles) {
		for (Obstacles o : obstacles) {
			if (o.getArea().contains(Player.getPosition())) {

				checkAndDoMarks(); // check for marks of grace at start

				final RSObject[] OBSTACLE_CLICK = Objects.findNearest(30, o.getId());
				if (OBSTACLE_CLICK != null) {

					if (Player.getPosition().distanceTo(OBSTACLE_CLICK[0]) >= 8) {
						WebWalking.walkTo(o.getTile());
						Timing.waitCondition(new Condition() {

							@Override
							public boolean active() {
								return Player.isMoving();
							}
						}, General.random(500, 1000));
					}
				}

				if (OBSTACLE_CLICK.length > 0) {
					if (OBSTACLE_CLICK[0].isOnScreen()) {
						if (DynamicClicking.clickRSObject(OBSTACLE_CLICK[0], o.getInteract())) {
							Timing.waitCondition(new Condition() {

								@Override
								public boolean active() {
									return Player.getAnimation() != -1 && Player.isMoving();
								}
							}, General.random(1500, 2000));
							General.sleep(500, 1000); // sudo afk
						}
					}

					else {
						if (!OBSTACLE_CLICK[0].isOnScreen()) {
							if (Player.getPosition().distanceTo(
									OBSTACLE_CLICK[0]) >= Antiban.abc.INT_TRACKER.WALK_USING_SCREEN.next()) {
								DynamicClicking.clickRSTile(OBSTACLE_CLICK[0], "Walk here");
								Antiban.abc.INT_TRACKER.WALK_USING_SCREEN.reset();
							} else {
								WebWalking.walkTo(OBSTACLE_CLICK[0]);
							}
						}

						Timing.waitCondition(new Condition() {

							@Override
							public boolean active() {
								return Player.getAnimation() == -1;
							}
						}, General.random(1500, 2000));

					}
				}

			}
		}
	}

	private static void checkAndDoMarks() {

		RSGroundItem[] marks = GroundItems.findNearest("Mark of Grace");
		if (marks.length > 0 && Player.getPosition().distanceTo(marks[0]) <= 5) {
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
						General.sleep(500, 750);
						skAgility.mog++;
					}
				}

			}
		}

	}

}
