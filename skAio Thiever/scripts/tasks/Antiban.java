package scripts.tasks;

import org.tribot.api2007.Player;
import org.tribot.api2007.Skills.SKILLS;

import scripts.data.Variables;
import scripts.framework.Task;

public class Antiban extends Task {

	@Override
	public boolean validate() {
		return Player.getAnimation() == -1; // when not doing anything we run
											// some antibans
	}

	@Override
	public void execute() {
		Variables.getVars().antiban.performCombatCheck();
		Variables.getVars().antiban.performEquipmentCheck();
		Variables.getVars().antiban.performExamineObject();
		Variables.getVars().antiban.performFriendsCheck();
		Variables.getVars().antiban.performLeaveGame();
		Variables.getVars().antiban.performMusicCheck();
		Variables.getVars().antiban.performPickupMouse();
		Variables.getVars().antiban.performRandomMouseMovement();
		Variables.getVars().antiban.performRandomRightClick();
		Variables.getVars().antiban.performRotateCamera();
		Variables.getVars().antiban.performTimedActions(SKILLS.THIEVING);
		Variables.getVars().antiban.performXPCheck(SKILLS.THIEVING);
	}

}
