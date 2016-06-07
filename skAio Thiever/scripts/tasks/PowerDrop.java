package scripts.tasks;

import org.tribot.api2007.Inventory;

import scripts.data.State;
import scripts.data.Variables;
import scripts.framework.Task;

public class PowerDrop extends Task {

	@Override
	public boolean validate() {
		return Inventory.isFull();
	}

	@Override
	public void execute() {
		Variables.getVars().state = State.DROPPING;
		Inventory.dropAllExcept();
	}

}
