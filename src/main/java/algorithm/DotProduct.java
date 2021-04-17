package algorithm;

public class DotProduct {

  /**
   * Given two arrays of integers, returns the dot product of the arrays
   * Arrays contains values of i, j and k axis (can contain i and j only as well)
   * A = a1 i + a2 j + a3 k; B = a2 i + a2 j + a3 k
   * Dot product = a1*a2 + b1*b2
   * 
   * There is also a cross product:
   * Cross product is calculated as cross product = (a2 * b3 – a3 * b2) * i + (a1 * b3 – a3 * b1) * j + (a1 * b1 – a2 * b1) * k,
   *  where a2 * b3 – a3 * b2, a1 * b3 – a3 * b1 and a1 * b1 – a2 * b1 are the coefficient of unit vector along i, j and k directions
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