package scripts.Data;

import java.util.ArrayList;
import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Vars {

	// variables that are constant and will be used all over to be used/modified

	public static ArrayList<String> itemsToLoot;
	public static final ABCUtil Antiban = new ABCUtil();
	public static boolean isRunning = false;
	public static int lootRadius = 0;
	public static RSTile startingTile;
	public static RSArea lootArea;
	public static String currStatus = "Setting up";
	public static boolean dmm = false;
	public static boolean hopWorlds = false;
	public static boolean mems = false;
	public static int itemsCollected = 0;
	public static int maxPlayers = 0;
}
