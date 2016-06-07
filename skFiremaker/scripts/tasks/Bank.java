package scripts.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.Variables.Variables;
import scripts.data.State;
import scripts.framework.Task;

public class Bank extends Task {

	@Override
	public boolean validate() {
		return Inventory.getCount(Variables.getVars().logToBurn.getLogName()) == 0
				|| Inventory.getCount("Tinderbox") == 0; // no logs or tinderbox
															// we need to bank
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.BANKING;
		if (!Banking.isInBank()) {
			WebWalking.walkToBank();
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return Banking.isInBank();
				}
			}, 3000);
		}

		if (!Banking.isBankScreenOpen()) {
			Banking.openBank();
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return Banking.isBankScreenOpen();
				}
			}, 2000);
		}

		// bank screen is open by now

		if (!(Inventory.getCount("Tinderbox") > 0)) { // checks for tinderbox
			Banking.withdraw(1, "Tinderbox");
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return Inventory.getCount("Tinderbox") > 0;
				}
			}, 2000);
		}

		widthdrawLogs();

	}

	private void widthdrawLogs() {
		Banking.withdraw(0, Variables.getVars().logToBurn.getLogName());
		Timing.waitCondition(new Condition() {

			@Override
			public boolean active() {
				General.sleep(100);
				return Inventory.getCount(Variables.getVars().logToBurn.getLogName()) > 0;
			}
		}, 3000);
	}

}
