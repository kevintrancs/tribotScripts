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
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;

import scripts.data.CustomNpc;
import scripts.data.State;
import scripts.data.Variables;
import scripts.framework.Task;

public class PickPocketCustomNpc extends Task {
	// redun code, should combine into just one pickpocket class.
	// To-do: Combine in one pick class later
	@Override
	public boolean validate() {
		return !Inventory.isFull() || Inventory.getCount(Variables.getVars().food) > 0;
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.PICKPOCKETING_NPC;
		if (!isInArea())
			walkToArea();
		pickNpc();
	}

	private boolean isInArea() {
		return CustomNpc.getNpcDetails().pickArea.contains(Player.getPosition());
	}

	private void walkToArea() {
		if (WebWalking.walkTo(CustomNpc.getNpcDetails().pickArea.getRandomTile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return isInArea();
				}
			}, General.random(3000, 6000));
		}
	}

	private void pickNpc() {
		final int OLDXP = Skills.getXP(SKILLS.THIEVING);
		final RSNPC[] VICTIM = NPCs.findNearest(CustomNpc.getNpcDetails().npcName);

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

}
