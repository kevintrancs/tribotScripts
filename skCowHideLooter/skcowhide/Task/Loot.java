package scripts.skcowhide.Task;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSTile;

import scripts.skcowhide.skCowhideLooter;
import scripts.skcowhide.Util.Task;

public class Loot extends Task {

	@Override
	public boolean activate() {
		return !Inventory.isFull() && skCowhideLooter.field.containsPlayer();
	}

	@Override
	public void execute() {
		
		final int COUNTHIDES = Inventory.getCount("Cowhide"); // #current hide
		
		final RSGroundItem[] NEARESTHIDE = GroundItems.findNearest("Cowhide");
		if(NEARESTHIDE.length > 0)
		{
			final RSGroundItem hideToLoot = NEARESTHIDE[0];
			
			if(NEARESTHIDE[0].isOnScreen())
			{
				if(DynamicClicking.clickRSGroundItem(NEARESTHIDE[0], "Take Cowhide"))
				{
					Timing.waitCondition(new Condition(){

						@Override
						public boolean active() {
							General.sleep(200,500);
							return Inventory.getCount("Cowhide") > COUNTHIDES || NEARESTHIDE[0].equals(null);
						}}, General.random(1500, 3000));
					skCowhideLooter.hideCount++;
				}
			}
			
			else
			{
				RSArea ranSpot = new RSArea(hideToLoot.getPosition(), 1);
				
				if(!NEARESTHIDE[0].isOnScreen() && Player.getPosition().distanceTo(NEARESTHIDE[0]) <= 6) // turn to it. if in close enough range
				{
				 	Camera.turnToTile(new RSTile(NEARESTHIDE[0].getPosition().getX() + General.random(-2, 2), NEARESTHIDE[0].getPosition().getY() + General.random(-2, 2)));
				}
				
				if(!NEARESTHIDE[0].isOnScreen()) // if turn doesnt work, walk to it
				{
					if (Player.getPosition().distanceTo(hideToLoot) >= Antiban.abc.INT_TRACKER.WALK_USING_SCREEN.next()) 
					{
						DynamicClicking.clickRSTile(ranSpot.getRandomTile(), 0);
						Antiban.abc.INT_TRACKER.WALK_USING_SCREEN.reset();
					} 
				
					else 
					{
						Walking.walkTo(ranSpot.getRandomTile());
					}
				}
				
				Timing.waitCondition(new Condition(){

					@Override
					public boolean active() {
						return NEARESTHIDE[0].isOnScreen() || NEARESTHIDE[0].equals(null);
					}}, General.random(1500, 2000));
			}
		}

		
	}

}
