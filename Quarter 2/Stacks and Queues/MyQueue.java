import java.util.*;

public class MyQueue<anyType> implements Queueable<anyType>
{
   private List<anyType> list;
   
   public MyQueue()
   {
      list = new LinkedList<anyType>();
   }
   
   public boolean isEmpty()
   {
      if(list.size() == 0)
         return true;
      return false;
   }
      
   public void add(anyType x)
   {
      list.add(x);
   }
      
   public anyType remove()
   {
      if(list.size() == 0)
         return null;
      //for(int i = 0; i < 
      return list.remove(0);
   } 
     
   public anyType peek()
   {
      return list.get(0);
   }
   
   public String toString()
   {
      String ans = "";
      for(int i = 0; i < list.size(); i++)
      {
            ans += list.get(i);
      }
      return ans;
   }
}