package scripts.tasks;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.Variables.Variables;
import scripts.data.State;
import scripts.framework.Task;

public class LightLogs extends Task {

	@Override
	public boolean validate() {
		return Inventory.getCount(Variables.getVars().logToBurn.getLogName()) > 0
				&& Inventory.getCount("Tinderbox") > 0;
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.BURNING_WOOD;
		if (Banking.isInBank()) { // Still in the bank and we need to venture
									// out
			walkToLane();
		}

		// If we have fire under then we set our lane to false and find new lane
		while (containsFire()) {
			Variables.getVars().bestLane = getNewLane();
			General.sleep(100);
		}

		if (!containsFire()) { // we have lane now, and we
								// must walk
			walkToLane();
			General.sleep(100);
			Variables.getVars().hasLane = true;
		}

		while (Inventory.getCount(Variables.getVars().logToBurn.getLogName()) > 0 && Variables.getVars().hasLane) {
			lightLog();

		}

	}

	/**
	 * 
	 * Pre - PLayer in not moving and checks if fire is under player
	 * 
	 * 
	 * Post - returns boolean if there is a fire under player or not
	 * 
	 * @return
	 */

	private boolean containsFire() {
		return Objects.isAt(Player.getPosition(), "Fire") ? true : false;
	}

	/**
	 * Pre - Implies you are in the sweet spot, but need a new lane
	 * 
	 * 
	 * Post - Gets new rstile which will be your new lane
	 * 
	 * @return
	 */

	private RSTile getNewLane() {
		int y = Player.getPosition().getY();
		int x = Player.getPosition().getX();
		RSTile fireLane;
		return fireLane = (y < Variables.getVars().location.getTile().getY() - 3) ? new RSTile(x, y + 3, 0)
				: new RSTile(x, y - 1, 0);
		// If we are already -3 under the best tile, then we restart it back up
		// there, else we go down one
	}

	/**
	 * Pre - Has a clear lane and is not moving so we can get ready to make fire
	 * 
	 * Post - finished lighting and gained xp
	 */

	private void lightLog() {
		final int beforeXp = Skills.getXP(SKILLS.FIREMAKING);

		if (Player.getAnimation() != 733) {
			Inventory.find("Tinderbox")[0].click("use");
			Inventory.find(Variables.getVars().logToBurn.getLogName())[0].click("use");
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return (Skills.getXP(SKILLS.FIREMAKING) > beforeXp && !Player.isMoving());
				}
			}, 6000);
		}
		Variables.getVars().burnedLogs++;
	}

	private void walkToLane() {
		if (Player.getPosition().distanceTo(
				Variables.getVars().bestLane) <= Variables.getVars().antiban.INT_TRACKER.WALK_USING_SCREEN.next()) {
			DynamicClicking.clickRSTile(Variables.getVars().bestLane, "Walk here");
			Variables.getVars().antiban.INT_TRACKER.WALK_USING_SCREEN.reset();
		} else {
			WebWalking.walkTo(Variables.getVars().bestLane);
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return Player.getPosition().equals(Variables.getVars().bestLane) && !Player.isMoving();
				}
			}, 5000);
		}
	}

}
