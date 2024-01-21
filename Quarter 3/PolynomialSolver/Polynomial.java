//to do: check for double zeroes

import java.util.*;
public class Polynomial
{
   private double[] coeff;
   private int degree, maxSolutions;
   private double MIN, MAX, ALMOST_ZERO, RESOLUTION;
   private  ArrayList<Double> solutions;
   
   
   public Polynomial(double[] c, int d)
   {
      degree = d;
      maxSolutions = d;
      ALMOST_ZERO = 0.00000001;
      RESOLUTION = 0.078125;
      coeff = new double[c.length];
      for(int i = 0; i < c.length; i++)
         coeff[i] = c[i];
      solutions = new ArrayList<Double>();
      if(coeff.length > 3)
         this.getMINandMAX();
      findRoots();
   }  
   
   public void findRoots()
   {
      if(coeff.length == 2)//linear
         solutions.add((coeff[1]*-1)/(coeff[0])); //add -b/a
      else if(coeff.length == 3)//quadratic
      {
         if((Math.pow(coeff[1],2) - (4*coeff[0]*coeff[2])) >= 0)//if discriminant isnt negative
         {
            double solution1=((coeff[1]*-1) - Math.sqrt(Math.pow(coeff[1],2) - (4*coeff[0]*coeff[2])))/(2*coeff[0]);//solution 1
            double solution2=((coeff[1]*-1) + Math.sqrt(Math.pow(coeff[1],2) - (4*coeff[0]*coeff[2])))/(2*coeff[0]);//solution 2
            if(isDoubleRoot(solution1))//if its a double root
            {
               solutions.add(solution1);//add root
               maxSolutions--;//shrink size
            }
            else
            {
               solutions.add(solution1);
               solutions.add(solution2);
            }
         }
      }
      else //polynomial
         for(double i = 1; i > RESOLUTION; i = i/2)
         {
            for(double x = MIN; x < MAX; x++)
            {
               double lower = x;
               double upper = x+i;
               if(isSolution(lower))
               {
                  if(!solutions.contains(lower))
                  {
                     if(isDoubleRoot(lower))
                     {
                        solutions.add(lower);
                        maxSolutions--;
                     }
                     else
                        solutions.add(lower);
                  }
               }
                  
               if(hasRootInBetween(lower, upper))
               {  
                  double root = findRoot(lower, upper);
                  if(!solutions.contains(root))
                  {
                     if(isDoubleRoot(root))
                     {
                        solutions.add(root);
                        maxSolutions--;
                     }
                     else
                        solutions.add(root);
                  }
               }
               if(solutions.size() >= maxSolutions)
                  return;
            }
         }
   }
   
   public boolean isDoubleRoot(double x)
   {
      double left = x - ALMOST_ZERO;
      double right = x + ALMOST_ZERO;
      double f_left = plugX(left);
      double f_right = plugX(right);
      if((f_right<0) == (f_left<0))
         return true;
      return false;
   }
   
   private void getMINandMAX()
   {
      ArrayList<Double> temp = new ArrayList<Double>();
      for(int i = 0; i < coeff.length; i++)
         temp.add(Math.abs(coeff[i]));
      double ld = temp.get(0);
      temp.remove(0);
      for(int i = 0; i < coeff.length-1; i++) 
         temp.set(i, (temp.get(i))/ld);
      double maxTemp = temp.get(0);
      double sumTemp = 0.0;
      for(int i = 0; i < temp.size(); i++)
      {
         if(temp.get(i)>maxTemp)
            maxTemp = temp.get(i);
      }
      for(int i = 0; i < temp.size(); i++)
         sumTemp += temp.get(i);
      double range = (double)Math.round(Math.min((Math.max(1, sumTemp)), 1+maxTemp));
      MIN = range * -1;
      MAX = range;
   }
   
   public String toString()
   {
      String ret = "";
      for(int i = degree; i >= 0; i--)
      {
         if(coeff[coeff.length-1-i] != 0)
         {
            if(i != 0)
               ret += coeff[coeff.length-1-i] + "x^" + i + " + ";
            else
               ret += coeff[coeff.length-1-i];
         }
      }
      ret += "\n Roots: ";
      for(int i = 0; i < solutions.size(); i++)
         ret += solutions.get(i) + "    ";
      return ret;
   }
   
   
   
   public double plugX(double x)
   {
      double ret = 0.0;
      for(int i = degree; i >= 0; i--)
         ret += Math.pow(x, i) * coeff[coeff.length-1-i];
      return ret;
   }
   
   public boolean hasRootInBetween(double l, double u)
   {
      double plugL = this.plugX(l);
      double plugU = this.plugX(u);
      if((plugL < 0 && plugU > 0) || plugL > 0 && plugU < 0)
         return true;
      return false;
   }
   
   public boolean isSolution(double x)
   {
      return Math.abs(plugX(x)) < ALMOST_ZERO;
   }
   
   public double findRoot(double l, double u)
   {
      double root = 0.0;
      double mid = (l+u)/2;
      
      if(isSolution(mid))
      {
         root = mid;
         return root;
      }
      if(hasRootInBetween(l, mid))
         root = findRoot(l, mid);
      if(hasRootInBetween(mid, u))
         root = findRoot(mid, u);
      return root;
   }
   public int getNumSolutions()
   {
      return solutions.size();
   }
   
   public int getMaxSolutions()
   {
      return maxSolutions;
   }
}