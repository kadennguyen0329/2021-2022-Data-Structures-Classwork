import java.io.*;
/**a binary-search-tree container d oberle 10/2021  */  
public class Tree
{
/** Data field: a reference to the first node of the tree. */
   private TreeNode myRoot;

/** No arg constructor initializes and empty tree. */   
   public Tree()
   {
      myRoot = null;
   }
   
/**Adds a new element to the tree such that the tree is still an ordered Binary Search Tree.
 * @param  x a non-null Comparable Object.
 */   
   public void add(Comparable x)
   {
      myRoot = addHelper(myRoot, x);
   }
  
/**Helper method for add(x).
 * @param   root is the root of a tree (or subtree for recursive calls). 
 * @param   x a non-null Comparable Object.   
 * @return  the root of the ordered binary search tree after x has been added.
 */    
   private TreeNode addHelper(TreeNode root, Comparable x)
   {
   //************COMPLETE THIS METHOD*****************************
      if(root == null)
      {
         root = new TreeNode(x, null, null);
         return root;
      }
      if(x.compareTo((Comparable)root.getValue()) < 0) //less than
         root.setLeft(addHelper(root.getLeft(), x));
      else if(x.compareTo((Comparable)root.getValue()) >= 0)
         root.setRight(addHelper(root.getRight(), x)); //greater than
      return root;
   }
   
/**Removes an element from the tree such that the tree is still an ordered Binary Search Tree.
 * @param  x a non-null Comparable Object.
 */   
   public void remove(Comparable x)
   {
      if(!contains(x))
      return;
      myRoot = removeHelper(myRoot, x);
   }
   
/**Helper method for remove(x).
 * @param   root is the root of a tree (or subtree for recursive calls).  
 * @param   x a non-null Comparable Object. 
 * @return  the root of the ordered binary search tree after x has been removed.
 */   
   private TreeNode removeHelper(TreeNode root, Comparable x)
   {
   //************COMPLETE THIS METHOD*****************************
   
      TreeNode curr = searchHelper(root, x);
      
      if(isLeaf(curr)) //is leaf
      {
         TreeNode parent = searchParent(root, x);
         if(parent.getLeft().getValue().equals(x)) //left leaf
            parent.setLeft(null);
         else if(parent.getRight().getValue().equals(x)) //right leaf
            parent.setRight(null);
         else if(parent.getLeft() == null && parent.getRight() == null) //root
            curr = null;
      }
      else if(oneKid(curr)) //one child
      {
         TreeNode parent = searchParent(root, x);
         
         if(parent.getLeft().getValue().equals(x) && curr.getLeft() != null) //is left child and has left child
            parent.setLeft(curr.getLeft());
         else if(parent.getLeft().getValue().equals(x) && curr.getRight() != null) //is left child and has right child
            parent.setLeft(curr.getRight());
         else if(parent.getRight().getValue().equals(x) && curr.getLeft() != null) //is right child and has left child
            parent.setRight(curr.getLeft());
         else if(parent.getRight().getValue().equals(x) && curr.getRight() != null) //is right child and has right child
            parent.setRight(curr.getRight());
      }
      else //if curr has two children
      {
         TreeNode least = curr.getLeft();
         while(!isLeaf(least))
         {
            if(least.getRight() != null)
               least = least.getRight();
            else if(least.getLeft() != null)
               least = least.getLeft();
         }
         
         Comparable temp = least.getValue();
         removeHelper(root, least.getValue());
         curr.setValue(temp);
      }
      return root;
   }
   
/** Displays  the elements of the tree such that they are displayed in prefix order. */  
   public void showPreOrder()
   {
      preOrderHelper(myRoot);
      System.out.println();  
   }
   
/**Helper method for showPreOrder().
 * Because the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls).  
 */   
   private void preOrderHelper(TreeNode root)
   {
   //************COMPLETE THIS METHOD*****************************
   
      if(root == null)
         return;
      System.out.print(root.getValue() + " ");
      preOrderHelper(root.getLeft());
      preOrderHelper(root.getRight());
   }
   
/** Displays  the elements of the tree such that they are displayed in infix order. */ 
   public void showInOrder()
   {
      inOrderHelper(myRoot);
      System.out.println();
   }

/**Helper method for showInOrder().
 * Because the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls).  
 */   
   private void inOrderHelper(TreeNode root)   
   {
      if(root!=null)
      {
         inOrderHelper(root.getLeft());
         System.out.print(root.getValue() + " ");    
         inOrderHelper(root.getRight());
      }
   }
      
/** Displays  the elements of the tree such that they are displayed in postfix order. */ 
   public void showPostOrder()
   {
      postOrderHelper(myRoot);
      System.out.println();   
   }
   
/**Helper method for showPostOrder(). 
 * Because the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls).  
 */   
   private void postOrderHelper(TreeNode root)
   {
   //************COMPLETE THIS METHOD*****************************
      if(root == null)
         return;
      postOrderHelper(root.getLeft());
      postOrderHelper(root.getRight());
      System.out.print(root.getValue() + " ");
   }
   
/**Searches for an element in the tree.
 * @param   x a non-null Comparable Object.
 * @return  true if x is found; false if x is not found in the tree
 */    
   public boolean contains(Comparable x)
   {
      if (searchHelper(myRoot, x)==null)
         return false;
      return true;
   }

/**Helper method for contains(x).
 * Because  the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (subroots for recursive calls).
 * @param   x a non-null Comparable Object.
 * @return  a pointer to the TreeNode that contains the value x; returns null if not found
 */   
   private TreeNode searchHelper(TreeNode root, Comparable x)
   {
   //************COMPLETE THIS METHOD*****************************
      if(root == null)
         return null;
      if(root.getValue().equals(x))
         return root;
      if(x.compareTo(root.getValue()) < 0)
         return searchHelper(root.getLeft(), x);
      //else if(x.compareTo(root.getValue()) > 0)
      return searchHelper(root.getRight(), x);
   }
   
/**Helper method for removeHelper(root, x).
 * Because  the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (subroots for recursive calls).
 * @param   x a non-null Comparable Object.
 * @return  a pointer to the parent of the node that contains the value x; returns null if not found
 */    
   private TreeNode searchParent(TreeNode root, Comparable x)
   {
      if(root == null)
         return null;
      if((root.getLeft() != null && root.getLeft().getValue().equals(x)) || (root.getRight() != null && root.getRight().getValue().equals(x)))
         return root;
      if(x.compareTo(root.getValue()) < 0)
         return searchParent(root.getLeft(), x);
      return searchParent(root.getRight(), x);
   }
   
/**Helper method for removeHelper(root, x).
 * @param   root is the root of a tree.
 * @return  true if root has no children; returns false if root has 1 or 2 children
 */ 
   private boolean isLeaf(TreeNode root)
   {
   //************COMPLETE THIS METHOD*****************************
      if(root == null)
         return false;
      if(root.getLeft() == null && root.getRight() == null)
         return true;
      return false;     //temporary return statement to keep things compiling
   }
      
/**Helper method for removeHelper(root, x).
 * @param   root is the root of a tree.
 * @return  true if root has exactly one child; returns false if root has 0 or 2 children
 */
   private boolean oneKid(TreeNode root)
   {
      if(root == null)
         return false;
      if((root.getLeft() == null && root.getRight() != null) || (root.getLeft() != null && root.getRight() == null))
         return true;
      return false;     //temporary return statement to keep things compiling
   }
      
/**Returns the number of logical elements stored in the tree.
 * @return the number of nodes in the tree.
 */ 
   public int size()
   {
      return sizeHelper(myRoot);
   }
   
/**Helper method for size().
 * Because  the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls). 
 * @return  the size of the tree that starts at root 
 */    
   private int sizeHelper(TreeNode root)
   {
      //************COMPLETE THIS METHOD*****************************
      TreeNode curr = root;
      if(curr == null)
         return 0;
      return 1 + sizeHelper(curr.getLeft())+sizeHelper(curr.getRight());
   }

/**Returns the number of levels in the tree.  
 * An empty tree is height -1.  A 1-node tree is height 0.
 * A 2-node tree and a balanced 3-node tree are height 1.
 * @return the height/depth/number of levels of the tree.
 */          
   public int height()
   {
      return heightHelper(myRoot);
   }

/**Helper method for height().
 * Because  the process is recursive and needs to continue by sending subtrees as the next root to process.
 * @param   root is the root of a tree (or subtree for recursive calls). 
 * @return the height/depth/number of levels of the tree that starts at root.
 */   
   public int heightHelper(TreeNode root)
   {
   /************COMPLETE THIS METHOD******************************/
      if (root == null)
         return -1;
      else
      {
         int leftTree = heightHelper(root.getLeft());
         int rightTree = heightHelper(root.getRight());
         if (rightTree > leftTree)
            return(rightTree + 1);
         else //if(leftTree > rightTree)
            return(leftTree + 1);
      }
   }
   
/**EXTRA CREDIT: returns true if p is an ancestor of c.
 * @param   root is the root of a tree (or subtree for recursive calls). 
 * @param   p a non-null Comparable Object that can be found in the tree. 
 * @param   c a non-null Comparable Object that can be found in the tree.  
 * @return  true if p is an ancestor of c; return false if not or one/both can not be found in the tree.
 */    
   public boolean isAncestor(TreeNode root, Comparable p, Comparable c)
   {
   //************COMPLETE THIS METHOD*****************************   
      return false;     //temporary return statement to keep things compiling
   }
   
/**EXTRA CREDIT: displays all elements of the tree at a particular depth/level/height.
 * level 0 will show the root.  level 1 will show all elements that are children of the root.
 * A level that is less than 0 or greater than the max depth will display nothing.
 * @param   root is the root of a tree. 
 * @param   level is the depth in which you want to see all the elements of. 
 */ 
   public void printLevel(TreeNode root, int level)
   {
      
   }
 
/**Nothing to see here...move along.*/     
   private String temp;
/**Helper method for toString().
 * @param   root is the root of a tree (or subtree for recursive calls). 
 */ 
   private void inOrderHelper2(TreeNode root)   
   {
      if(root!=null)
      {
         inOrderHelper2(root.getLeft());
         temp += (root.getValue() + ", "); 
         inOrderHelper2(root.getRight());
      }
   }

/**Returns a String of all the elements in the tree in the form [a1, a2, a3, . . . , an] in order
 * @return String of all the in-oder tree elements separated by a comma
 */
   @Override
   public String toString()
   {
      temp="";
      inOrderHelper2(myRoot);
      if(temp.length() > 1)
         temp = temp.substring(0, temp.length()-2);
      return "[" + temp + "]";
   }
   
   public void numberLeavesHelper(TreeNode root, int nextNum)
   {
      if(root.getLeft() == null & root.getRight() == null)
         root.setValue(nextNum);
      if(root.getLeft() != null)
         numberLeavesHelper(root.getLeft(), nextNum);
      if(root.getRight() != null)
         numberLeavesHelper(root.getRight(), nextNum);
   }
   public void numberLeaves(int nextNum)
   {
      numberLeavesHelper(myRoot, nextNum);
   }
   
   public void changeTreeHelper(TreeNode root)
   {
      if(root.getLeft() != null && root.getRight() != null)
      {
         changeTreeHelper(root.getLeft());
         changeTreeHelper(root.getRight());
      }
      if(oneKid(root))
      {
         if(root.getLeft() != null && (root.getLeft().getRight() != null || root.getLeft().getLeft() != null))
            changeTreeHelper(root.getLeft());
         if(root.getRight() != null && (root.getRight().getRight() != null || root.getRight().getLeft() != null))
            changeTreeHelper(root.getRight());
         if(root.getLeft() != null && (root.getLeft().getRight() == null && root.getLeft().getLeft() == null))
         {
            root.setValue(root.getLeft().getValue());
            root.setLeft(null);
         }
         if(root.getRight() != null && (root.getRight().getRight() == null && root.getRight().getLeft() == null))
         {
            root.setValue(root.getRight().getValue());
            root.setRight(null);
         }
      }
   }
   
   public void changeTree()
   {
      changeTreeHelper(myRoot);
   }
}