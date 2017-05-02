import javax.sound.sampled.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class SoundManager {
	
	// properties
	private ArrayList<String> musicFiles;
	private ArrayList<String> soundFiles;
	private Boombox music;
	private Boombox sound;
	private boolean isMusicEnabled;
	
	public SoundManager(){
		music = new Boombox();
		sound = new Boombox();
		musicFiles = new ArrayList<String>();
		soundFiles = new ArrayList<String>();
      
		musicFiles.add("Track1");
		musicFiles.add("Track2");
		musicFiles.add("Track3");
		soundFiles.add("Track3");
      
	}
	
	// resumes playing the music in loop, does nothing if there is no clip
	public void playMusic(){
			music.loopClip(-1);
			music.playClip();
			isMusicEnabled = true;
	}
	
	// pauses music in it's current position
	public void pauseMusic(){
			music.pauseClip();
		
	}
	
	// closes the old clip and loads a new one, if music is enabled, starts playing it immediately
	public void switchMusic(int i){
		music.loadClip(musicFiles.get(i));
		if (isMusicEnabled)
			this.playMusic();
	}
	
	// takes and integer parameter, loads the target clip and plays it immediately and only once
	public void playSound(int i){
		sound.loadClip(soundFiles.get(i));
		sound.playClip();
	}
	
	
}
