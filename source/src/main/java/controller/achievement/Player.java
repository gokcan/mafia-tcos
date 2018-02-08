import java.util.*;

/*
* AchievementTracker needs some of the methods and properties here to function properly
*/

public class Player{
   private ArrayList<Achievement> locked;
   private ArrayList<Achievement> unlocked;
   
   public Player(){
      locked = new ArrayList<Achievement>();
      unlocked = new ArrayList<Achievement>();
   }
   
   // creates and add an achievement in the locked list
   public void addAchievement(int ID, String description){
      locked.add(new Achievement(ID, description));
   }
   
   // returns the number of unearned achievements
   public int getLockedSize(){
      return locked.size();
   }
   
   // search for the achievement in the locked list, removes it and adds it to unlocked
   public void unlockAchievement(int ID){
      Achievement a = null;
      for (int i = 0; i < locked.size() && a == null;i++){
         if (locked.get(i).getID() == ID){
            a = locked.get(i);
            locked.remove(i);
            unlocked.add(a);
         }
      }
   }
   
   // returns locked achievement in the given index
   public Achievement getAchievement(int i){
      return locked.get(i);
   }
   
   // returns the IDs of unlocked achievements so they can be saved
   public ArrayList<Integer> getEarnedAchievementIDs(){
      ArrayList<Integer> i = new ArrayList<Integer>();
      for (Achievement a : unlocked)
         i.add(a.getID());
      return i;
   }
}
