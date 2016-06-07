package scripts.Tasks;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSGroundItem;
import scripts.Data.Vars;
import scripts.framework.Activity;

public class Loot extends Activity {

	@Override
	public boolean validate() {
		return !Inventory.isFull() && Vars.lootArea.contains(Player.getPosition());
	}

	@Override
	public void execute() {
		int amtInv = Inventory.getAll().length;

		for (String s : Vars.itemsToLoot) {
			final RSGroundItem[] ITEMTOLOOT = GroundItems.findNearest(s);

			if (ITEMTOLOOT.length > 0) {
				if (ITEMTOLOOT[0].isOnScreen()) {
					if (DynamicClicking.clickRSGroundItem(ITEMTOLOOT[0],
							"Take " + ITEMTOLOOT[0].getDefinition().getName())) {
						Timing.waitCondition(new Condition() {

							@Override
							public boolean active() {
								return Inventory.getAll().length > amtInv || Inventory.isFull()
										|| !ITEMTOLOOT[0].isOnScreen();
							}
						}, General.random(2000, 3000));
						Vars.itemsCollected++;
					}
				} else {
					if (Player.getPosition().distanceTo(ITEMTOLOOT[0]) >= Vars.Antiban.INT_TRACKER.WALK_USING_SCREEN
							.next()) {
						DynamicClicking.clickRSTile(ITEMTOLOOT[0].getPosition(), 0);
						Vars.Antiban.INT_TRACKER.WALK_USING_SCREEN.reset();
					} else {
						Walking.blindWalkTo(ITEMTOLOOT[0]);
					}

				}
			}
		}

	}

	@Override
	public String status() {
		return "Currently Looting!";
	}

}
