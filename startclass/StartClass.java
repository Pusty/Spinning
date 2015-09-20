package startclass;


import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import me.game.main.Engine;


public class StartClass{
     
	   
	public void setSize(Engine engine, int x, int y){
		 JFrame frame = new JFrame();
	    frame.setName(engine.getName());
            
			frame.setSize(x, y);
			
		
			frame.setTitle("Spinning[MiniLD] ");
		//	engine.initStartImages();
			frame.add(engine);
			engine.iconInit();
			frame.setIconImage(engine.getIconImage());		
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			frame.setVisible(true);
			
			 }
		
	}

	

