package scripts.tasks;

import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;

import scripts.data.State;
import scripts.data.Variables;
import scripts.framework.Task;

public class Bank extends Task {

	@Override
	public boolean validate() {
		switch (Variables.getVars().mode) {
		case STALL:
			return Inventory.isFull();
		case NPC:
			return Inventory.isFull() || Inventory.getCount(Variables.getVars().food) == 0;
		case CUSTOM_NPC:
			return Inventory.isFull() || Inventory.getCount(Variables.getVars().food) == 0;
		default:
			return false;
		}
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.BANKING;
		if (!Banking.isInBank())
			WebWalking.walkToBank();
		// In bank otherwise
		if (!Banking.isBankScreenOpen())
			Banking.openBank();
		// screen is open
		depositItems(); // empty my inventory
		if (!Variables.getVars().food.equals("")) // Not empty food string
			widthdrawItems();

	}

	private void depositItems() {
		Banking.depositAll();
		Timing.waitCondition(new Condition() {

			@Override
			public boolean active() {
				return !Inventory.isFull();
			}
		}, 3000);
	}

	private void widthdrawItems() {
		Banking.withdraw(4, Variables.getVars().food);
		Timing.waitCondition(new Condition() {

			@Override
			public boolean active() {
				return Inventory.getCount(Variables.getVars().food) == 4;
			}
		}, 3000);
	}

}
