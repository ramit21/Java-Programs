package algorithm;

public class DotProduct {

  /*
    Given two arrays of integers, returns the dot product of the arrays
   * Arrays contains values of i, j and k axis (can contain i and j only as well)
   * A = a1 i + a2 j + a3 k; B = a2 i + a2 j + a3 k
   * Dot product = a1*a2 + b1*b2
   *
*/

  public static int dotProduct( int[] array1, int[] array2 ) {
     int sum =0;
	 for(int i=0; i< array1.length; i++) {
    	sum += array1[i] * array2[i];
    }
    return sum;
  }
 
  public static void main( String[] args ) {
   int[] array1 = { 1, 2 };
    int[] array2 = { 2, 3 };
    int result = dotProduct( array1, array2 );

    if( result == 8 ) {
      System.out.println( "Passed." );
    } else {
      System.out.println( "Failed." );
    }
  }
}