package me.game.level;

import me.game.entity.EntityMain;
import me.game.entity.EntitySphere;
import me.game.main.Engine;
import me.game.main.World;
import me.game.util.Location;

public class Level02 extends Level{
	
	public Level02(){
		
	}
	@Override
	public World getWorld(Engine engine) {
		World world = new World();
		int main1 = world.addEntity(new EntityMain(new Location(0,0),true,0));
		world.addEntity(createSphere(world,new Location(16,16),main1,5,16,true,0,0));
		
		int main2 = world.addEntity(new EntityMain(new Location(16*6,16*6),false,1));
		world.addEntity(createSphere(world,new Location(16*7,16*7),main2,1,16,true,1,1));
		
		return world;
	}
	public boolean startPaused(){return true;}
	
	@Override
	public String[] getText(Engine engine) {
		String[] text = {
				
				"Press P to unpause",
				"Press 1-6 to slow down",
				"If your core sphere gets",
				"destroyed you lose!",
				"Try to hit the moving sphere"};
		return text;
	}
	
	public void collisionEvent(Engine engine, EntitySphere e, EntitySphere col) {
		col.setConnect(null);
		col.setClickable(2);
		col.setDistance(16);
	}


}
