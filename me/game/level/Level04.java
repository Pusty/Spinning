package me.game.level;

import me.game.entity.EntityMain;
import me.game.entity.EntitySphere;
import me.game.main.Engine;
import me.game.main.World;
import me.game.util.Location;

public class Level04 extends Level{
	
	public Level04(){
		
	}
	@Override
	public World getWorld(Engine engine) {
		World world = new World();
		int main1 = world.addEntity(new EntityMain(new Location(0,0),true,0));
		world.addEntity(createSphere(world,new Location(16,16),main1,10,16,true,0,0));
		
		int main2 = world.addEntity(new EntityMain(new Location(16*6,16*6),false,4));
		world.addEntity(createSphere(world,new Location(16*7,16*7),main2,1,16,true,1,4));
		
		int main3 = world.addEntity(new EntityMain(new Location(16*-4,16*-4),false,4));
		world.addEntity(createSphere(world,new Location(16*-5,16*-5),main3,1,16,true,1,4));
		
		return world;
	}
	public boolean startPaused(){return true;}
	
	@Override
	public String[] getText(Engine engine) {
		String[] text = {
		"Destory both cores!"};
		return text;
	}
	
	public void collisionEvent(Engine engine, EntitySphere e, EntitySphere col) {
		if(e.getClickable() == col.getClickable()){}else{
		if(e.getClickable()==0){
		col.setConnect(null);
		col.setClickable(2);
		}else{
		e.setConnect(null);
		e.setClickable(2);	
		}
		}
	}


}
