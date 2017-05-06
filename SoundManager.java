import javax.sound.sampled.*;
import java.io.*;
import java.net.*;
import java.util.*;
/*
* Only works with .wav files. Use at your own peril
*/
public class SoundManager {
   
   public SoundManager(){
      clipLoaded = false;
   }
   
   private Clip clip;
   private Boolean clipLoaded;
   
   // takes the name of the .wav file to be played and opens it as a clip
   public boolean loadClip(String name){
      try {
         File f = new File(name + ".wav"); // file name goes here
         URI uri = f.toURI();
         URL url = uri.toURL();
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         
         if (clipLoaded)    // if a clip is already loaded close it
            clip.close();
         
         clip = AudioSystem.getClip();
         clip.open(audioIn);
         clipLoaded = true;
         return clipLoaded;
         
      }
      catch(Exception e){
         clipLoaded = false;
         e.printStackTrace();
         return clipLoaded;
      }
   }
   
   // if a clip it available, plays/resumes it
   public void playClip(){
      if (clipLoaded)
         clip.start();
   }
   
   // pauses the clip
   public void pauseClip(){
      if (clipLoaded)
         clip.stop();
   }
   
   // rewinds the clip to beginning
   public void resetClip(){
      if (clipLoaded)
         clip.setFramePosition(0);
   }
   
   // sets the number of times the clip will be played, -1 for an endless loop
   public void loopClip(int loopNum){
      if (loopNum >= 0)
         clip.loop(loopNum);
      else if (loopNum == -1)
         clip.loop(Clip.LOOP_CONTINUOUSLY);
   }
   
   // closes the clip
   public void closeClip(){
      if (clipLoaded){
         clipLoaded = false;
         clip.close();
      }
   }
   
}
