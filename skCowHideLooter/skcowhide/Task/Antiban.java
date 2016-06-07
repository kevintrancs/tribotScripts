package scripts.skcowhide.Task;

import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.Game;
import org.tribot.api2007.Options;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills.SKILLS;
import scripts.skcowhide.Util.Task;

public class Antiban extends Task {

	@Override
	public boolean activate() {
		return Player.getAnimation() == -1 || Player.isMoving();
	}

	@Override
	public void execute() {
		if(Player.isMoving() && Game.getRunEnergy() >= abc.INT_TRACKER.NEXT_RUN_AT.next())
		{
			Options.setRunOn(true);
			abc.INT_TRACKER.NEXT_RUN_AT.reset();
		}
		abc.performCombatCheck();
		abc.performEquipmentCheck();
		abc.performFriendsCheck();
		abc.performExamineObject();
		abc.performLeaveGame();
		abc.performMusicCheck();
		abc.performPickupMouse();
		abc.performQuestsCheck();
		abc.performRandomMouseMovement();
		abc.performRandomRightClick();
		abc.performRotateCamera();
		abc.performTimedActions(SKILLS.HITPOINTS);
		abc.performXPCheck(SKILLS.HITPOINTS);
		
	}
	
	public static ABCUtil abc = new ABCUtil();

}
