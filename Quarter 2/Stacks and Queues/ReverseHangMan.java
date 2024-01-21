import java.util.*;
public class ReverseHangMan
{
   public static int score, index = 0;//index keeps track of index of revealed letters
   public static MyStack<String> puzzleStack;//stack for word
   public static MyStack<Integer> scoreStack = new MyStack<Integer>();//stack for score according to letter
   public static String[] revealed;//array of revealed letters
   public static Scanner input = new Scanner(System.in);
   
   public static void main(String[] args)
   {
      createVariables();
      for(int i = 0; i<revealed.length-1; i++)//playGame
         turn();
      System.out.println();
      System.out.println("The word is revealed!");
      displayInfo();
   }
   
   public static MyStack<String> toStack(String word)//word to stack
   {
      MyStack<String> ans = new MyStack();  
      for(int i=0; i<word.length(); i++)
         ans.push("" + word.charAt(i));
      return ans;
   }
   
   public static void createVariables()//creates all global variables
   {
      System.out.print("Enter a word for your opponent to guess: ");
      String puzzle = input.nextLine().toLowerCase();
      puzzleStack = toStack(puzzle);//word to stack
      revealed = new String[puzzleStack.size()];//array for revealed characters
      index = revealed.length-1;//index is at last index since letters are revealed backwards
      for(int i = 0; i < puzzleStack.size()-1; i++)
         revealed[i] = "_";
      revealed[index] = puzzleStack.pop();//last letter is automatically given, pop it and reveal it 
      for(int i = puzzleStack.size(); i > 0; i--)//scores for each letter is added
         scoreStack.push(i);
   }
   
   public static void displayInfo()
   {
      System.out.println("Score: " + score);
      for(String x: revealed)
         System.out.print(x + " ");
      System.out.println();
   }
   
   public static void turn()
   {
      index--;//every turn is a new letter
      displayInfo();
      System.out.println("Guess a letter: ");
      String guess = input.nextLine().toLowerCase();
      if(guess.equals(puzzleStack.peek()))//if its right
      {
         System.out.println("Correct! You get " + (revealed.length-scoreStack.peek()) + " point(s).");
         revealed[index] = puzzleStack.pop();//reveal letter and pop it out
         score += revealed.length-scoreStack.pop();//add correct value
      }
      else//if its wrong
      {
         System.out.println("Wrong! You lose " + scoreStack.peek() + " points.");
         revealed[index] = puzzleStack.pop();//reveal letter and pop it out
         score -= scoreStack.pop();//subtract correct score 
      }
   }
}