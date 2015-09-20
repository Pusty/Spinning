package me.game.render;

import java.awt.Graphics;
import java.awt.Image;

import me.game.entity.Entity;
import me.game.entity.EntitySphere;
import me.game.level.Level;
import me.game.main.Engine;
import me.game.util.Location;

public class RenderClass {
	public RenderClass(){
		
	}
	public static Location getStartLocation(Engine e){
		Location extra = new Location(0,0);
		if(e.getSelectedIndex()>=0&&e.getSelectedIndex()<e.getWorld().getEntityArray().length){
			if(e.getWorld().getEntityArray()[e.getSelectedIndex()]!=null)
				extra=e.getWorld().getEntityArray()[e.getSelectedIndex()].getLocation().clone();
		}
		extra.setX(extra.getX()*e.getZoom());
		extra.setZ(extra.getZ()*e.getZoom());
		return new Location(-e.getWidth()/2 +extra.x,-e.getHeight()/2+extra.z);
	}
	public void render(Engine e,Graphics g){
		if(e.getWidth()*9/16 > e.getHeight())
		g.drawImage(e.getImageHandler().getImage("bg"), 0, 0, e.getWidth(), e.getWidth()*9/16, null);
		else
		g.drawImage(e.getImageHandler().getImage("bg"), 0, 0, e.getHeight()*16/9, e.getHeight(), null);
		
		
		if(e.getWorld()!=null){
		for(int i=0;i<e.getWorld().getEntityArray().length;i++){
			if(e.getWorld().getEntityArray()[i]==null)continue;
			if(!(e.getWorld().getEntityArray()[i] instanceof EntitySphere))continue;
			EntitySphere en = (EntitySphere) e.getWorld().getEntityArray()[i];
			if(en.getConnect()==null)continue;
			
			Location loc1 = new Location((en.getLocation().x * e.getZoom()) -getStartLocation(e).getX() ,(en.getLocation().z * e.getZoom())  -getStartLocation(e).getZ());
			Location loc2 = new Location((en.getConnect().getLocation().x * e.getZoom()) -getStartLocation(e).getX(), (en.getConnect().getLocation().z * e.getZoom())  -getStartLocation(e).getZ());
			
			g.drawLine((int)loc1.x, (int)loc1.z, (int)loc2.x, (int)loc2.z);
		}	
		
		for(int i=0;i<e.getWorld().getEntityArray().length;i++){
			if(e.getWorld().getEntityArray()[i]==null)continue;
				renderRelative(e,g,e.getWorld().getEntityArray()[i],null);
				if(e.getSelectedIndex()==i)
					if(e.getWorld().getEntityArray()[i] instanceof EntitySphere && ((EntitySphere)e.getWorld().getEntityArray()[i]).getClickable() == 0){
					renderRelative(e,g,e.getWorld().getEntityArray()[i],"item_13");
					}else{
						renderRelative(e,g,e.getWorld().getEntityArray()[i],"item_12");	
					}
		}
		
		if(e.getEndGame()==-1){
		renderText(e,g,new Location(e.getWidth()/2 - 16*9/2,e.getHeight()/2 - 16),"You lose!");
		renderText(e,g,new Location(e.getWidth()/2 - 16*20/2 ,e.getHeight()/2),"Press Space to retry");
		}else if(e.getEndGame()==1){
		renderText(e,g,new Location(e.getWidth()/2 - 16*8/2,e.getHeight()/2-16),"You win!");
		renderText(e,g,new Location(e.getWidth()/2 - 16*23/2,e.getHeight()/2),"Press Space to continue");
		}
		
		
		renderText(e,g,new Location(5,5),"Level "+(e.getLevel().getCur()+1));
		if(e.getEndGame()==-1)
		renderText(e,g,new Location(5,5+16*1),"You lose!");
		else if(e.getEndGame()==1)
		renderText(e,g,new Location(5,5+16*1),"You win!");
		
		Level lvl = e.getLevel().getLevel();
		if(lvl.getText(e).length >= 1){
			for(int i=0;i<lvl.getText(e).length;i++)
				renderText(e,g,new Location(5,5+16*(3+i)),lvl.getText(e)[i]);
		}
		if(!e.isTimeRunning())
			renderText(e,g,new Location(5,5+16*13),"PAUSED!");
		
		}else{
			if(e.getLevel().getCur()!=-1){
			renderCentured(e,g,new Location(0,-32),"You win!");
			renderCentured(e,g,new Location(0,-16),"Thank you for playing my game");
			renderCentured(e,g,new Location(0,0),"This game was made for Mini Ludum Dare");
			renderCentured(e,g,new Location(0,16),"- Pusty");
			}else{
				renderCentured(e,g,new Location(0,16*-4),"Hello and welcome to Spinning");
				renderCentured(e,g,new Location(0,16*-3),"In this game you try to destoy other");
				renderCentured(e,g,new Location(0,16*-2),"spheres with your own");
				renderCentured(e,g,new Location(0,16*-1),"Controls:");
				renderCentured(e,g,new Location(0,0),"W/S or Up/Down: Change Length of Rope");
				renderCentured(e,g,new Location(0,16*1),"P and 1-6: Change Speed of the Game");
				renderCentured(e,g,new Location(0,16*2),"Mouse: Select Sphere");
				renderCentured(e,g,new Location(0,16*3),"(If you can control a sphere the box will be green)");
				renderCentured(e,g,new Location(0,16*4),"Space: Unpause/Next Level");			
				renderCentured(e,g,new Location(0,16*5),"M: Stop Music if it annoys you");	
				renderCentured(e,g,new Location(0,16*7),"Press Space to start");		
			}
		}

	}
	
	public void renderCentured(Engine engine,Graphics g,Location offset,String txt){
		renderText(engine,g,new Location(engine.getWidth()/2 + offset.x - 16*txt.length()/2,engine.getHeight()/2 +offset.z),txt);
	}
	
	public void renderRelative(Engine en,Graphics g,Entity e,String txt){
		String img = e.getImage();
		if(txt!=null)img=txt;
		Location loc = e.getLocation();
			Image i = en.getImageHandler().getImage(img);
			g.drawImage(i, (int)((loc.x * en.getZoom()) -getStartLocation(en).getX()) - 16*en.getZoom()/2, (int)((loc.z * en.getZoom())  -getStartLocation(en).getZ()) - 16*en.getZoom()/2,16*en.getZoom(),16*en.getZoom(), null);
	}
	
	public void renderAbsolute(Engine en,Graphics g,Location loc,String img){
		if(en.getZoom()==1)
			g.drawImage(en.getImageHandler().getImage(img), (int)loc.x, (int)loc.z, null);
		else{
			Image i = en.getImageHandler().getImage(img);
			g.drawImage(i, (int)loc.x * en.getZoom(), (int)loc.z * en.getZoom(),16*en.getZoom(),16*en.getZoom(), null);
		}
	}
	
	
	public void renderText(Engine en,Graphics g,Location loc,String txt){
		if(txt == null || txt == "")return;
		for(int a=0;a<txt.length();a++)
			g.drawImage(en.getImageHandler().getImage("char_"+txt.toUpperCase().toCharArray()[a]), (int)loc.x + a*16, (int)loc.z, null);
	}

	public boolean getInitStart() {
		return true;
	}
}
