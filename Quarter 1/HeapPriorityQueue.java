//Rev Dr. D. R. Oberle  Feb 2015
import java.util.*;

public class HeapPriorityQueue implements PriorityQueue
{
   private static final int DFLT_CAPACITY = 1024;
   private Comparable [] items;		//index 0 will go unused
   private int numItems;

   public HeapPriorityQueue(int initialCapacity)
   {
      items = new Comparable[initialCapacity + 1];
      numItems = 0;
   }

   public HeapPriorityQueue()
   {
      this(DFLT_CAPACITY);
   }

   public boolean isEmpty()
   {
      return (numItems == 0);
   }

   public Comparable peek()
   {
      if (numItems == 0)
      {
         throw new NoSuchElementException();
      }
   
      return items[1];
   }
   
   private void reheapDown()
   { 
      int i = 1; //index of moving node
      int rightChildIndex = i*2+1;
      int leftChildIndex = i*2;
      boolean hasRightChild = (items[rightChildIndex] != null);
      boolean hasLeftChild = (items[leftChildIndex] != null);
      
      while(hasRightChild || hasLeftChild){ //while there is a child
      
         if(hasRightChild && hasLeftChild){ //if there are 2 children
            if(lowerPriority(items[leftChildIndex], items[i]) && lowerPriority(items[rightChildIndex], items[i])) //if parent is higher priority than both children
               break;
            else if(lowerPriority(items[i], items[leftChildIndex]) && lowerPriority(items[rightChildIndex], items[leftChildIndex])){ //if left child is higher priority than parent and right
               swap(items, i, leftChildIndex);
               i = i*2;
            }
            else if(lowerPriority(items[i], items[rightChildIndex]) && lowerPriority(items[leftChildIndex], items[rightChildIndex])){ //if right child is higher priority than parent and left
               swap(items, i, rightChildIndex);
               i = i*2+1;
            }
         }
         else if(hasLeftChild && !hasRightChild){ //if there is only 1 child which is left
            if(lowerPriority(items[leftChildIndex], items[i])) //if the parent is higher priority
               break;
            else if(lowerPriority(items[i], items[leftChildIndex])){ //if the child is higher priority than parent
               swap(items, i, leftChildIndex);
               i = i*2;
            }
         }
         
         //update variables
         rightChildIndex = i*2+1;
         leftChildIndex = i*2;
         hasRightChild = (items[rightChildIndex] != null);
         hasLeftChild = (items[leftChildIndex] != null);
      }
   }

   public Comparable remove()
   {
      if (numItems == 0)
      {
         throw new NoSuchElementException();
      }
   
      Comparable min = items[1];
      items[1] = items[numItems];
      items[numItems] = null;
      numItems--;
      reheapDown();
      return min;
   }

   private static void swap(Comparable [] items, int a, int b)
   {
      Comparable temp = items[a];
      items[a] = items[b];
      items[b] = temp;
   }

   private void reheapUp()
   {
      int i = numItems;
      int parentIndex = i/2;
      boolean hasParent = (items[parentIndex] != null);
      while(hasParent){
         if(lowerPriority(items[i], items[parentIndex])) //if parent node is higher priority than child
            break;
         else if(lowerPriority(items[parentIndex], items[i])){ //if child is higher priorty than parent
            swap(items, i, parentIndex);
            i = i/2;
         }
         //update variables
         parentIndex = i/2;
         hasParent = (items[parentIndex] != null);
      }
   }

   public boolean add(Comparable obj)
   {
      numItems++;
      if (numItems >= items.length)
         doubleCapacity();
      items[numItems] = obj;
      reheapUp();
      return true;
   }

   private static boolean lowerPriority(Comparable obj1, Comparable obj2)
   {	//we will consider that low value == high priority
      return (obj1.compareTo(obj2) > 0);
   }

   public String toString()
   {
      String ans = "[";
      for (int i = 1; i <= numItems; i++)
      {
         ans += items[i];
         if(i <= numItems-1)
            ans += ", ";   
      }
      return ans+"]";
   }

   private void doubleCapacity()
   {
      Comparable tempItems[] = new Comparable[2 * items.length - 1];
      for (int i = 0; i <= numItems; i++)
         tempItems[i] = items[i];
      items = tempItems;
   }
}

