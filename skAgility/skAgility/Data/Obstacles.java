package scripts.skAgility.Data;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Obstacles {
	
	private int id;
	private RSArea area;
	private String interact;
	private RSTile obsTile;

	public Obstacles(int id, RSArea area, String interact, RSTile tile) {
		this.id = id;
		this.area = area;
		this.interact = interact;
		this.obsTile = tile;
	}

	public int getId() {
		return this.id;
	}

	public String getInteract() {
		return this.interact;
	}

	public RSArea getArea() {
		return this.area;
	}

	public RSTile getTile() {
		return this.obsTile;
	}

}
