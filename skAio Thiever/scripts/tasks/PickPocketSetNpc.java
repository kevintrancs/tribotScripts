package scripts.tasks;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;

import scripts.data.State;
import scripts.data.Variables;
import scripts.framework.Task;

public class PickPocketSetNpc extends Task {

	@Override
	public boolean validate() {
		return !Inventory.isFull() && Inventory.getCount(Variables.getVars().food) > 0;
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.PICKPOCKETING_NPC;
		if (!isInArea())
			walkToArea();
		// In area now
		pickPocket();
	}

	private boolean isInArea() {
		return Variables.getVars().npc.getArea().contains(Player.getPosition());
	}

	private void pickPocket() {

		final int OLDXP = Skills.getXP(SKILLS.THIEVING);
		final RSNPC[] VICTIM = NPCs.findNearest(Variables.getVars().npc.getName());

		if (VICTIM.length > 0) {
			RSNPC victimToClick = VICTIM[0];
			if (victimToClick.isOnScreen()) {
				if (DynamicClicking.clickRSNPC(victimToClick, "Pickpocket")) {
					Timing.waitCondition(new Condition() {

						@Override
						public boolean active() {
							return Skills.getXP(SKILLS.THIEVING) > OLDXP || !victimToClick.isOnScreen();
						}
					}, 2000);
				}
			} else {
				if (!victimToClick.isOnScreen())
					Camera.turnToTile(new RSTile(victimToClick.getPosition().getX() + General.random(-4, 4),
							victimToClick.getPosition().getY() + General.random(-4, 4)));
			}
		}
	}

	private void walkToArea() {
		if (WebWalking.walkTo(Variables.getVars().npc.getArea().getRandomTile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return isInArea();
				}
			}, General.random(3000, 6000));
		}
	}

}
