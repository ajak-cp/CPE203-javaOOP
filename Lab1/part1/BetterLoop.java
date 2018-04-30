class BetterLoop
{
   public static boolean contains(int [] values, int v)
   {
      /* TO DO: if value v is in the array, return true.
         If not, return false.  Use a "foreach" loop.
      */
         boolean flag = false;
         for (int x : values){
         	if (x == v){
         		flag = true; 
         	}

         }
      return flag;  // A bit optimistic, but a real boolean value.
   }
}
