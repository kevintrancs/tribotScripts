package scripts.Variables;

import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.types.RSTile;

import scripts.data.Location;
import scripts.data.LogType;
import scripts.data.State;

public class Variables {

	private static Variables vars;

	public static Variables getVars() {
		return vars = vars == null ? new Variables() : vars;
	}

	public ABCUtil antiban = new ABCUtil();
	public LogType logToBurn;
	public Location location;
	public int burnedLogs = 0;
	public RSTile bestLane;
	public boolean hasLane = false;
	public State state;

}
