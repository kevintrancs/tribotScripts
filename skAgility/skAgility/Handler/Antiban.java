package scripts.skAgility.Handler;

import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills.SKILLS;

import scripts.skAgility.Util.Task;

public class Antiban extends Task {

	@Override
	public boolean activate() {
		return Player.getAnimation() == -1 || Player.isMoving();
	}

	@Override
	public void execute() {

		abc.performCombatCheck();
		abc.performEquipmentCheck();
		abc.performExamineObject();
		abc.performFriendsCheck();
		abc.performLeaveGame();
		abc.performMusicCheck();
		abc.performPickupMouse();
		abc.performQuestsCheck();
		abc.performRandomMouseMovement();
		abc.performRandomRightClick();
		abc.performRotateCamera();
		abc.performTimedActions(SKILLS.AGILITY);
		abc.performXPCheck(SKILLS.AGILITY);
	}

	public static ABCUtil abc = new ABCUtil();
}
