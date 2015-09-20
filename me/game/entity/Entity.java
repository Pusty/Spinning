package me.game.entity;


import me.game.util.Location;
import me.game.util.Velocity;

public class Entity {
Location location;
Velocity velocity;
	public Entity(Location loc){
		location=loc;
	}
	public Velocity getVelocity() {
		return velocity;
	}
	public void setVelocity(Velocity v) {
		velocity=v;
	}
	public Location getLocation() {
		return location;
	}
	public String getImage(){
		return "item_4";
	}

}
