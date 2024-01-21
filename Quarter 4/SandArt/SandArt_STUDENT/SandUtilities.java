//d oberle - doberle@fcps.edu
import java.awt.Color;

public class SandUtilities
{
  //pre : c!= null.
  //post: returns the inverted color from the one sent as c.
   public static Color invert(Color c)
   {
      Color invert = new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
      return invert;
   }
   
   //pre:   m!= null.
   //post:  for each non-null element of m, changes it to its inverted color.
   //       skips any color with the value skip1 and skip2, leaving them unchanged.
   public static void invertColors(Color[][] m, Color skip1, Color skip2)
   {
      for(int r = 0; r<m.length; r++){
         for(int c = 0; c<m[0].length; c++){
            if(m[r][c]!=null && !m[r][c].equals(skip1) && !m[r][c].equals(skip2))
               m[r][c] = invert(m[r][c]);
         }
      } 
   }
   
   //pre:   m is a square 2-D array (m.length==m[0].length).
   //post:  flips the array upside down.
   public static void flipUpsideDown(Object[][]m)
   {
      for(int r = 0; r < m.length/2; r++){
         for(int c = 0; c < m.length; c++)
            swap(m, r, c, (m.length-1-r), c);
      }
   }
   
   public static void swap(Object[][]m, int r1, int c1, int r2, int c2){
      Object temp = m[r1][c1];
      m[r1][c1] = m[r2][c2];
      m[r2][c2] = temp;
   }
   
   //pre:   m is a square 2-D array (m.length==m[0].length)
   //post:  rotates the array 90 degrees to the left
   public static void rotateLeft(Object[][] m) 
   {
      Object[][] original = new Object[m.length][m[0].length];
      
      for(int r = 0; r < m[0].length; r++)
      {
         original[r] = m[r].clone();
      }
      
      int r1 = 0, c1 = 0;
      for(int c =  0; c < m[0].length; c++)
      {
         for(int r = m.length-1; r > -1; r--)
         {
            m[r1][c1] = original[r][c];
            c1++;
         }
         c1 = 0;
         r1++;
      }
   }

   //pre:   m is a square 2-D array (m.length==m[0].length)
   //post:  rotates the array 90 degrees to the right
   public static void rotateRight(Object[][] m)
   {
   Object[][] original = new Object[m.length][m[0].length];
      
      for(int r = 0; r < m[0].length; r++)
      {
         original[r] = m[r].clone();
      }
      
      int r1 = 0, c1 = 0;
      for(int c =  m[0].length-1; c >= 0; c--)
      {
         for(int r = 0; r < m.length; r++)
         {
            m[r1][c1] = original[r][c];
            c1++;
         }
         c1 = 0;
         r1++;
      }

   }      
}