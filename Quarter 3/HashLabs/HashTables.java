import java.util.*;
import java.io.*;

public class HashTables
{

   public static final int tableSize = 10000;
   public static final String[] vowels = {"a", "e", "i", "o", "u"};
   public static final String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w","x", "y", "z"};
    
   
   public static void main(String[] args) throws IOException
   {    
   //       System.setOut(new PrintStream(new FileOutputStream("data.txt")));
   //          for(int i = 0; i < tableSize; i++)
   //          {
   //             System.out.println(randomDataItem().toString());
   //          } 
      Scanner input = new Scanner(System.in);
      LinkedList<dataItem>[] hashTable = readFile("data.txt");
      
      System.out.println("What word do you want to search for?");
      String word = input.nextLine();
      dataItem temp = new dataItem(0, word);
      int index = Math.abs(temp.hashCode()) % tableSize;
      if(hashTable[index] != null)
      {
         for(int i = 0; i < hashTable[index].size(); i++)
         {
            if(hashTable[index].get(i).getWord().equals(word))
            {
               System.out.println("The number associated with this word is " + hashTable[index].get(i).getNum());
               return;
            }
         }
      }
      System.out.println("The word has not been found.");
   }
   
   public static LinkedList<dataItem>[] readFile(String filename) throws IOException
   {
      LinkedList<dataItem>[] hashTable = new LinkedList[tableSize];
      Scanner input = new Scanner(new FileReader(filename));
      while(input.hasNextLine())
      {
         String line = input.nextLine();
         String[] splitted = line.split(" ");
         dataItem temp = new dataItem(Integer.parseInt(splitted[0]), splitted[1]);
         int hashCode = temp.hashCode();
         int index = Math.abs(temp.hashCode()) % tableSize;
         
         if(hashTable[index] == null)
         {
            hashTable[index] = new LinkedList<dataItem>();
            hashTable[index].add(temp);
         }
         else
            hashTable[index].add(temp);
      }
      input.close();
      return hashTable;
   }
   
   public static dataItem randomDataItem()
   {
      int length = (int)(Math.random()*(8-3+1)+3);
      String word = "";
      for(int i = 0; i < length; i++)
      {
         if(i % 2 == 0)//even
            word += consonants[(int)(Math.random()*(20-0+1)+0)];
         else if(i % 2 == 1)//odd
            word += vowels[(int)(Math.random()*(4-0+1)+0)];
      } 
      int num = (int)(Math.random()*(9999-0+1)+0);
      
      return new dataItem(num, word);
   }
   

}
//(int)(Math.random()*(high-low+1)+low);