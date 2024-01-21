/**a linked list container with double-linked nodes - conserves memory (no buffer space)
   d oberle 10/2021
*/
public class dLinkedList<anyType> implements ListInterface<anyType>
{
/** Data field: a reference to the first node in the list */
   private dListNode<anyType> head;
   /** Data field: a reference to the last node in the list */
   private dListNode<anyType> tail;
   /** Data field: a reference to the size of the list */
   private int size = 0;

/**
 * No arg constructor initializes the dLinkedList to an empty list.
 *
 */
   public dLinkedList()
   {
      head = null;
   }
   
   private dListNode getNodeFromFront(int spaces)
   {
      dListNode<anyType> current = head;
      for(int i = 0; i < spaces; i++)
         current = current.getNext();
      return current;
   }
   
   private dListNode getNodeFromBack(int spaces)
   {
      dListNode<anyType> current = tail;
      for(int i = 0; i < (size-1-spaces); i++)
         current = current.getPrev();
      return current;
   }

/**
 * Adds a new element to the front of the LinkedList.
 *
 * @param  x a non-null Object.
 */
   public void addFirst(anyType x)//O(1)		
   {
   //WRITE THIS METHOD***********************************************
      if(head==null)
      {
         head = new dListNode(x, null, null);
         tail = head;
         size++;
      }
      else
      {
         dListNode temp = new dListNode(x, null, head);
         head.setPrev(temp);
         head = temp;
         size++;
      }
   }

/**
 * Adds a new element to the end of the LinkedList.
 *
 * @param  x a non-null Object.
 */
   public void addLast(anyType x)//O(1)
   {
      //WRITE THIS METHOD***********************************************
      if(head==null)
      {
         head = new dListNode(x, null, null);
         tail = head;
         size++;
      }
      else
      {
         dListNode temp = new dListNode(x, tail, null);
         tail.setNext(temp);
         tail = temp;
         size++;
      }      
   }

/**
 * Retrieve the first node in the LinkedList if the head is not null
 *
 * @return the value of the first node in the List, or null if the head is null
 */
   public anyType getFirst()
   {
      if (head==null)							//if list is empty
         return null;
      return head.getValue();
   }

/**
 * Retrieve the last node in the LinkedList if the head is not null
 *
 * @return the value of the last node in the List, or null if the head is null
 */
   public anyType getLast()
   {
      if (head==null)							//if list is empty
         return null; 
      return tail.getValue();
   }

/**
 * Remove the first node in the LinkedList and return its value if the head is not null
 *
 * @return the value of the node removed from the List, or null if the LinkedList is empty
 */
   public anyType removeFirst()//O(1)
   {
      //WRITE THIS METHOD***********************************************
      anyType ret = head.getValue();
      if (head==null)
      {   
         return null;
      }
      else if(size == 1)
      {
         head = null;
         size--;
         return ret;
      }
      head = head.getNext();
      head.setPrev(null);
      size--;
      return ret;					
   }
//****************************************************************

/**
 * Remove the last element of the list and return its value if the list is not empty
 *
 * @return the value of the element removed, or null if the list is empty
 */
   public anyType removeLast()//O(1)
   {
      anyType ret = head.getValue();
      if (head==null)
      {
         return null;
      }
      else if(size == 1)
      {
         head = null;
         size--;
         return ret;
      }
      tail = tail.getPrev();
      tail.setNext(null);
      size--;
      return ret;		
   }

/**
 * Returns the number of logical elements stored in the LinkedList.
 *
 * @return the size of the LinkedList.
 */
   public int size()//O(1)
   {
      //WRITE THIS METHOD***********************************************
      return size;				
   }

/**
 * Finds the Object that resides at a given index
 *
 * @param index a value such that index is greater or equal to 0 and index is less than size()
 * @return the value stored in the node at the given index, or null if the list is empty or invalid index
 */
   public anyType get(int index)//O(1)
   {
      //WRITE THIS METHOD***********************************************
      if(index < 0 || index > size-1)
      {  
         return null;
      } 
      dListNode<anyType> ret = null;
      if(head == null)
         return null;
      else if(index < size/2)
         ret = this.getNodeFromFront(index);
      else if(index > size/2)
         ret = this.getNodeFromBack(index);
      return ret.getValue();
   }	

/**
 * Change the Object that resides at a given index to a new value
 *
 * @param index a value such that index is greater or equal to 0 and index is less than size()
 * @param x a non-null Object
 * @return the old value stored in the node at the given index, or null if the list is empty or invalid index
 */
   public anyType set(int index, anyType x)//O(1)
   {
      //WRITE THIS METHOD***********************************************
      if(index < 0 || index > size-1)
      {  
         return null;
      } 
      dListNode<anyType> ret = null;
      if(head == null)
         return null;
      else if(index <= size/2)
      {  
         dListNode<anyType> curr = this.getNodeFromFront(index);
         ret = curr;
         curr.setValue(x);
      }
      else if(index >= size/2)
      {
         dListNode<anyType> curr = this.getNodeFromBack(index);
         ret = curr;
         curr.setValue(x);
      }
      return ret.getValue();
   }	
//****************************************************************

/**
 * Add a new element at the end of the list
 *
 * @param x a non-null Object
 * @return true
 */
   public boolean add(anyType x)
   {
      addLast(x);
      return true;			
   }	

/**
 * Add a new element at a given index
 *
 * @param index a value such that index is greater or equal to 0 and index is less than size()
 * @param x a non-null Object
 * @return if the element was added successfully, false if the index is invalid
 */
   public boolean add(int index, anyType x)
   {
   
      if(index < 0 || index > size-1)
      {  
         return false;
      } 
      if(head == null || index == 0)
      {
         this.addFirst(x);
      }
      else if(index <= size/2)
      {
         dListNode<anyType> curr = this.getNodeFromFront(index);
         dListNode temp = new dListNode(x, curr.getPrev(), curr);
         temp.getPrev().setNext(temp);
         curr.setPrev(temp);
         size++;
      }
      else if(index >= size/2)
      {
         dListNode<anyType> curr = this.getNodeFromBack(index);
         dListNode temp = new dListNode(x, curr.getPrev(), curr);
         temp.getPrev().setNext(temp);
         curr.setPrev(temp);
         size++;
      }
      return true;			
   }	

/**
 * Remove a node that resides at a given index and return its value
 *
 * @param index a value such that index is greater or equal to 0 and index is less than size()
 * @return the value of the element removed, or null if the list is empty or invalid index
 */
   public anyType remove(int index)		
   {
      //WRITE THIS METHOD*********************************************** 
      
      if(index < 0 || index > size-1)
      {  
         return null;
      } 
      dListNode<anyType> ret = null;
      if(head == null)
      {
         return null;
      }
      else if(index == 0)
         return this.removeFirst();
      else if(index == size-1)
      { 
         return this.removeLast();
      }
      else if(index <= size/2)
      {
         dListNode<anyType> curr = this.getNodeFromFront(index);
         ret = curr;
         curr.getPrev().setNext(curr.getNext());
         curr.getNext().setPrev(curr.getPrev());
         size--;
      }
      else if(index >= size/2)
      {
         dListNode<anyType> curr = this.getNodeFromFront(index);
         ret = curr;
         curr.getPrev().setNext(curr.getNext());
         curr.getNext().setPrev(curr.getPrev());
         size--;
      }
      return ret.getValue();
   }	
	

/**
 * Returns a String of all the elements in the List in the form [a0, a1, a2, . . . , an-1]
 *
 * @return String of all the list elements separated by a comma
 */
   public String toString()
   {
      String ans = "[";									//start with left bookend						
      dListNode<anyType> current =  head;
      while(current != null)
      {
         ans += current.getValue().toString();
         current = current.getNext();
         if (current != null)							//don't add comma after the last element
            ans += ",";
      }
      ans += "]";											//end with right bookend
      return ans;
   }

/**
 * Finds if the dLinkedList is empty (true) or contains items (false).
 *
 * @return whether or not the dLinkedList is empty.
 */
   public boolean isEmpty()
   {
      if (head == null)
         return true;
      return false;
   }

}