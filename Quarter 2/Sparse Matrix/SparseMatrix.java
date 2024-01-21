import java.util.*;

public class SparseMatrix<anyType> implements Matrixable<anyType>
{
   private LinkedList<Cell<anyType>> list;
   private int numRows, numCols;
   
   public SparseMatrix(int r, int c)
   {
      numRows = r;
      numCols = c;
      list = new LinkedList();
   }
   public anyType get(int r, int c)
   {
      anyType temp = null;
      for(int i = 0; i < list.size(); i++)
      {
         if(r == list.get(i).getRow() && c == list.get(i).getCol())
         {
            temp = list.get(i).getElement();
            break;
         }
      }
      return temp;
   }		
   	
   public anyType set(int r, int c, anyType x)
   {
      anyType old = null;
      for(int i = 0; i < list.size(); i++)
      {
         if(r == list.get(i).getRow() && c == list.get(i).getCol())
         {
            old = list.get(i).getElement();
            list.set(i, new Cell(x, r, c));
            break;
         }
      }
   
      return old;
   } 
      
   public void add(int r, int c, anyType x)
   {
      if(list.size() == 0)
      {
         list.add(new Cell(x, r, c));      
      }
      else
      {
         int newKey = (r * numCols) + c;
         for(int i = 0; i < list.size(); i++)
         {
            int tempKey = (list.get(i).getRow() * numCols) + list.get(i).getCol();
            if(tempKey > newKey)
            {
               list.add(i, new Cell(x, r, c));
               return;
            }
         }        
         list.add(new Cell(x, r, c)); 
      }     
   }
   
   public anyType remove(int r, int c)
   {
      anyType old = null;
      for(int i = 0; i < list.size(); i++)
      {
         if(r == list.get(i).getRow() && c == list.get(i).getCol())
         {
            old = list.get(i).getElement();
            list.remove(i);
         }
      }
      return old;
   }
   
   public String toString()
   {
      String array = "";
      for(int r = 0; r < numRows; r++)
      {
         for(int c = 0; c < numCols; c++)
         {
            String added = "";
            anyType curr = this.get(r, c);
            if(curr != null)
               added = curr + " ";
            else
               added = "- ";
            array+=added;
         }
         array += "\n";
      }
      return array;
   }
   
   public int size()
   {
      return list.size();
   }
   
   public int numRows()	
   {
      return numRows;
   }
   
   public int numColumns()
   {
      return numCols;
   }
}