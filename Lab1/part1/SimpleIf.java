class SimpleIf
{
   public static double max(double x, double y)
   {
      /* TO DO: Write an if statement to determine which
         argument is larger and return that value.
      */
      if (x > y){ //X greater than Y 
      	return x;
      }
      else if (y > x) { // Y greater than X
      	return y;
      }
      else{ //The inputs are equal
      	return x;
      }
   }
}
