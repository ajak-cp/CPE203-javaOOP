class SimpleLoop
{
   public static int sum(int low, int high)
   {
      int returnSum = 0;
      for (int i = low; i < (high + 1); i++){
      	returnSum = returnSum + i;
      }

      return returnSum;
   }
}
