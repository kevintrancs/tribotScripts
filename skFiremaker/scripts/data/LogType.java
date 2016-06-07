package scripts.data;

public enum LogType {
	
	NORMAL_LOG("Logs", 1511),
	OAK_LOG("Oak logs", 1521),
	WILLOW_LOG("Willow logs", 1519),
	MAPLE_LOG("Maple logs", 1517),
	YEW_LOG("Yew logs", 1515),
	MAGIC_LOG("Magic logs", 1513);
	
	// Name, ID
	
	private String name;
	private int id;
	
	LogType(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	public String getLogName(){
		return this.name;
	}
	
	public int getLogId(){
		return this.id;
	}
	

}
