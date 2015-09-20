package me.game.render;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;
import javax.swing.ImageIcon;




public class PictureLoader {
	HashMap<String, Image> list = new HashMap<String, Image>();
	HashMap<String, Integer> integerlist = new HashMap<String, Integer>();
    public boolean useOwnSize=false;
    public int ownSizeX=0;
    public int ownSizeY=0;
	
	public PictureLoader() {
		list.clear();
		integerlist.clear();
	}
	
	public void setOwnSize(int x,int y){
		useOwnSize=true;
		ownSizeX=x;
		ownSizeY=y;
	}

	
	
	
	public void ImportFromSheet(String name,SheetLoader loader,int x,int y){
		Image img = loader.getImage(x, y);
		int sizex=(int)(img.getWidth(null));
		int sizey=(int)(img.getHeight(null));
		if(useOwnSize)
		{
	     sizex=ownSizeX;
	     sizey=ownSizeY;
		}
		Image img2 = resizeImage(img,sizex,sizey);
		list.put(name, img2);
	}
	
	public void ImportFromSheet(String name,SheetLoader loader,int x,int y,boolean b){
		Image img = loader.getImage(x, y);

		list.put(name, img);
	}
	
	public void ImportFromSheet(String name,SheetLoader loader,int x,int y,int sizex,int sizey){
		Image img = loader.getImage(x, y);
		Image img2 = resizeImage(img,sizex,sizey);
		list.put(name, img2);
	}
	
	
	
	public void addImage(String name, String pfad) {
		Image img = new ImageIcon(pfad).getImage();
		int sizex=(int)(img.getWidth(null));
		int sizey=(int)(img.getHeight(null));
		if(useOwnSize)
		{
	     sizex=ownSizeX;
	     sizey=ownSizeY;
		}
		Image img2 = resizeImage(img,sizex,sizey);
		list.put(name, img2);
	}
	
	public void addImage(String name, URL pfad) {
		Image img = new ImageIcon(pfad).getImage();
		
		
		//BufferedImage img2 = resizeImage(img,img.getWidth(null),img.getHeight(null));
		int sizex=img.getWidth(null);
		int sizey=img.getHeight(null);
		if(useOwnSize)
		{
	     sizex=ownSizeX;
	     sizey=ownSizeY;
		}
		Image img2 = resizeImage(img,sizex,sizey);
		
		list.put(name, (Image)img2);
	}

	public void addImage(String name, Image img) {
		list.put(name, img);
	}

	public Image getImage(String name) {
		if (list.containsKey(name))
			return list.get(name);
		else
			return null;
	}
	
	public int getImageAsInteger(String name) {
		if (integerlist.containsKey(name))
			return integerlist.get(name);
		else
			return 0;
	}
	

	public Image removeImage(String name) {
		if (list.containsKey(name))
			return list.remove(name);
		else
			return null;
	}

	public HashMap<String, Image> getList() {
		return list;
	}
	
	public HashMap<String, Integer> getIntegerList() {
		return integerlist;
	}

	public void setIntegerList(HashMap<String, Integer> list) {
		this.integerlist = list;
	}
	public void setList(HashMap<String, Image> list) {
		this.list = list;
	}
	
	
	/*public void reinit(double sizex,double sizey){
		if(sizex < 1 || sizey < 1)
		return;
		for(String n:this.list.keySet())
		{
			Image img =  this.list.get(n);
			Image img2 = resizeImage(img,(int)(img.getWidth(null)*sizex),(int)(img.getHeight(null)*sizey));
			list.put(n,img2);
		}
	}*/
	
    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, 3);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.SrcOver);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        try{
        image.flush();
        }catch(Exception e){
        			e.printStackTrace();
        }
        return bufferedImage;
    }
    
    public static BufferedImage resizeIcon(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
//        graphics2D.setComposite(AlphaComposite.SrcOver);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        try{
        image.flush();
        }catch(Exception e){
        			e.printStackTrace();
        }
        return bufferedImage;
    }
    
    
 

	public void addRezizedImage(String name, Image img, int sizex, int sizey) {
		Image img2 = resizeImage(img,sizex,sizey);
		list.put(name, img2);
	}

	
}
