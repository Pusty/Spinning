package me.game.entity;

import me.game.util.Location;

public class EntitySphere extends Entity{
	boolean side;
	float distance;
	Entity connect;
	float speed;
	int type;
	int clickable;
	public EntitySphere(Location loc,int stat,int type) {
		super(loc);
		side=true;
		distance=32;
		speed=5f;
		clickable=stat;
		this.type=type;
	}
	public int getType(){return type;}
	public int getClickable(){return clickable;}
	public void setClickable(int stat){clickable=stat;}
	
	
	
	public boolean getSide() {
		return side;
	}



	public void setSide(boolean side) {
		this.side = side;
	}



	public float getDistance() {
		return distance;
	}



	public void setDistance(float distance) {
		if(distance > 16*16)return;
		if(distance < 8)return;
		this.distance = distance;
	}



	public Entity getConnect() {
		return connect;
	}



	public void setConnect(Entity connect) {
		this.connect = connect;
	}



	public float getSpeed() {
		return speed;
	}



	public void setSpeed(float speed) {
		this.speed = speed;
	}



	public String getImage(){
		if(clickable==2){
			return "item_6";
		}else if(type==1){
			return "item_2";
		}else if(type==0){
			return "item_4";
		}else if(type==2){
			return "item_6";
		}else if(type==3){
			return "item_9";
		}else if(type==4){
			return "item_11";
		}
		return "item_7";
	}

}
