package me.game.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;

public class SoundLoader {

	 HashMap<String, Sound> list;
     Applet applet;
	public SoundLoader(Applet a) {
		applet = a;
		list = new HashMap<String, Sound>();
	}

	public void addSound(String name, URL url,boolean l) {
		list.put(name, new Sound(url,applet,l));
	}
	
	
	public AudioClip getClip(String name){
		return list.get(name).getClip();
	}
	
	public Sound getSound(String name){
		return list.get(name);
	}
	
	public synchronized  void playClip(String name){
	     if(list.get(name) != null){
	    	 list.get(name).start();
	   
	     }
	}

}
