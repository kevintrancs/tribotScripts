package scripts.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.data.State;
import scripts.data.Variables;
import scripts.framework.Task;

public class LootChest extends Task {

	@Override
	public boolean validate() {
		return !Inventory.isFull();
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.LOOTING_CHEST;
		if (onChestPlane())
			lootFromChest();
		else if (!onChestPlane() && atDoor())
			handleObjects();
		else
			walkToDoor();

	}

	private boolean onChestPlane() {
		return Player.getPosition().getPlane() == 1;
	}

	private boolean atDoor() {
		return Player.getPosition().equals(Variables.getVars().chest.getRstile());
	}

	private boolean atInsideDoor() {
		return Player.getPosition().equals(Variables.getVars().chest.getInsideDoor());
	}

	private void walkToDoor() {
		if (WebWalking.walkTo(Variables.getVars().chest.getRstile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return atDoor() && Player.getAnimation() == -1;
				}
			}, General.random(3000, 6000));
		}
	}

	private void lootFromChest() {
		final int OLDXP = Skills.getXP(SKILLS.THIEVING);
		final RSObject[] CHEST = Objects.find(8, Variables.getVars().chest.getId());

		if (CHEST.length > 0 && CHEST != null) {
			RSObject chestLooting = CHEST[0];

			if (chestLooting.isClickable()) {
				if (DynamicClicking.clickRSObject(chestLooting, "Search for traps Chest")) {
					Timing.waitCondition(new Condition() {

						@Override
						public boolean active() {
							return Skills.getXP(SKILLS.THIEVING) > OLDXP || chestLooting == null;
						}
					}, General.random(1000, 2000));

					General.sleep(8000, 10000); // respawn time
				}
			}

		}
	}

	private void handleObjects() {
		final RSObject[] DOOR = Objects.findNearest(3, Variables.getVars().chest.getDoorId());

		if (DOOR.length > 0) {
			RSObject doorToClick = DOOR[0];
			if (doorToClick.isClickable()) {
				Clicking.click("Pick-lock Door", doorToClick);
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return atInsideDoor();
					}
				}, General.random(1500, 2000));

				final RSObject[] STAIR = Objects.findNearest(7, Variables.getVars().chest.getStairUp());

				if (STAIR.length > 0) {
					RSObject stairToClick = STAIR[0];
					if (stairToClick.isClickable()) {
						Clicking.click("Climb-up Staircase", stairToClick);
						Timing.waitCondition(new Condition() {

							@Override
							public boolean active() {
								return onChestPlane();
							}
						}, General.random(1000, 2000));
					}
				}
			}
		}
	}

}
