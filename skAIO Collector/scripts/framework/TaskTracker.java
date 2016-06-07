package scripts.framework;

import org.tribot.api.General;

import scripts.Data.Vars;

public class TaskTracker {

	private Activity currentTask;
	private Activity[] taskList;

	public TaskTracker(Activity[] taskList) {
		this.taskList = taskList;
	}

	public void runIt() {
		for (Activity task : taskList) {
			if (task.validate()) {
				currentTask = task;
				Vars.currStatus = task.status();
				task.execute();
				General.sleep(50, 100);
			}
		}
	}

	public Activity getCurrentTask() {
		return currentTask;
	}

}
