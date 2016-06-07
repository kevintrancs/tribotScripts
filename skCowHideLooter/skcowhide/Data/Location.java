package scripts.skcowhide.Data;

import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import scripts.skcowhide.skCowhideLooter;


public enum Location {
	
	//new RSTile(3025, 3311, 0), new RSTile(3041, 3298, 0)
	LUMBY("Lumbridge", new RSArea(new RSTile[] {new RSTile(3241,3297,0), new RSTile(3264,3297,0), new RSTile(3265,3255,0),new RSTile(3253,3255,0)})), 
	FALLY("Falador", new RSArea(skCowhideLooter.lField));
	
	private String name;
	private RSArea rsarea;
	

	Location(String name, RSArea rsarea) {
		this.name = name;
		this.rsarea = rsarea;
	}

	public RSArea getArea() {
		return this.rsarea;
	}

	public String getString() {
		return this.name;
	}
	
	public boolean containsPlayer()
	{
		return rsarea.contains(Player.getPosition());
	}
	
	
}
