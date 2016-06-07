package scripts.framework;

import org.tribot.api.General;

public class Looper {

	public void loop() {
		for (Task t : TaskManager.getTasks()) {
			if (t.validate())
				t.execute();

			General.sleep(100);
		}
	}

}
