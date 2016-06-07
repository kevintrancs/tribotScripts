package scripts.skAgility.Rooftops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.skAgility.skAgility;
import scripts.skAgility.Data.Obstacles;
import scripts.skAgility.Data.Rooftops;
import scripts.skAgility.Util.GeneralUtils;
import scripts.skAgility.Util.Task;

public class DraynorRoof extends Task {
	
private List<Obstacles> draynor_obstacles = new ArrayList<>();
	
	private final Obstacles CROSS_TIGHTROPE_ONE = new Obstacles(10074, new RSArea(new RSTile(3097, 3281, 3), new RSTile(3102, 3277, 3)), "Cross Tightrope",new RSTile(3099, 3277, 3));
	
	private final Obstacles CROSS_TIGHTROPE_TWO = new Obstacles(10075, new RSArea(new RSTile(3090, 3276, 3), new RSTile(3091, 3273, 3)), "Cross Tightrope",new RSTile(null));
	
	private final Obstacles BALANCE_NARROW_WALL = new Obstacles(10077, new RSArea(new RSTile(3094, 3266, 3), new RSTile(3089, 3265, 3)),"Balance Narrow Wall", new RSTile(null));
	
	private final Obstacles JUMP_UP_WALL = new Obstacles(10084, new RSArea(new RSTile(3088, 3261, 3), new RSTile(3088, 3257, 3)), "Jump-up Wall",new RSTile(3088, 3257, 3));
	
	private final Obstacles JUMP_GAP = new Obstacles(10085, new RSArea(new RSTile(3088, 3255, 3), new RSTile(3094, 3255, 3)), "Jump Gap",new RSTile(3094, 3255, 3));
	
	private final Obstacles CLIMB_DOWN_CRATE = new Obstacles(10086, new RSArea(new RSTile(3096, 3256, 3), new RSTile(3101, 3261, 3)), "Climb-down Crate",new RSTile(3101, 3261, 3));

	@Override
	public boolean activate() {
		return Player.getPosition().getPlane() != 0 && skAgility.rooftop.getName().equals(Rooftops.DRAYNOR.getName());
	}

	@Override
	public void execute() {

		Collections.addAll(draynor_obstacles, CROSS_TIGHTROPE_ONE, CROSS_TIGHTROPE_TWO, BALANCE_NARROW_WALL , JUMP_UP_WALL, JUMP_GAP, CLIMB_DOWN_CRATE);
		
		GeneralUtils.doObstacles(draynor_obstacles);
	}

}
