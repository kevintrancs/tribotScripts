package scripts.framework;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public abstract class GuiSet extends JFrame {

	public abstract ArrayList<Task> getUserTasks();

	public void finishSetUp(ActionEvent e) {
		TaskManager.addTasks(getUserTasks());
	}

}
