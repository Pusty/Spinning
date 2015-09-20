package me.game.main;

import startclass.StartClass;
import me.game.level.*;
import me.game.render.RenderClass;
import me.game.render.SheetLoader;

public class GameClass extends Engine{


	@Override
	public void preInit() {
		try{
			SheetLoader sheetloader = new SheetLoader(StartClass.class.getResource("entitys.png"), 8, 8, 16, 16);
			for (int a = 0; a < 8 * 8; a++) {
				getImageHandler().ImportFromSheet("item_" + String.valueOf(a),
						sheetloader, a % 8, a / 8);
			}
			
			String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
					"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
					"V", "W", "X", "Y", "Z", ":", "!", "?", ".", "[", "]", "0",
					"1", "2", "3", "4", "5", "6", "7", "8", "9", "(", ")", "+",
					"-", "/", " ", "_" };
			sheetloader = new SheetLoader(StartClass.class.getResource("letters.png"), 8, 8, 16, 16);
			for (int a = 0; a < letter.length; a++) {
				getImageHandler().ImportFromSheet("char_" + letter[a],
						sheetloader, a % 8, a / 8);

			}
			
			getImageHandler().addImage("bg", StartClass.class.getResource("background.png"));			
		}catch(Exception e){}
		this.setRender(new RenderClass());
		this.setTick(new TickClass());
		this.addMouse();
		this.addKeyboard();
		engine=this;
		
		getLevel().addLevel(0, new Level01());
		getLevel().addLevel(1, new Level02());
		getLevel().addLevel(2, new Level03());
		getLevel().addLevel(3, new Level04());
		getLevel().addLevel(4, new Level05());
		
		getSound().addSound("death", StartClass.class.getResource("death.wav"),false);
		getSound().addSound("start", StartClass.class.getResource("start.wav"),false);
		getSound().addSound("hit", StartClass.class.getResource("hit.wav"),false);
		getSound().addSound("select", StartClass.class.getResource("select.wav"),false);
		getSound().addSound("bg_1", StartClass.class.getResource("bg_1.wav"),true);
		getSound().addSound("bg_2", StartClass.class.getResource("bg_2.wav"),true);
//		this.getSound().playClip("click");
	
	}

	@Override
	public void Init() {	
		setTimeRunning(false);
		getLevel().setLevel(this, -1);
		this.setEndGame(-1);
	}

	@Override
	public void postInit() {
		syncTicks();
	}
	
    public static void main(String[] args) {
		StartClass render = new StartClass();
		render.setSize(new GameClass(), 500*16/9, 500);
    }
    
    Thread mainThread;
    Thread tickThread;
    Engine engine;
    public void syncTicks(){
    	mainThread =new Thread(new Runnable() {
    	    @Override
    	    public void run() {
    		while (isRunning()) {
    		    repaint();
    		    try {
    			Thread.sleep(1000/30);
    		    }
    		    catch (InterruptedException e) {}
    		}
    	    }
    	});
    	tickThread =new Thread(new Runnable() {
    	    @Override
    	    public void run() {
    		while (isRunning()) {
    		    getTick().tick(engine);
    		    try {
    			Thread.sleep((int)(1000/20 * getTickSpeed()));
    		    }
    		    catch (InterruptedException e) {}
    		}
    	    }
    	});
    	mainThread.start();
    	tickThread.start();
    }
    
}
