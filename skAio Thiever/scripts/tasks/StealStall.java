package scripts.tasks;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.data.State;
import scripts.data.Variables;
import scripts.framework.Task;

public class StealStall extends Task {

	@Override
	public boolean validate() { // In stall area and
		return !Inventory.isFull();
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.STEALING_FROM_STALL;
		if (!isInLocation())
			walkToLocation();
		// check for sweet spot
		if (!inSweetSpot())
			getInSweetSpot();
		// Now in prime position to steal
		steal();
		General.sleep(Variables.getVars().stall.getRespawn(), Variables.getVars().stall.getRespawn() + 500);
	}

	private boolean isInLocation() {
		return Variables.getVars().stall.getArea().contains(Player.getPosition());
	}

	private boolean inSweetSpot() {
		return Player.getPosition().equals(Variables.getVars().stall.getTile());
	}

	private void walkToLocation() {
		if (WebWalking.walkTo(Variables.getVars().stall.getTile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					General.sleep(200);
					return isInLocation();
				}
			}, 5000);
		}
	}

	private void getInSweetSpot() {
		if (Player.getPosition().distanceTo(
				Variables.getVars().stall.getTile()) <= Variables.getVars().antiban.INT_TRACKER.WALK_USING_SCREEN
						.next()) {
			DynamicClicking.clickRSTile(Variables.getVars().stall.getTile(), "Walk here");
			Variables.getVars().antiban.INT_TRACKER.WALK_USING_SCREEN.reset();
		} else
			walkToLocation();
		Timing.waitCondition(new Condition() {

			@Override
			public boolean active() {
				return !Player.isMoving();
			}
		}, 3000);
	}

	private void steal() {
		final int OLDXP = Skills.getXP(SKILLS.THIEVING);
		final RSObject[] sStall = Objects.findNearest(2, Variables.getVars().stall.getName());

		if (sStall.length > 0) {
			RSObject stallToSteal = sStall[0];
			if (DynamicClicking.clickRSObject(stallToSteal, "Steal-from " + Variables.getVars().stall.getName())) {
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return stallToSteal == null || Skills.getXP(SKILLS.THIEVING) > OLDXP;
					}
				}, 2000);
			}
		}
	}

}
