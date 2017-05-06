import javax.swing.Timer;
import java.awt.event.*;

/*
 * Example code to handle time based events such as updating the amount of drugs in the player's posession
 * In the GameEngine, updateDrug() will use Player object's get() and set() methods
 * */

public class Heisenberg{
   
   private Timer clk;
   private long drug;
   private long drugRate;
   private static final int DELAY = 1000;
   private static final int DELAY_IN_SECONDS= 2; // change this to set timer intervals
   
   public Heisenberg(){
      drug = 500;
      drugRate = 25;
      clk = new Timer(DELAY * DELAY_IN_SECONDS,new ClockListener());
      clk.start();
   }
   
   
   private void updateDrug(){
      drug += drugRate;
   }
   
   public void printDrug(){
      System.out.println("Grams of Blue Sky in the stash: " + drug);
   }
   
   private class ClockListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         updateDrug();
         printDrug();
      }
   }
   
   public static void main(String[] args){
      Heisenberg danger = new Heisenberg(); // he is the danger
      danger.printDrug();
      
      // an infinite loop to show that time based operation works even when the program is busy
      while(true){
      }
   }
}
