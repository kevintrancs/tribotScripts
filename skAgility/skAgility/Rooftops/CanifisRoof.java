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

public class CanifisRoof extends Task {

	private List<Obstacles> canifis_obstacles = new ArrayList<>();
	
	private final Obstacles JUMP_GAP_ONE = new Obstacles(10820, new RSArea(new RSTile(3507, 3492, 2), new RSTile(3505, 3497, 2)), "Jump Gap", new RSTile(3505, 3497, 2));
	
	private final Obstacles JUMP_GAP_TWO = new Obstacles(10821, new RSArea(new RSTile(3503, 3506, 2), new RSTile(3498, 3504, 2)), "Jump Gap",new RSTile(3498, 3504, 2));
	
	private final Obstacles JUMP_GAP_THREE = new Obstacles(10828, new RSArea(new RSTile(3492, 3504, 2), new RSTile(3487, 3499, 2)), "Jump Gap",new RSTile(3487, 3499, 2));
	
	private final Obstacles JUMP_GAP_FOUR = new Obstacles(10822, new RSArea(new RSTile(3479, 3499, 3), new RSTile(3475, 3492, 3)), "Jump Gap",new RSTile(3475, 3492, 3));
	
	private final Obstacles POLE_VAULT = new Obstacles(10831, new RSArea(new RSTile(3478, 3486, 2), new RSTile(3480, 3482, 2)), "Vault Pole-vault",new RSTile(3479, 3484, 2));
	
	private final Obstacles JUMP_GAP_FIVE = new Obstacles(10823, new RSArea(new RSTile(3489, 3476, 3), new RSTile(3503, 3472, 3)), "Jump Gap",new RSTile(3503, 3475, 3));
	
	private final Obstacles HURDLE_LEDGE = new Obstacles(10832, new RSArea(new RSTile(3511, 3475, 2), new RSTile(3509, 3481, 2)), "Jump Gap",new RSTile(3509, 3481, 2));
	
	
	@Override
	public boolean activate() {
		return Player.getPosition().getPlane() != 0 && skAgility.rooftop.getName().equals(Rooftops.CANIFIS.getName());
	}

	@Override
	public void execute() {
	
		Collections.addAll(canifis_obstacles, JUMP_GAP_ONE, JUMP_GAP_TWO, JUMP_GAP_THREE , JUMP_GAP_FOUR, POLE_VAULT, JUMP_GAP_FIVE, HURDLE_LEDGE);
		
		GeneralUtils.doObstacles(canifis_obstacles);
		
	}

}
