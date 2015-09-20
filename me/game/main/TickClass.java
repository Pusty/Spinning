package me.game.main;


import me.game.entity.Entity;
import me.game.entity.EntityMain;
import me.game.entity.EntitySphere;
import me.game.util.Location;

public class TickClass {

	public void tick(Engine engine) {
		if(!engine.isTimeRunning()){}else{
		
		boolean me=false;
		boolean enemy=false;
		for(int i=0;i<engine.getWorld().getEntityArray().length;i++){
			if(engine.getWorld().getEntityArray()[i]==null)continue;
			if(engine.getWorld().getEntityArray()[i] instanceof EntityMain){
				EntityMain main = (EntityMain) engine.getWorld().getEntityArray()[i];
				if(main.getTeam())me=true;
				else enemy=true;
			}
			tickEntity(engine,engine.getWorld().getEntityArray()[i]);
		}
		
		if(engine.getSelectedIndex()>=0&&engine.getSelectedIndex()<engine.getWorld().getEntityArray().length){
			if(engine.getWorld().getEntityArray()[engine.getSelectedIndex()]!=null && engine.getWorld().getEntityArray()[engine.getSelectedIndex()] instanceof EntitySphere){
				EntitySphere ep = (EntitySphere) engine.getWorld().getEntityArray()[engine.getSelectedIndex()];
				if(ep.getClickable()==0){
				if(GameKeyHandler.up)ep.setDistance(ep.getDistance()+1f);
				if(GameKeyHandler.down)ep.setDistance(ep.getDistance()-1f);
				}
			}
		}
		
		if(me == false){
			engine.setTimeRunning(false);
			engine.setEndGame(-1);
			System.out.println("You lose!");
		}
		if(enemy == false){
			engine.setTimeRunning(false);
			engine.setEndGame(1);
			System.out.println("You win!");
		}
		}
		
	}
	
	public void tickEntity(Engine engine,Entity e){
		if(e instanceof EntitySphere)sphereTick(engine,(EntitySphere) e);
	}
	public void sphereTick(Engine engine,EntitySphere e){
		if(e.getConnect()==null){
			if(e.getClickable()!=2){
				e.setClickable(3);
			}
		}
		if(e.getConnect()==null || e.getConnect().getLocation()==null)return;
		if(engine.getWorld().getEntity(e.getConnect()) == -1){
			e.setConnect(null);
			return;
		}
		
		
		if(e.getClickable()==1){
			engine.getLevel().getLevel().aiTick(engine,e);
		}
		
		Location loc = e.getLocation();
		Location rotLoc = e.getConnect().getLocation();
		float distance = e.getDistance();
		float angle = 0;
	
		{
			float r=0f;
			float ry=0f;
			Location direction = new Location(loc.x-rotLoc.x,loc.z-rotLoc.z);
			direction = Location.getNorm(direction);
			r=(float) Math.toDegrees(Math.acos(direction.getX()));
			ry=(float) Math.toDegrees(Math.asin(direction.getZ()));	
			if(ry<=0)
				r=360f-r;
			angle=r;
		}
		if(Float.isNaN(angle))angle=0;
		
		
		angle = angle + e.getSpeed() * (e.getSide()?1:-1);
		
		
		Location newLoc = new Location((float)(rotLoc.x+Math.cos(Math.toRadians(angle))*distance)
				,(float)(rotLoc.z+Math.sin(Math.toRadians(angle))*distance));
		e.getLocation().set(newLoc);
		
		
		{
			for(int i=0;i<engine.getWorld().getEntityArray().length;i++){
				if(engine.getWorld().getEntityArray()[i]==null)continue;
				if(engine.getWorld().getEntityArray()[i]==e)continue;
				if(engine.getWorld().getEntityArray()[i]==e.getConnect())continue;
				Location loc2 = engine.getWorld().getEntityArray()[i].getLocation();
				if(Location.getDistance(newLoc, loc2)<10){
					e.setSide(!e.getSide());
					engine.getSound().playClip("hit");
					if(engine.getWorld().getEntityArray()[i] instanceof EntitySphere){
						EntitySphere col = (EntitySphere) engine.getWorld().getEntityArray()[i];
						if(col.getConnect()==null){
							if(col.getClickable() == 3){
								col.setClickable(2);
							}else{
							col.setConnect(e);
							col.setClickable(e.getClickable());
							}
						}else{
							engine.getLevel().getLevel().collisionEvent(engine,e,col);
						}
					}else{
						engine.getLevel().getLevel().collisionMain(engine,e,i);
					}
				}
			}
		}
		
	}

}
