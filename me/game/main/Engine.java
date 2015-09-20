package me.game.main;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import me.game.level.LevelHandler;
import me.game.render.PictureLoader;
import me.game.render.RenderClass;
import me.game.util.SoundLoader;

public abstract class Engine extends Applet{
	private static final long serialVersionUID = 5253769474528882845L;
	
	TickClass tickclass;
	RenderClass renderclass;
	World world;
	boolean timeRunning=true;
	int selectedIndex=-1;
	float tickSpeed=1f;
	int endGame = 0;
	PictureLoader pictureloader;
	SoundLoader soundloader;
	
	boolean running=true;
	LevelHandler levelhandler;
	int zoom=1;
	public int getEndGame(){return endGame;}
	public void setEndGame(int inx){endGame=inx;}
	public int getSelectedIndex(){return selectedIndex;}
	public void setSelectedIndex(int inx){selectedIndex=inx;}
	
	public float getTickSpeed(){return tickSpeed;}
	public void setSpeed(float s){tickSpeed=s;}
	public SoundLoader getSound(){return soundloader;}
	public Engine(){
		pictureloader=new PictureLoader();
		levelhandler=new LevelHandler();
		soundloader=new SoundLoader(this);
		preInit();
		Init();
		postInit();
	}
	public LevelHandler getLevel(){return levelhandler;}
	public boolean isTimeRunning(){return timeRunning;}
	public void setTimeRunning(boolean b){timeRunning=b;}
	
	public int getZoom(){
		return zoom;
	}
	
	public void setZoom(int z){
		zoom=z;
	}
	
	public PictureLoader getImageHandler(){return pictureloader;}

	public boolean isRunning(){
		return running;
	}
	public void setRunning(boolean b){
		running=b;
	}
	
	public abstract void preInit();
	public abstract void Init();
	public abstract void postInit();
	
	public TickClass getTick() {
		return tickclass;
	}

	public void setTick(TickClass tickclass) {
		this.tickclass = tickclass;
	}

	public RenderClass getRender() {
		return renderclass;
	}

	public void setRender(RenderClass renderclass) {
		this.renderclass = renderclass;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public String getName(){return "Engine";}
	
	@Override
	public void paint(Graphics g) {
			super.paint(g);
			BufferedImage screen = new BufferedImage(this.getWidth(), this.getHeight(), 4);
			Graphics g3 = screen.getGraphics();
			 if (getRender().getInitStart()) 
				 getRender().render(this, g3);
			g.drawImage(screen, 0, 0, this);
			this.repaint();
	}

	@Override
	public void update(Graphics g) {
			BufferedImage screen = new BufferedImage(this.getWidth(), this.getHeight(), 4);
			Graphics g3 = screen.getGraphics();
			if (isRunning())
				getRender().render(this, g3);
			g.drawImage(screen, 0, 0, this);
		}

		public void addKeyboard() {
			this.addKeyListener(new GameKeyHandler(this));
		}

		public void addMouse() {
			GameMouseHandler g = new GameMouseHandler(this);
			this.addMouseListener(g);
			this.addMouseMotionListener(g);
		}
		public void iconInit() {
			
		}
		
		public Image getIconImage(){
			return this.getImageHandler().getImage("item_1");
		}


}
