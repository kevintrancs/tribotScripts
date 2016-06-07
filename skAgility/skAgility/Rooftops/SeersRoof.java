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

public class SeersRoof extends Task {
	
	private List<Obstacles> seers_obstacles = new ArrayList<>();
	
	private final Obstacles JUMP_GAP = new Obstacles(11374, new RSArea(new RSTile(2729, 3490, 3), new RSTile(2721, 3496, 3)), "Jump Gap",new RSTile(2721, 3496, 3));

	private final Obstacles CROSS_TIGHTROPE = new Obstacles(11378, new RSArea(new RSTile(2713, 3494, 2), new RSTile(2708, 3488, 2)), "Cross Tightrope",new RSTile(2709, 3490, 2));
	
	private final Obstacles JUMP_GAP_TWO = new Obstacles(11375, new RSArea(new RSTile(2710, 3480, 2), new RSTile(2714, 3477, 2)), "Jump Gap",new RSTile(2711, 3477, 2));
	
	private final Obstacles JUMP_GAP_THREE = new Obstacles(11376, new RSArea(new RSTile(2714, 3472, 3), new RSTile(2700, 3470, 3)), "Jump Gap",new RSTile(2703, 3470, 3));
	
	private final Obstacles JUMP_EDGE = new Obstacles(11377, new RSArea(new RSTile(2698, 3465, 2), new RSTile(2702, 3461, 2)), "Jump Edge",new RSTile(null));
	@Override
	public boolean activate() {
		return Player.getPosition().getPlane() != 0 && skAgility.rooftop.getName().equals(Rooftops.SEERS.getName());
	}

	@Override
	public void execute() {
	
		Collections.addAll(seers_obstacles, JUMP_GAP, CROSS_TIGHTROPE, JUMP_GAP_TWO , JUMP_GAP_THREE, JUMP_EDGE);
	
		GeneralUtils.doObstacles(seers_obstacles);
		
	}

}
