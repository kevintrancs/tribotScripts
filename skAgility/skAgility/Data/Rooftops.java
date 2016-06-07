package scripts.skAgility.Data;

import org.tribot.api2007.types.RSTile;

public enum Rooftops {
	
	DRAYNOR("Draynor Village Agility Course", 10, new RSTile(3104, 3278, 0), 10073, "Climb Rough wall"),

	AL_KHARID("Al Kharid Agility Course", 20, new RSTile(3273, 3196, 0), 10093, "Climb Rough wall"),

	VARROCK("Varrock Agility Course", 30, new RSTile(3222, 3414, 0), 10586, "Climb Rough wall"),

	CANIFIS("Canifis Agility Course", 40, new RSTile(3508, 3486, 0), 10819, "Climb Tall tree"),

	SEERS("Seers' Village Agility Course", 60, new RSTile(2729, 3488, 0), 11373, "Climb-up Wall"),

	POLLNIVNEACH("pollnivneach Agility Course", 70, new RSTile(3351, 2960, 0), 11380,"Climb-on Basket"),

	ARDOUGNE("Ardougne Agility Course", 90, new RSTile(2673, 3296, 0), 11405, "Climb-up Wooden Beams");

	private String name;
	private int lvlReq;
	private RSTile startingTile;
	private int climbId;
	private String opSelect;

	Rooftops(String s, int i, RSTile rst, int id, String sel) {
		this.name = s;
		this.lvlReq = i;
		this.startingTile = rst;
		this.climbId = id;
		this.opSelect = sel;
	}

	public String getName() {
		return name;
	}

	public int getLevelRequirement() {
		return lvlReq;
	}

	public RSTile getStartingTile() {
		return startingTile;
	}

	public int getClimbId() {
		return climbId;
	}

	public String getSel() {
		return opSelect;
	}

}
