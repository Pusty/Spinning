
package me.game.main;


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import me.game.entity.Entity;
import me.game.render.RenderClass;
import me.game.util.Location;

public class GameMouseHandler implements MouseListener, MouseMotionListener {
    Engine engine;

    public GameMouseHandler(Engine e) {
	engine = e;

    }


    @Override
    public void mouseClicked(MouseEvent ev) {
    	Location mouseLocation = new Location(ev.getX(),ev.getY());
    	if(engine.getWorld()!=null){
    	for(int i=0;i<engine.getWorld().getEntityArray().length;i++){
    		Entity e = engine.getWorld().getEntityArray()[i];
    		if(e==null)continue;
    		Location entityLoc = new Location((e.getLocation().x * engine.getZoom()) -RenderClass.getStartLocation(engine).getX(), 
    		(e.getLocation().z * engine.getZoom())  -RenderClass.getStartLocation(engine).getZ() );
    		
    		if(Location.getDistance(entityLoc, mouseLocation) <= 16*engine.getZoom()/2){
    			engine.setSelectedIndex(i);
    			engine.getSound().playClip("select");
    	}
    	}
    	}
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent ev) {
	
    }

    @Override
    public void mouseReleased(MouseEvent ev) {
	
    }

    @Override
    public void mouseDragged(MouseEvent ev) {
	 
    }

    @Override
    public void mouseMoved(MouseEvent ev) {
	
    }

}
