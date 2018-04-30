class SimpleArray
{ 
   public static int [] squareAll(int values[])
   {
        int [] newValues = new int[values.length];  // This allocates an array of integers.

      /* TO DO: The output array, newValues, should hold as
         its elements the square of the corresponding element
         in the input array.

         Write a loop to compute the square of each element from the
         input array and to place the result into the output array.
      */
        for (int i = 0; i < values.length; i++){
        	int squared = values[i] * values[i];
        	newValues[i] = squared;
        }

      return newValues;
   }
}
