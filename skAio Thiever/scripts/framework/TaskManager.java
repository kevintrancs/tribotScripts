package scripts.framework;

import java.util.ArrayList;

import org.tribot.api.General;

public class TaskManager {

	private static ArrayList<Task> tasks = new ArrayList<Task>();

	public static ArrayList<Task> getTasks() {
		return tasks;
	}

	public static void addTasks(ArrayList<Task> task) {
		tasks.addAll(task);
	}

}
