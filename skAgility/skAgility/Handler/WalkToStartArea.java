package scripts.skAgility.Handler;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.skAgility.skAgility;
import scripts.skAgility.Data.Rooftops;
import scripts.skAgility.Util.Task;

public class WalkToStartArea extends Task {

	@Override
	public boolean activate() {
		return Player.getPosition().getPlane() == 0 && Inventory.getCount(Bank.food) > 0;
	}

	@Override
	public void execute() {
		
		if(skAgility.rooftop.equals(Rooftops.SEERS) && !area.contains(Player.getPosition())) // webwalking doesn't work for seers, blindwalks get there
		{
			Walking.blindWalkTo(skAgility.rooftop.getStartingTile());
		}

		if (!area.contains(Player.getPosition())) {
			if (WebWalking.walkTo(area.getRandomTile())) {
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return area.contains(Player.getPosition());
					}
				}, General.random(3000, 6000));
			}
		}

		if (area.contains(Player.getPosition())) {
			final RSObject[] CLIMBUP = Objects.findNearest(10, skAgility.rooftop.getClimbId());

			if (CLIMBUP.length > 0 && CLIMBUP != null) {
				if (CLIMBUP[0].isOnScreen() && CLIMBUP[0].isClickable()) {
					if (DynamicClicking.clickRSObject(CLIMBUP[0], skAgility.rooftop.getSel())) {
						Timing.waitCondition(new Condition() {

							@Override
							public boolean active() {
								return Player.getPosition().getPlane() != 0 && Player.isMoving();
							}
						}, General.random(1000, 1250));
						General.sleep(500, 750);
					}
				}

				else {

					if (!CLIMBUP[0].isClickable())
						Camera.turnToTile(new RSTile(CLIMBUP[0].getPosition().getX() + General.random(-4, 4),
								CLIMBUP[0].getPosition().getY() + General.random(-4, 4)));
					if (Player.getPosition().distanceTo(CLIMBUP[0]) >= Antiban.abc.INT_TRACKER.WALK_USING_SCREEN
							.next()) {
						DynamicClicking.clickRSTile(CLIMBUP[0], "Walk here");
						Antiban.abc.INT_TRACKER.WALK_USING_SCREEN.reset();
					} else {
						WebWalking.walkTo(CLIMBUP[0]);
					}
					Timing.waitCondition(new Condition() {

						@Override
						public boolean active() {
							return CLIMBUP[0].isOnScreen();
						}
					}, General.random(500, 1000));
				}
			}
		}

	}

	private RSArea area = new RSArea(skAgility.rooftop.getStartingTile(), 2);

}
