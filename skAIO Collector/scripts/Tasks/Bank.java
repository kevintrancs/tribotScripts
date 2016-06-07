package scripts.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.framework.Activity;

public class Bank extends Activity {

	@Override
	public boolean validate() {
		return Inventory.isFull();
	}

	@Override
	public void execute() {
		if (!Banking.isInBank())
			walkToBank();
		else // Player is already in bank
			bankLoot();
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Currently Banking";
	}

	private void walkToBank() {
		if (WebWalking.walkToBank())
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					// TODO Auto-generated method stub
					return Banking.isInBank();
				}
			}, General.random(1500, 3500));

	}

	private void bankLoot() {
		if (!Banking.isBankScreenOpen())
			Banking.openBank();
		else { // bank screen open
			Banking.depositAll();
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					// TODO Auto-generated method stub
					return !Inventory.isFull();
				}
			}, General.random(500, 1000));
		}
	}

}
