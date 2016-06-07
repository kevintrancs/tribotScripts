package scripts.skAgility.Handler;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSItem;
import scripts.skAgility.Util.Task;

public class EatFood extends Task {

	@Override
	public boolean activate() {
		return percentHealth() <= 40 && Inventory.getCount(Bank.food) >= 1;
	}

	@Override
	public void execute() {
		final int LOWHEALTH = Skills.getCurrentLevel(SKILLS.HITPOINTS);
		RSItem[] food = Inventory.find(Bank.food);

		if (food != null && food.length > 0) {
			if (food[0].click("Eat")) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return Skills.getCurrentLevel(SKILLS.HITPOINTS) > LOWHEALTH;
					}
				}, General.random(250, 750));
			}
		}

	}

	private int percentHealth() {
		return (int)((Skills.getCurrentLevel(SKILLS.HITPOINTS) / Skills.getActualLevel(SKILLS.HITPOINTS)) * 100);
	}


}
