public class Achievement{
   // properties
   int ID;
   String description;
   
   public Achievement(int ID, String description){
      this.ID = ID;
      this.description = description;
   }
   
   public int getID(){
      return ID;
   }
   
   public String getDescription(){
      return description;
   }
}
