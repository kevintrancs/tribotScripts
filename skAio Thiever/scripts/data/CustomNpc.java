package scripts.data;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class CustomNpc {

	private static CustomNpc custNpc;

	// singleton
	public static CustomNpc getNpcDetails() {
		return custNpc = custNpc == null ? new CustomNpc() : custNpc;
	}

	public String npcName;
	public RSTile startingTile;
	public int radius;
	public RSArea pickArea;

}
