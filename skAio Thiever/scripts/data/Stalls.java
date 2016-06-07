package scripts.data;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public enum Stalls {
	CAKE("Baker's stall", new RSArea(new RSTile(2666,3310,0), new RSTile(2668,3312,0)), new RSTile(2668,3312,0), 2000),
	TEA("Tea Stall", new RSArea(new RSTile(3268,3409,0), new RSTile(3271,3412,0)), new RSTile(3268,3410,0), 7000),
	SILK("Silk stall", new RSArea(new RSTile(2661,3314,0), new RSTile(2663,3316,0)), new RSTile(2663,3316,0), 5000),
	FUR("Fur stall", new RSArea(new RSTile(2662, 3297,0), new RSTile(2664,3295,0)), new RSTile(2664, 3295,0), 10000),
	GEM("Gem Stall", new RSArea(new RSTile(2666,3303,0), new RSTile(2668,3305,0)), new RSTile(2668,3305,0), 180000);
	
	private String name;
	private RSArea area;
	private RSTile tile;
	private int respawn;
	
	Stalls(String name, RSArea area, RSTile tile, int respawn){
		this.name = name;
		this.area = area;
		this.tile = tile;
		this.respawn = respawn;
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public RSArea getArea(){
		return this.area;
	}
	
	public RSTile getTile(){
		return this.tile;
	}
	public int getRespawn(){
		return this.respawn;
	}

}
