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

public class PolliRoof extends Task {
	
private List<Obstacles> polli_obstacles = new ArrayList<>();
	
	private final Obstacles JUMP_ON_MARKET_STALL = new Obstacles(11381, new RSArea(new RSTile(3351,2964,1), new RSTile(3346,2968,1)),"Jump-on Market stall", new RSTile(3349,2968,1));

	private final Obstacles GRAB_BANNER = new Obstacles(11382, new RSArea(new RSTile(3352,2973,1), new RSTile(3355,2976,1)),"Grab Banner", new RSTile(3355,2976,1));
	
	private final Obstacles LEAP_GAP = new Obstacles(11383, new RSArea(new RSTile(3360,2977,1), new RSTile(3362,2979,1)),"Leap Gap", new RSTile(3362,2977,1) );
	
	private final Obstacles JUMP_TO_TREE = new Obstacles(11384,  new RSArea(new RSTile(3366,2976,1), new RSTile(3369,2974,1)),"Jump-to Tree", new RSTile(3366,2976,1));
	
	private final Obstacles CLIMB_ROUGH_WALL = new Obstacles(11385, new RSArea(new RSTile(3368,2982,1), new RSTile(3365,2982,1)),"Climb Rough wall", new RSTile(3365,2982,1));

	private final Obstacles CROSS_MONKEYBARS = new Obstacles(11386, new RSArea(new RSTile(3365,2983,2), new RSTile(3355,2984,2)),"Cross Monkeybars", new RSTile(3359,2984,2));

	private final Obstacles JUMP_ON_TREE_TWO = new Obstacles(11389, new RSArea(new RSTile(3358,2991,2), new RSTile(3360,2995,2)),"Jump-on Tree", new RSTile(3360,2995,2));

	private final Obstacles JUMP_TO_DRYINGLINE = new Obstacles(11390, new RSArea(new RSTile(3359,3000,2), new RSTile(3362,3002,2)),"Jump-to Drying line", new RSTile(3362,3002,2));

	@Override
	public boolean activate() {
		return Player.getPosition().getPlane() != 0 && skAgility.rooftop.getName().equals(Rooftops.POLLNIVNEACH.getName());
	}

	@Override
	public void execute() {
		Collections.addAll(polli_obstacles, JUMP_ON_MARKET_STALL, GRAB_BANNER, LEAP_GAP , JUMP_TO_TREE, CLIMB_ROUGH_WALL, CROSS_MONKEYBARS, JUMP_ON_TREE_TWO, JUMP_ON_TREE_TWO, JUMP_TO_DRYINGLINE);
		
		GeneralUtils.doObstacles(polli_obstacles);
		
		
	}

}
