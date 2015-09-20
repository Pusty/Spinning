package me.game.level;

import me.game.entity.EntityMain;
import me.game.entity.EntitySphere;
import me.game.main.Engine;
import me.game.main.World;
import me.game.util.Location;

public class Level05 extends Level{
	
	public Level05(){
		
	}
	@Override
	public World getWorld(Engine engine) {
		World world = new World();
		int main1 = world.addEntity(new EntityMain(new Location(0,0),true,0));
		world.addEntity(createSphere(world,new Location(16,16),main1,10,16,true,0,0));
		
		int main2 = world.addEntity(new EntityMain(new Location(16*10,16*10),false,3));
		world.addEntity(createSphere(world,new Location(16*9,16*9),main2,1,32,true,1,3));
		int main3 = world.addEntity(createSphere(world,new Location(16*6,16*6),main2,10,64,false,1,3));
		world.addEntity(createSphere(world,new Location(16*5,16*5),main3,5,16,true,1,3));
		world.addEntity(createSphere(world,new Location(16*4,16*4),main3,5,16,true,1,3));
		world.addEntity(createSphere(world,new Location(16*10,16*10),main2,5,16,false,1,3));
		world.addEntity(createSphere(world,new Location(16*8,16*8),main2,5,16,false,1,3));
		
		
		return world;
	}
	public boolean startPaused(){return true;}
	
	public void aiTick(Engine engine, EntitySphere e) {
		if(e.getSide()){
		if(engine.getWorld().getMe() != null){
			boolean near = e.getDistance() >= Location.getDistance(e.getConnect().getLocation(), engine.getWorld().getMe().getLocation());
			if(!near)e.setDistance(e.getDistance()+e.getSpeed()/5);
			else e.setDistance(e.getDistance()-e.getSpeed()/5);
			}
		}
	}
	
	@Override
	public String[] getText(Engine engine) {

		String[] text = {
				"FINAL BOSS!",
				"Hit every eyeball to",
				"destroy the eyecore"};
		return text;
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
			if(((EntityMain)engine.getWorld().getEntityArray()[i]).getTeam()){
			engine.getWorld().getEntityArray()[i] = null;
			engine.getSound().playClip("death");
			}
		}
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
