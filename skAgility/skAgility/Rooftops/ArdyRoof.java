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

public class ArdyRoof extends Task {
	
	private List<Obstacles> ardy_obstacles = new ArrayList<>();
	
	private final Obstacles JUMP_GAP = new Obstacles(11406, new RSArea(new RSTile(2671, 3299, 3), new RSTile(2671, 3309, 3)), "Jump Gap",
			new RSTile(2671, 3307, 3));
	
	private final Obstacles WALK_ON_PLANK = new Obstacles(11631, new RSArea(new RSTile(2665, 3318, 3), new RSTile(2662, 3318, 3)), "Walk-on Plank",
			new RSTile(null));
	
	private final Obstacles JUMP_GAP_TWO = new Obstacles(11429, new RSArea(new RSTile(2654, 3318, 3), new RSTile(2656, 3318, 3)), "Jump Gap",
			new RSTile(null));
	
	private final Obstacles JUMP_GAP_THREE = new Obstacles(11430, new RSArea(new RSTile(2653, 3314, 3), new RSTile(2653, 3310, 3)), "Jump Gap",
			new RSTile(2653, 3311, 3));
	
	private final Obstacles BALANCE_ACROSS_STEEP_ROOF = new Obstacles(11633, new RSArea(new RSTile(2651, 3309, 3), new RSTile(2653, 3300, 3)),
			"Balance-across Steep roof", new RSTile(2653, 3301, 3));
	
	private final Obstacles JUMP_GAP_FOUR = new Obstacles(11630, new RSArea(new RSTile(2656, 3297, 3), new RSTile(2654, 3299, 3)), "Jump Gap",
			new RSTile(null));
	
	

	@Override
	public boolean activate() {
		return Player.getPosition().getPlane() == 3 && skAgility.rooftop.getName().equals(Rooftops.ARDOUGNE.getName());
	}

	@Override
	public void execute() {
		
		Collections.addAll(ardy_obstacles, JUMP_GAP, WALK_ON_PLANK, JUMP_GAP_TWO , JUMP_GAP_THREE, BALANCE_ACROSS_STEEP_ROOF, JUMP_GAP_FOUR);
		
		GeneralUtils.doObstacles(ardy_obstacles);
		
		
	}

}
