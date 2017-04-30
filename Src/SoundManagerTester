import java.util.*;
/*
* Creates an ArrayList to hold file names, instantiates the SoundManager,
* prints a menu asking user what to do next
*/

public class SoundManagerTester{
   public static void main(String args[]){
      Scanner scan = new Scanner(System.in);
      int input = 0;
      SoundManager box = new SoundManager();
      ArrayList<String> tracks = new ArrayList<String>();
      tracks.add("Track1");
      tracks.add("Track2");
      tracks.add("Track3");
      boolean greenLight = false;
      
      do {
         System.out.println("1: Change tracks\n2: Start\n3: Pause\n4: Replay\n5: Loop\n6: Exit");
         input = scan.nextInt();
         
         if (input == 1){
            System.out.print("Number of tracks available: " + tracks.size() + "\n Choose one: ");
            int choice = scan.nextInt();
            if (choice <= tracks.size() && choice > 0){
               if (box.loadClip(tracks.get(choice - 1))){
                  System.out.println("Track loaded successfully");
                  greenLight = true;
               }
               else {
                  System.out.println("Track not found");
                  greenLight = false;
               }
            }
            else
               System.out.println("Out of bounds");
         }
         
         if (input == 2 ){
            if (greenLight)
               box.playClip();
            else
               System.out.println("Load a track first");
         }
         
         if (input == 3){
            if (greenLight)
               box.pauseClip();
            else
               System.out.println("Load a track first");
         }
         
         if (input == 4){
            if (greenLight)
               box.resetClip();
            else
               System.out.println("Load a track first");
         }
         
         if (input == 5){
            if (greenLight){
               System.out.println("Enter the number of loops, -1 for infinite loops");
               int num = scan.nextInt();
               if (num < -1)
                  System.out.println("Not a valid choice, better luck next time");
               else
                  box.loopClip(num);
            }
            else
               System.out.println("Load a track first");
         }
         
         
      } while (input != 6);
      
   }
}
