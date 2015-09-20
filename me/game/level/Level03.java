package me.game.level;

import me.game.entity.EntityMain;
import me.game.entity.EntitySphere;
import me.game.main.Engine;
import me.game.main.World;
import me.game.util.Location;

public class Level03 extends Level{
	
	public Level03(){
		
	}
	@Override
	public World getWorld(Engine engine) {
		World world = new World();
		int main1 = world.addEntity(new EntityMain(new Location(0,0),true,0));
		world.addEntity(createSphere(world,new Location(16,16),main1,5,16,true,0,0));
		
		int main2 = world.addEntity(new EntityMain(new Location(16*8,16*8),false,4));
		world.addEntity(createSphere(world,new Location(16*7,16*7),main2,1,16,false,1,4));
		world.addEntity(createSphere(world,new Location(16*7,16*7),main2,0.5f,32,false,1,4));
		world.addEntity(createSphere(world,new Location(16*9,16*9),main2,5,64,true,1,4));
		
		return world;
	}
	
	
	public boolean startPaused(){return true;}
	
	@Override
	public String[] getText(Engine engine) {
		String[] text = {
				
				"Destroy the plants!",
				"Destory all plants to destroy",
				"the core sphere"};
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
	
	public void collisionMain(Engine engine, EntitySphere e, int i) {
		if(e.getClickable()==0){
		boolean enemy=false;
		for(int a=0;a<engine.getWorld().getEntityArray().length;a++){
			if(engine.getWorld().getEntityArray()[a]==null)continue;
			if(engine.getWorld().getEntityArray()[a] instanceof EntitySphere){
				EntitySphere main = (EntitySphere) engine.getWorld().getEntityArray()[a];
				if(main.getClickable()==1)enemy=true;
			}
		}
		if(!enemy){
		engine.getWorld().getEntityArray()[i] = null;
		engine.getSound().playClip("death");
		}
		}else{
			engine.getWorld().getEntityArray()[i] = null;
			engine.getSound().playClip("death");
		}
	}
	
	public void aiTick(Engine engine, EntitySphere e) {
		if(!e.getSide()){
		if(engine.getWorld().getMe() != null){
			boolean near = e.getDistance() >= Location.getDistance(e.getConnect().getLocation(), engine.getWorld().getMe().getLocation());
			if(!near)e.setDistance(e.getDistance()+e.getSpeed()/5);
			else e.setDistance(e.getDistance()-e.getSpeed()/5);
			}
		}
	}


}
