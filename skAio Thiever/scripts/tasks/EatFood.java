package scripts.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSItem;

import scripts.data.State;
import scripts.data.Variables;
import scripts.framework.Task;

public class EatFood extends Task {

	@Override
	public boolean validate() {
		return Player.getRSPlayer().getHealth() < getUserEatPerc();
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.HEALING;
		final int LOWHEALTH = Skills.getCurrentLevel(SKILLS.HITPOINTS);
		RSItem[] food = Inventory.find(Variables.getVars().food);

		if (food.length > 0) {
			RSItem eatThis = food[0];

			if (eatThis.click("Eat")) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return Skills.getCurrentLevel(SKILLS.HITPOINTS) > LOWHEALTH;
					}
				}, 2000);
			}
		}

	}

	private int getUserEatPerc() {
		if (Variables.getVars().userEatPerc < 0)
			Variables.getVars().userEatPerc = Variables.getVars().antiban.INT_TRACKER.next();
		return Variables.getVars().userEatPerc;
	}
}
