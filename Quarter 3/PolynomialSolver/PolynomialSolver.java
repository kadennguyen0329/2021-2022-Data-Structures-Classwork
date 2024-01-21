import java.util.*;

public class PolynomialSolver
{
   private static Scanner input = new Scanner(System.in);
   private static Polynomial poly;
   
   public static void main(String[] args)
   {
      createPolynomial();
      System.out.println(poly.toString());
      System.out.print(poly.getNumSolutions() + " out of " + poly.getMaxSolutions() + " solutions have been found. ");
      if(poly.getNumSolutions() < poly.getMaxSolutions())
      {
           System.out.print("There MAY be " + (poly.getMaxSolutions()-poly.getNumSolutions()) + " imaginary solutions.");
      }
         
   }
   
   public static void createPolynomial()
   {
      String choice = "";
         ArrayList<Double> p = new ArrayList<Double>();
         
         while(!choice.equals("N"))
         {
            System.out.println("Enter the next coefficient of the polynomial in descending order.");
            double num = input.nextDouble();
            p.add(num);
            input.nextLine();   //absorbs enter key
            System.out.println("Continue adding to polynomial? Y or N.");
            choice = input.nextLine().toUpperCase();
         }
         
         double[] d = new double[p.size()];
         for(int i = 0; i < p.size(); i++)
            d[i] = p.get(i).doubleValue();
         poly = new Polynomial(d, d.length-1);
         
   }
}