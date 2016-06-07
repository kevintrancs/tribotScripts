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

public class AlKharidRoof extends Task {
	
	private List<Obstacles> al_kharid_obstacles = new ArrayList<>();
	
	private final Obstacles CROSS_ROPE = new Obstacles(10284, new RSArea(new RSTile(3274,3180,3), new RSTile(3271,3192,3)), "Cross Tightrope", new RSTile(3273,3182,3));
	
	private final Obstacles SWING_ACCOSS_CABLE = new Obstacles(10355, new RSArea(new RSTile(3272,3172,3), new RSTile(3266,3162,3)), "Swing-across Cable", new RSTile(3269,3168,3));
	
	private final Obstacles ZIP_LINE = new Obstacles(10527, new RSArea(new RSTile(3283,3160,3), new RSTile(3302,3169,3)), "Teeth-grip Zip line", new RSTile(3302,3164,3));
	
	private final Obstacles SWING_TREE = new Obstacles(10357, new RSArea(new RSTile(3315,3161,1), new RSTile(3317,3165,1)), "Swing-across Tropical tree", new RSTile(3317,3165,1));
	
	private final Obstacles CLIMB_ROOF_BEAMS = new Obstacles(10094, new RSArea(new RSTile(3314,3173,2), new RSTile(3318,3179,2)), "Climb Roof top beams", new RSTile(3315,3178,2));
	
	private final Obstacles CROSS_ROPE_TWO = new Obstacles(10583, new RSArea(new RSTile(3318,3180,3), new RSTile(3312,3186,3)), "Cross Tightrope", new RSTile(3314,3185,3));
	
	private final Obstacles JUMP_GAP = new Obstacles(10284, new RSArea(new RSTile(3274,3180,3), new RSTile(3271,3192,3)), "Cross Tightrope", new RSTile(3273,3182,3));
	
	@Override
	public boolean activate() {
		return Player.getPosition().getPlane()!= 0&& skAgility.rooftop.getName().equals(Rooftops.AL_KHARID.getName());
	}

	@Override
	public void execute() {
		
		Collections.addAll(al_kharid_obstacles, CROSS_ROPE, SWING_ACCOSS_CABLE, ZIP_LINE , SWING_TREE, CLIMB_ROOF_BEAMS, CROSS_ROPE_TWO, JUMP_GAP);
		
		
		GeneralUtils.doObstacles(al_kharid_obstacles);
		
	}

}
