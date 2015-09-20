package me.game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameKeyHandler implements KeyListener {
	Engine engine;
	
	public static boolean down;
	public static boolean up;
	public static boolean left;
	public static boolean right;
	
	public static boolean pause;

	public GameKeyHandler(Engine e) {
		engine = e;

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_1:
			if(engine.getEndGame()!=0)break;
			engine.setSpeed(1);
			engine.setTimeRunning(true);
			break;
		case KeyEvent.VK_2:
			if(engine.getEndGame()!=0)break;
			engine.setSpeed(2);
			engine.setTimeRunning(true);
			break;
		case KeyEvent.VK_3:
			if(engine.getEndGame()!=0)break;
			engine.setSpeed(3);
			engine.setTimeRunning(true);
			break;
		case KeyEvent.VK_4:
			if(engine.getEndGame()!=0)break;
			engine.setSpeed(4);
			engine.setTimeRunning(true);
			break;
		case KeyEvent.VK_5:
			if(engine.getEndGame()!=0)break;
			engine.setSpeed(5);
			engine.setTimeRunning(true);
			break;
		case KeyEvent.VK_6:
			if(engine.getEndGame()!=0)break;
			engine.setSpeed(6);
			engine.setTimeRunning(true);
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_SPACE:
			if(engine.getEndGame()!=0){
				if(engine.getEndGame()==1)
					engine.getLevel().setLevel(engine, engine.getLevel().getCur()+1);
				else{
					if(engine.getLevel().getCur()==-1)
						engine.getLevel().setLevel(engine, 0);
					else
						engine.getLevel().setLevel(engine, engine.getLevel().getCur());
				}
				
				engine.getSound().playClip("start");
			}else if(!engine.isTimeRunning() || engine.getTickSpeed() < 1.5f){
				engine.setTimeRunning(true);
				engine.setSpeed(1f);
			}else
			up = true;
			break;
		case KeyEvent.VK_SHIFT:
			down = true;
			break;
		case KeyEvent.VK_M:
			engine.getSound().getClip("bg_1").stop();
			engine.getSound().getClip("bg_2").stop();
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;	
//			engine.getLevel().setLevel(engine, engine.getLevel().getCur()-1);
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
//			engine.getLevel().setLevel(engine, engine.getLevel().getCur()+1);
			break;
		case KeyEvent.VK_P:
			if(engine.getEndGame()!=0)break;
			if(!pause){
			engine.setTimeRunning(!engine.isTimeRunning());
			pause=true;
			}
			break;
		case KeyEvent.VK_PLUS:
			if(engine.getZoom()+1 < 6)
			engine.setZoom(engine.getZoom()+1);
			break;
		case KeyEvent.VK_MINUS:
			if(engine.getZoom()-1 > 0)
			engine.setZoom(engine.getZoom()-1);
			break;
		case 75:
			System.exit(0);
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_SPACE:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_SHIFT:
			down = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;	
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_P:
			pause = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {

	}
}