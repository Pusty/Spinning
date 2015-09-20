package me.game.level;

import me.game.main.Engine;

public class LevelHandler {
	Level[] level;
	int curlevel;
	public LevelHandler(){
		curlevel=0;
		level = new Level[25];
	}
	
	public void addLevel(int ind,Level l){
		level[ind]=l;
	}
	public Level[] getLevels(){return level;}
	public Level getLevel(){return level[curlevel];}
	public int getCur(){return curlevel;}
	public void setCur(int ind){curlevel=ind;}
	
	public void setLevel(Engine en,int ind){
		if(ind == 5){
			en.setWorld(null);
			curlevel=ind;
		}else if(ind == -1){
			en.setWorld(null);
			curlevel=ind;
		}else{
		if(ind<0 || ind >= level.length || level[ind]==null)return;
		curlevel=ind;
		en.setEndGame(0);
		en.setTimeRunning(!getLevel().startPaused());
		en.setWorld(getLevel().getWorld(en));
		
		en.getSound().getClip("bg_1").stop();
		en.getSound().getClip("bg_2").stop();
		if(ind != 4)
			en.getSound().playClip("bg_1");
		else
			en.getSound().playClip("bg_2");
		}
	}
}
