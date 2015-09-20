package me.game.level;

import me.game.entity.EntitySphere;
import me.game.main.Engine;
import me.game.main.World;
import me.game.util.Location;

public abstract class Level {
	public Level(){
		
	}
	
	public abstract World getWorld(Engine engine);
	public abstract String[] getText(Engine engine);
	public boolean startPaused(){return false;}
	

	public static EntitySphere createSphere(World w,Location l,int connect,float speed,float dis,boolean si,int click,int type){
		EntitySphere sphere =  new EntitySphere(l,click,type);
		if(connect>=0)
			sphere.setConnect(w.getEntityArray()[connect]);
		sphere.setSpeed(speed);
		sphere.setDistance(dis);
		sphere.setSide(si);
		return sphere;
	}

	public void collisionEvent(Engine engine, EntitySphere e, EntitySphere col) {
		col.setConnect(null);
		col.setClickable(2);
	}

	public void aiTick(Engine engine, EntitySphere e) {
		if(engine.getWorld().getMe() != null){
			boolean near = e.getDistance() >= Location.getDistance(e.getConnect().getLocation(), engine.getWorld().getMe().getLocation());
			if(!near)e.setDistance(e.getDistance()+1f);
			else e.setDistance(e.getDistance()-1f);
			}
	}

	public void collisionMain(Engine engine, EntitySphere e, int i) {
		engine.getWorld().getEntityArray()[i] = null;
		engine.getSound().playClip("death");
	}
}
