package sample;

import sample.Boombox;

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
    private boolean isMusicEnabled = false;
    private boolean isSoundEnabled = false;
    private int currentMusic;
    private float absoluteVolume;


    // constructor
    public SoundManager(){
        // A LineListener that invokes switchLoop() when the clip it is added to ends
        LineListener loopListener = new LineListener(){
            public void update(LineEvent event){
                if (event.getType() == LineEvent.Type.STOP)
                    switchLoop();
            }
        };

        music = new Boombox(loopListener); //passes the listener to the music object so that it may loop between tracks
        sound = new Boombox();
        musicFiles = new ArrayList<String>();
        soundFiles = new ArrayList<String>();
        musicFiles.add("Track1");
        musicFiles.add("Track2");
        musicFiles.add("Track3");
        soundFiles.add("Track3");
        currentMusic = 0;

        // add file names to appropriate ArrayLists
        // if you are adding to musicFiles, ordering matters
     /*musicFiles.add("Track3");
      musicFiles.add("Track4");
      musicFiles.add("Track5");
      soundFiles.add("Track3");*/
        absoluteVolume = 1f;
    }

    // resumes playing the music in loop, does nothing if there is no clip
    public void playMusic(){
        music.loadClip(musicFiles.get(0));
        music.playClip();
        isMusicEnabled = true;
    }

    // pauses music
    public void pauseMusic(){
        music.pauseClip();
        isMusicEnabled = false;
    }

    // pauses sound
    public void pauseSound(){
        sound.pauseClip();
        isSoundEnabled = false;
    }

    // closes the old clip and loads a new one, if music is enabled, starts playing it immediately
    public void switchMusic(int i){
        music.loadClip(musicFiles.get(i));
        currentMusic = i;
        if (isMusicEnabled)
            music.playClip();
    }

    // switches to the next track on the list
    public void switchLoop(){
        if (currentMusic == musicFiles.size() - 1){
            switchMusic(0);
            music.adjustClipVolume(absoluteVolume);
        }
        else
        {
            switchMusic(currentMusic + 1);
            music.adjustClipVolume(absoluteVolume);
        }
    }

    // takes and integer parameter, loads the target clip and plays it immediately and only once
    public void playSound(int i){
        sound.loadClip(soundFiles.get(i));
        sound.playClip();
        music.adjustClipVolume(absoluteVolume);
        isSoundEnabled = true;
    }

    public boolean getMusicStatus()
    {
        return isMusicEnabled;
    }

    public boolean getSoundStatus()
    {
        return isSoundEnabled;
    }

    // feed a single precision floating point number between 0 and 100 inclusive
    public void adjustVolume(float vol){
        if (vol <= 100 && vol >= 0) {
            absoluteVolume = vol / 100;
        }
        music.adjustClipVolume(absoluteVolume);
        sound.adjustClipVolume(absoluteVolume);
    }
   /*
    * you can use this method to test the SoundManager
    *
   public static void main(String[] args){
      Scanner scan = new Scanner(System.in);
      SoundManager speaker = new SoundManager();

      speaker.switchMusic(0);
      speaker.playMusic();


      int vol = 0;

      do{
         System.out.println("Choose volume:");
         vol = scan.nextInt();
         speaker.adjustVolume(vol);
      } while(vol >= 0 && vol <= 100);
   }*/
}