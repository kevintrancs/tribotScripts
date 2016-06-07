package scripts.skcowhide.Task;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.skcowhide.skCowhideLooter;
import scripts.skcowhide.Util.Task;

public class ToLocation extends Task {
	
	@Override
	public boolean activate() {
		return !Inventory.isFull() && !skCowhideLooter.field.containsPlayer();
	}

	@Override
	public void execute() {
		
		if(WebWalking.walkTo(skCowhideLooter.field.getArea().getRandomTile()))
		{
			Timing.waitCondition(new Condition(){

				@Override
				public boolean active() {
					General.sleep(200,500);
					return skCowhideLooter.field.containsPlayer();
				}}, General.random(4000, 6000));
		}
		
	}

}
