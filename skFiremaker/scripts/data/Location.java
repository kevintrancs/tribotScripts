package scripts.data;

import org.tribot.api2007.types.RSTile;

public enum Location {
	
	FALLY_EAST_BANK(new RSTile(3025,3363,0)),
	VARROCK_EAST_BANK(new RSTile(3272,3430,0)),
	VARROCK_WEST_BANK(new RSTile(3199,3442,0));
	
	RSTile startTile;
	Location(RSTile rstile){
		this.startTile = rstile;
	}
	
	public RSTile getTile(){
		return this.startTile;
	}

}
