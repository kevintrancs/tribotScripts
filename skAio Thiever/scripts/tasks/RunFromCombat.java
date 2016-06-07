package scripts.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;

import scripts.data.Npc;
import scripts.data.State;
import scripts.data.Variables;
import scripts.framework.Task;

public class RunFromCombat extends Task {

	@Override
	public boolean validate() {
		return Player.getRSPlayer().isInCombat();
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.RUNNING_AWAY_FROM_COMBAT;
		switch (Variables.getVars().mode) {
		case STALL:
			runAway();
			break;
		case NPC:
			if (Variables.getVars().npc.equals(Npc.MASTERFARMER))
				while (Player.getRSPlayer().isInCombat()) {
					Inventory.dropAllExcept(keep);
				}
			else
				General.sleep(Variables.getVars().npc.getStunTime());
			break;
		case CUSTOM_NPC:
			General.sleep(5000, 6000);
			break;
		default:
			break;
		}

	}

	private void runAway() {
		if (WebWalking.walkToBank()) {
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(200);
					return !Player.getRSPlayer().isInCombat() || Banking.isInBank();
				}
			}, General.random(1500, 2000));
		}
	}

	private String[] keep = { Variables.getVars().food, "Ranarr seed", "Watermelon seed", "Wildblood seed",
			"Limpwurt seed", "Toadflax seed", "Irit seed", "Avantoe seed", "Kwuarm seed", "Snapdragon seed",
			"Cadantine seed", "Lantadyme seed", "Dwarf weed seed", "Torstol seed", "Cactus seed" };

}
