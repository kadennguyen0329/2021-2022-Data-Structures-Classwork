import java.util.*;

public class TrafficSimulator
{
   public static Scanner input = new Scanner(System.in);
   public static MyQueue<String> mainQueue = new MyQueue<String>();
   public static MyQueue<String> mapQueue = new MyQueue<String>();
   public static int mainTime, mainProb, mapTime, mapProb, cycles = 0;
   public static boolean mainLight = true;
   public static boolean mapLight = false;
   
   public static void main(String[] args) //time increment thingy, 
   {  
      getInfo();
      for(int i = 0; i < cycles; i++)
      {
         for(int main = 0; main < mainTime; main++)
         {
            displayLights();
            if(random(mainProb))
               mainQueue.add(" " + randomChar() + " ");
            if(random(mapProb))
               mapQueue.add(" " + randomChar() + " ");
            if(!mainQueue.isEmpty())
               mainQueue.remove();
         }
         lightChange();
         for(int map = 0; map < mapTime; map++)
         {
         displayLights();
            if(random(mapProb))
               mapQueue.add(" " + randomChar() + " ");
            if(random(mainProb))
               mainQueue.add(" " + randomChar() + " ");
            if(!mapQueue.isEmpty())
               mapQueue.remove();
         }
         lightChange();
      }
   }
   
   public static void getInfo()
   {
      System.out.println("How long will Main Street's light stay green?");
      mainTime = input.nextInt();
      System.out.println("What is the probability a car will show up on Main Street per second?");
      mainProb = input.nextInt();
      System.out.println("How long will Maple Avenue's light stay green?");
      mapTime = input.nextInt();
      System.out.println("What is the probability a car will show up on Main Avenue per second?");
      mapProb = input.nextInt();
      System.out.println("How many light cycles do you want to simulate?");
      cycles = input.nextInt();
   }
   
   public static void displayLights()
   {
      if(mainLight == true)
         System.out.println("Main Street (GREEN): " + mainQueue.toString());
      else if(mainLight == false)
         System.out.println("Main Street (RED): " + mainQueue.toString());
      if(mapLight == true)
         System.out.println("Maple Avenue (GREEN): " + mapQueue.toString());
      else if(mapLight == false)
         System.out.println("Maple Avenue (RED): " + mapQueue.toString());
   }
   
   public static void lightChange()
   {
      mainLight = !mainLight;
      mapLight = !mapLight;
      System.out.println("*******Light Change********");
   }
   
   public static boolean random(int probability)
   {
      int num = (int)(Math.random() * 101);
      if(probability > num)
         return true;
      return false;
   }
   
   public static char randomChar()
   {
      Random r = new Random();
      return (char)(r.nextInt(26) + 'a');
   }
}