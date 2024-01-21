public class dataItem
{
   private int num;
   private String word;
   public dataItem(int n, String w)
   {
      num = n;
      word = w;
   }
      
   public String getWord()
   {
      return word;
   }
      
   public int getNum()
   {
      return num;
   }
      
   public boolean equals(dataItem x)
   {
      if(num == x.getNum() && word.equals(x.getWord()))
         return true;
      return false;
   }
   
   public int hashCode()
   {
      return word.hashCode();
   }
   
   public String toString()
   {
      return num + " " + word;
   }
}