package me.game.level;

import me.game.entity.EntityMain;
import me.game.main.Engine;
import me.game.main.World;
import me.game.util.Location;

public class Level01 extends Level{
	
	public Level01(){
		
	}
	@Override
	public World getWorld(Engine engine) {
		World world = new World();
		int main1 = world.addEntity(new EntityMain(new Location(0,0),true,0));
		world.addEntity(createSphere(world,new Location(16,16),main1,5,16,true,0,0));
		
		int main2 = world.addEntity(new EntityMain(new Location(16*6,16*6),false,1));
		
		return world;
	}

	@Override
	public String[] getText(Engine engine) {
		String[] text = {
				"Press +/- to zoom",
				"Use mouse to select sphere",
				"Use up and down (arrow)",
				"to change distance",
				"use p to pause",
				"",
				"try to hit the enemys",
				"core sphere"};
		return text;
	}

}
