package scripts.skAgility.Handler;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;

import scripts.skAgility.Util.Task;

public class Bank extends Task {

	@Override
	public boolean activate() {
		return Inventory.getCount(food) == 0 && Player.getPosition().getPlane() == 0;
	}

	@Override
	public void execute() {

		if (!Banking.isInBank()) {
			if (WebWalking.walkToBank()) {
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return Banking.isInBank();
					}
				}, General.random(3000, 6000));
			}
		}

		if (Banking.isInBank()) {
			if (!Banking.isBankScreenOpen()) {
				Banking.openBank();
			}

			if (Banking.isBankScreenOpen()) {
				Banking.withdraw(20, food);
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return Inventory.getCount(food) >= 10;
					}
				}, General.random(1000, 1500));
			}
		}

	}

	public static String food;

}
