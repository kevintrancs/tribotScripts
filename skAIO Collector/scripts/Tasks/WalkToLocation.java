package scripts.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import scripts.Data.Vars;
import scripts.framework.Activity;

public class WalkToLocation extends Activity {

	@Override
	public boolean validate() {
		return !Vars.lootArea.contains(Player.getPosition()) && !Inventory.isFull();
	}

	@Override
	public void execute() {
		if (WebWalking.walkTo(Vars.lootArea.getRandomTile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return Vars.lootArea.contains(Player.getPosition());
				}
			}, General.random(1500, 3500));
		}

	}

	@Override
	public String status() {
		return "Walking back to looting area";
	}

}
