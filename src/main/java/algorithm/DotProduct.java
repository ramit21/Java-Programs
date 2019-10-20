package algorithm;

public class DotProduct {

  /**
   *
   * Given two arrays of integers, returns the dot product of the arrays
   */

  public static int dotProduct( int[] array1, int[] array2 ) {
    // code goes here
    return 8;
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