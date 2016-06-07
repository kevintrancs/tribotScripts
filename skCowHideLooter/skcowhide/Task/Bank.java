package scripts.skcowhide.Task;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;

import scripts.skcowhide.Util.Task;

public class Bank extends Task {

	@Override
	public boolean activate() {
		return Inventory.isFull();
	}

	@Override
	public void execute() {
		
		if(!Banking.isInBank())
		{
			if(WebWalking.walkToBank())
			{
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(200,500);
						return Banking.isInBank();
					}}, General.random(6000, 9000));
			}
		}
		
		else // in da bank
		{
			if(!Banking.isBankScreenOpen())
			{
				if(Banking.openBank())
				{
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							General.sleep(200,500);
							return Banking.isBankScreenOpen();
						}}, General.random(6000, 9000));
				}
			}
			
			else // in da bank and bank screen be open
			{
				Banking.depositAll();
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						General.sleep(200,500);
						return !Inventory.isFull();
					}}, General.random(500, 1000));
			}
			
		}
		
	}

}
