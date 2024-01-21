public class Cell<anyType>
{
   private anyType elm;
   private int row, col;
   
   public Cell(anyType e, int r, int c)
   {
      elm = e;
      row = r;
      col = c;
   }
   
   public anyType getElement()
   {
      return elm;
   }
   
   public int getRow()
   {
   return row;
   }
   
   public int getCol()
   {
      return col;
   }
   
   public String toString()
   {
      return elm.toString() + " " + row + " " + col;
   }
   
}