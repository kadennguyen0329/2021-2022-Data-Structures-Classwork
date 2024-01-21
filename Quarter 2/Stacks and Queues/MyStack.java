import java.util.*;

public class MyStack<anyType> implements Stackable<anyType>
{
   private List<anyType> list;
   
   public MyStack()
   {
      list = new ArrayList<anyType>();
   }
   
 /** add x to the top of the stack.
  * @param  x a non-null anyType object.
  */
   public void push(anyType x)
   {
      list.add(x);
   }

 /** removes and returns the element at the top of a non-empty stack.
  * @return the value that was removed; returns null if the stack is empty.
  */
   public anyType pop()
   {
      if(list.size()==0)
         return null;
      return list.remove(list.size()-1);
   }
        				                 
 /** returns the element at the top of a non-empty stack.
  * @return the element at the top of the stack; returns null if the stack is empty.
  */                                     
   public anyType peek()
   {
      if(list.size()==0)
         return null;
      return list.get(list.size()-1);
   }
                         
 /** lets the client know if the stack has any elements or is empty.
  * @return true if the stack is empty; returns false if the stack has elements stored in it.
  */                                     
   public boolean isEmpty()
   {
      return list.isEmpty();
   }
   
 /** returns the number of logical elements stored in the stack.
 *   @return the number of elements added into the stack.
 */    
   public int size()
   {
      return list.size();
   }
   
   public String toString()
   {
      String ans = "";
      for(int i = list.size()-1; i >= 0; i--)
      {
         if(i == 0)
            ans += list.get(i);
         else
            ans += list.get(i) + ", "; 
      }
      return ans;
   }  
}