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

public class VarrockRoof extends Task {
	
	private List<Obstacles> varrock_obstacles = new ArrayList<>();
	
	private final Obstacles CROSS_CLOTHES_LINE = new Obstacles(10587, new RSArea(new RSTile(3214, 3419, 3), new RSTile(3219, 3411, 3)),
			"Cross Clothes Line", new RSTile(3214, 3414, 3));
	
	private final Obstacles LEAP_GAP_ONE = new Obstacles(10642, new RSArea(new RSTile(3201, 3417, 3), new RSTile(3208, 3414, 3)), "Leap Gap",
			new RSTile(3201, 3416, 3));
	
	private final Obstacles BALANCE_WALL = new Obstacles(10777, new RSArea(new RSTile(3194, 3416, 1), new RSTile(3197, 3416, 1)), "Balance Wall",
			new RSTile(3194, 3416, 1));
	
	private final Obstacles LEAP_GAP_TWO = new Obstacles(10778, new RSArea(new RSTile(3198, 3402, 3), new RSTile(3192, 3406, 3)), "Leap Gap",
			new RSTile(3193, 3402, 3));
	
	private final Obstacles LEAP_GAP_THREE = new Obstacles(10779, new RSArea(new RSTile(3192, 3398, 3), new RSTile(3208, 3395, 3)), "Leap Gap",
			new RSTile(3208, 3395, 3));
	
	private final Obstacles LEAP_GAP_FOUR = new Obstacles(10780, new RSArea(new RSTile(3218, 3399, 3), new RSTile(3231, 3402, 3)), "Leap Gap",
			new RSTile(3232, 3402, 3));
	
	private final Obstacles HURDLE_LEDGE = new Obstacles(10781, new RSArea(new RSTile(3236, 3403, 3), new RSTile(3235, 3408, 3)), "Hurdle Ledge",
			new RSTile(3235, 3408, 3));
	
	private final Obstacles JUMP_OFF_EDGE = new Obstacles(10817, new RSArea(new RSTile(3240, 3410, 3), new RSTile(3236, 3415, 3)), "Jump-off Edge",
			new RSTile(3236, 3415, 3));
	

	@Override
	public boolean activate() {
		return Player.getPosition().getPlane() != 0 && skAgility.rooftop.getName().equals(Rooftops.VARROCK.getName());
	}

	@Override
	public void execute() {
		
		Collections.addAll(varrock_obstacles, CROSS_CLOTHES_LINE, LEAP_GAP_ONE, BALANCE_WALL , LEAP_GAP_TWO, LEAP_GAP_THREE, LEAP_GAP_FOUR, HURDLE_LEDGE,JUMP_OFF_EDGE );
		
		GeneralUtils.doObstacles(varrock_obstacles);
		
	}

}
