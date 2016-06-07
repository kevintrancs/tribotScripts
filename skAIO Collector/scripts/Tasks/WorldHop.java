package scripts.Tasks;

import org.tribot.api.General;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.api2007.Players;
import org.tribot.api2007.WorldHopper;
import org.tribot.api2007.ext.Filters;
import scripts.Data.Vars;
import scripts.framework.Activity;

public class WorldHop extends Activity { // using TRibot's world hopper api

	@Override
	public boolean validate() {
		return Vars.hopWorlds && Players.findNearest(Filters.Players.inArea(Vars.lootArea)).length >= 1;
	}

	@Override
	public void execute() {
		if (Login.getLoginState() == STATE.INGAME)
			Login.logout();
		General.sleep(1500, 3000); // rest 1.5 - 3 secs
		if (WorldHopper.changeWorld(WorldHopper.getRandomWorld(Vars.mems, Vars.dmm))) {
			General.println("Successful World Hop");
		}

	}

	@Override
	public String status() {
		return "Currently world hopping";
	}

}
