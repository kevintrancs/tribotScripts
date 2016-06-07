package scripts.framework;

public abstract class Activity {
	
	public abstract boolean validate();
	
	public abstract void execute();
	
	public abstract String status();

}
