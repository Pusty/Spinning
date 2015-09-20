package me.game.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sound implements Runnable {

	AudioClip mainclip;
	boolean loop;
	public Sound(URL url,Applet a,boolean loop){
		this.loop=loop;
		mainclip = a.newAudioClip(url);
/*		AudioInputStream audioInputStream = null;
		Clip clip = null;

		try {
			audioInputStream = AudioSystem.getAudioInputStream(url);
			if (audioInputStream != null) {
				AudioFormat format = audioInputStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				try {
					clip = (Clip) AudioSystem.getLine(info);
					// clip.addLineListener(this);
					clip.open(audioInputStream);
					mainclip = clip;
				} catch (Exception e) {
                     e.printStackTrace();
				}

			}
		} catch (Exception e) {
            e.printStackTrace();
		}*/
	}
	Thread thread;
	public Thread getThis(){return thread;}
	public synchronized  void start(){
		thread = new Thread(this);
		thread.start();
	}
	public synchronized  void run() {
		if(loop)
		mainclip.loop();
		else
			mainclip.play();
	}
	
	public AudioClip getClip(){
		return mainclip;

	}

}
