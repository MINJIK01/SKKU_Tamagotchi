

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Bgm extends Thread {
	
	@Override
	public void run() {
		
		// play music
		while(true)
		{
			try {
			File a = new File("img\\song.wav");
			AudioInputStream b = AudioSystem.getAudioInputStream(a);
			Clip c = AudioSystem.getClip();
		
			c.open(b);
			c.start();
		
			Thread.sleep(c.getMicrosecondLength()/1000);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
