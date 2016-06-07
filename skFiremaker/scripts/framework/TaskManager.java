package scripts.framework;

import java.util.ArrayList;

public class TaskManager {

	private static ArrayList<Task> tasks = new ArrayList<Task>();

	public static ArrayList<Task> getTasks() {
		return tasks;
	}

	public static void addTask(ArrayList<Task> t) {
		tasks.addAll(t);
	}

}
