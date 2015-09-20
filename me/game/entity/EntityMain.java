package me.game.entity;

import me.game.util.Location;

public class EntityMain extends Entity{
	
	boolean team;
	int type;
	public EntityMain(Location loc,boolean t,int type) {
		super(loc);
		team=t;
		this.type=type;
	}
	public boolean getTeam(){return team;}
	public int getType(){return type;}
	
	public String getImage(){
		if(type==1){
			return "item_1";
		}else if(type==0){
			return "item_3";
		}else if(type==2){
			return "item_5";
		}else if(type==3){
			return "item_8";
		}else if(type==4){
			return "item_10";
		}
		return "item_7";
	}

}
