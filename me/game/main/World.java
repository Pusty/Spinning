package me.game.main;

import me.game.entity.Entity;
import me.game.entity.EntityMain;

public class World {
	Entity[] entitys;
	public World(){
		entitys = new Entity[25];
	}
	public Entity[] getEntityArray(){return entitys;}
	public int addEntity(Entity e){
		for(int i=0;i<entitys.length;i++){
			if(entitys[i]==null){entitys[i]=e;return i;}
		}
		return -1;
	}
	
	public int getEntity(Entity e){
		for(int i=0;i<entitys.length;i++){
			if(entitys[i]==e){return i;}
		}
		return -1;
	}
	
	
	public void removeEntity(Entity e){
		for(int i=0;i<entitys.length;i++){
			if(entitys[i]==e){
				entitys[i] = null;return;}
		}
	}
	public EntityMain getMe() {
		for(int i=0;i<entitys.length;i++){
			if(entitys[i] != null && entitys[i] instanceof EntityMain && ((EntityMain)entitys[i]).getTeam()){return (EntityMain) entitys[i];}
		}
		return null;
	}
	
	
}
