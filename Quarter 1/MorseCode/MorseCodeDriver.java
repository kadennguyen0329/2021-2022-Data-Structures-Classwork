import java.io.*;
import java.util.*;

public class MorseCodeDriver
{
   public static Map <String, String> toMorse = new HashMap();
   public static Map <String, String> toEnglish = new HashMap();
   public static Scanner input = new Scanner(System.in);
       
   public static void fillMaps(){
      MorseCode code = new MorseCode();
      for(int i = 0; i < code.size(); i++)
      {
         toMorse.put(code.getEnglish(i), code.getMorse(i));
         toEnglish.put(code.getMorse(i), code.getEnglish(i));
      }
   }
   public static void main(String[]arg)throws IOException
   {
      fillMaps();
      System.out.println("Would you like to decode (press d) or encode (press e)?");
      String ans = input.nextLine().toLowerCase();
      while(!ans.equals("d") && !ans.equals("e"))
      {
         System.out.println("INVALID ANSWER. TRY AGAIN. Would you like to decode (press d) or encode (press e)?");
         ans = input.nextLine().toLowerCase();
      }
      if(ans.equals("d"))
         decode();
      else if(ans.equals("e"))
         encode();
   }
   
   public static void decode()throws IOException //morse to english
   {
      String[] lines = readFile("code.txt");
      for(int i = 0; i < lines.length; i++){
         String[] text = lines[i].split("/");
         for(int j = 0; j < text.length; j++){
            System.out.print(codeWordtoEnglish(text[j].trim()) + " ");
         }
         System.out.println();
      }
   }
   
   public static void encode()throws IOException  //english to morse
   {
      String[] lines = readFile("english.txt");
      for(int i = 0; i < lines.length; i++){
         String[] text = lines[i].trim().split(" ");
         for(int j = 0; j < text.length; j++){
            if(j != text.length-1)
               System.out.print(englishWordtoMorse(text[j]) + "/");
            else
               System.out.print(englishWordtoMorse(text[j]));
         }
         System.out.println();
      }
   }
   
   public static String englishWordtoMorse(String w){
      char[] letters = w.toUpperCase().toCharArray();
      String translation = "";
      for(int i = 0; i < letters.length; i++){
         translation += " " + toMorse.get(""+letters[i]);
      }
      return translation + " ";
   }
   
   public static String codeWordtoEnglish(String w){
      String[] letters = w.split(" ");
      String translation = "";
      for(int i = 0; i < letters.length; i++){
         translation += "" + toEnglish.get(""+letters[i]);
      }
      return translation;
   }
   
   public static int getFileSize(String fileName)throws IOException{
      Scanner input = new Scanner(new FileReader(fileName));
      int size=0;
      while (input.hasNextLine())
      {
         size++;										
         input.nextLine();							
      }
      input.close();									
      return size;
   }
   
   public static String[] readFile(String fileName)throws IOException{
      int size = getFileSize(fileName);		
      String[] list = new String[size];		
      Scanner input = new Scanner(new FileReader(fileName));
      int i=0;											
      String line;	
      while (input.hasNextLine())				
      {
         line=input.nextLine();					
         list[i]= line;								
         i++;											      
      }
      input.close();	
      return list;					
   }
}
      
