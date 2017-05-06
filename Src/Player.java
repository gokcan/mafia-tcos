import java.util.*;

/*
* AchievementTracker needs some of the methods here properly function
*/

public class Player{
   private ArrayList<Achievement> locked;
   private ArrayList<Achievement> unlocked;
   public double drug;
   public double money;
   public double launderedMoney;
   
   public Player(){
      locked = new ArrayList<Achievement>();
      unlocked = new ArrayList<Achievement>();
      drug = 0;
      money = 0;
      launderedMoney = 0;
   }
   
   // creates and add an achievement in the locked list
   public void addAchievement(int ID, String description){
      locked.add(new Achievement(ID, description));
   }
   
   // returns the number of unearned achievements
   public int getLockedSize(){
      return locked.size();
   }
   
   // seachers for the achievement in the locked list, removes it and adds it to unlocked
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
   
   public double getDrug(){
      return drug;
   }
   
   public double getMoney(){
      return money;
   }
      
   public double getLaunderedMoney(){
      return launderedMoney;
   }
   
   // returns locked achievement in the given index
   public Achievement getAchievement(int i){
      return locked.get(i);
   }
   
   // not normally used, for testing purposes
   public void printAchis(){
      System.out.println("Locked:");
      for (int i = 0; i < locked.size();i++)
         System.out.println(locked.get(i).getDescription());
      System.out.println("Unlocked:");
      for (int i = 0; i < unlocked.size();i++)
         System.out.println(unlocked.get(i).getDescription());
   }
   
   // returns the IDs of unlocked achievements so they can be saved
   public ArrayList<Integer> getEarnedAchievementIDs(){
      ArrayList<Integer> i = new ArrayList<Integer>();
      for (Achievement a : unlocked)
         i.add(a.getID());
      return i;
   }
}
