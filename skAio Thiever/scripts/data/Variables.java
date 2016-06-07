package scripts.data;

import org.tribot.api.util.ABCUtil;

public class Variables {

	private static Variables vars;

	// Singleton
	public static Variables getVars() {
		return vars = vars == null ? new Variables() : vars;

	}

	public String food = "";
	public boolean drop;
	public Mode mode;
	public ABCUtil antiban = new ABCUtil();
	public int userEatPerc = -1;
	public Stalls stall;
	public Npc npc;
	public Chest chest;
	public State state;

}
