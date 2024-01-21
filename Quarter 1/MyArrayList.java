public class MyArrayList<anyType> implements ListInterface<anyType>{
   private Object[] list;
   private int numElements;
   	
   public MyArrayList(){
      list = new Object[10];
      numElements = 0;
   }
   
   private void doubleCapacity(){
      Object[] temp = list.clone();
      list = new Object[list.length * 2];
      for(int i = 0; i < numElements; i++){
         list[i] = temp[i];
      }
   }
      
   private void cutCapacity(){
      Object[] temp = list.clone();
      list = new Object[list.length / 2];
      for(int i = 0; i < numElements; i++){
         list[i] = temp[i];
      }
   }
   
   public boolean add(anyType x){
      if(list.length == numElements)
         doubleCapacity();
      list[numElements] = x;
      numElements++;
      return true;
   }
 
   public boolean add(int index, anyType x){
      if(list.length == numElements)
         doubleCapacity();
      for(int i = numElements; i > index; i--)
         list[i] = list[i-1];
      list[index] = x;
      numElements++;
      return true;
   }
   									
   public int size(){				
      return numElements;
   }
   
   public anyType set(int index, anyType x){
      list[index] = x;
      return x;
   }
   
   public anyType get(int index){
      return (anyType)list[index];
   }
   
   public anyType remove(int index){
      anyType x = (anyType)list[index];
      if ((list.length/3) > numElements)
         cutCapacity();
      for(int i = index; i < numElements; i++)
         list[i] = list[i+1];
      numElements--;
      return x;
   }
      
   public String toString(){
      String ans = "[";
      for(int i = 0; i < numElements; i++){
         ans += list[i];
         if(i < numElements-1)
            ans += ", ";
      }
      return ans + "]";
   }  
}