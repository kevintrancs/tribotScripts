package scripts.Tasks;

import org.tribot.api2007.Game;
import org.tribot.api2007.Options;
import org.tribot.api2007.Player;
import scripts.Data.Vars;
import scripts.framework.Activity;

public class Antiban extends Activity {

	@Override
	public boolean validate() {
		return Player.isMoving() || Player.getAnimation() == -1;
	}

	@Override
	public void execute() {
		if (Player.isMoving() && Game.getRunEnergy() >= Vars.Antiban.INT_TRACKER.next()) {
			Options.setRunOn(true);
			Vars.Antiban.INT_TRACKER.reset();
		}
		Vars.Antiban.performCombatCheck();
		Vars.Antiban.performEquipmentCheck();
		Vars.Antiban.performExamineObject();
		Vars.Antiban.performFriendsCheck();
		Vars.Antiban.performLeaveGame();
		Vars.Antiban.performMusicCheck();
		Vars.Antiban.performPickupMouse();
		Vars.Antiban.performQuestsCheck();
		Vars.Antiban.performRandomMouseMovement();
		Vars.Antiban.performRandomRightClick();
		Vars.Antiban.performRotateCamera();
	}

	@Override
	public String status() {
		return "Performing random Antiban action";
	}

}
