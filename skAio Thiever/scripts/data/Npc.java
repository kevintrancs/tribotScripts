package scripts.data;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public enum Npc {
	
	MAN("Man", 3080, new RSArea(new RSTile(3217,3224,0),new RSTile(3226,3213,0)), 5000),
	MASTERFARMER("Master Farmer",3257, new RSArea(new RSTile(3076,3255,0),new RSTile(3086,3246,0)), 5000);
	
	private String name;
	private int id;
	private int stunTime;
	private RSArea area;
	
	Npc(String name, int id, RSArea area,int stunTime){
		this.name = name;
		this.id = id;
		this.stunTime = stunTime;
		this.area = area;
	}
	
	public String getName(){
		return this.name;
	}
	public int getId(){
		return this.id;
	}
	public RSArea getArea(){
		return this.area;
	}
	public int getStunTime(){
		return this.stunTime;
	}
}
