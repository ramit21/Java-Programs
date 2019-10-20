package algorithm;

public class AddFraction {

  /**
   * Given two fractions passed in as int arrays,
   * returns the fraction which is result of adding the two input fractions.
   */
  public static int[] addFractions( int[] fraction1, int[] fraction2 ) {
    // Write your code here
    return ( new int[]{ 0, 0 } );
  }

  
  public static void main( String[] args ) {
    int[] result = addFractions( new int[]{ 2, 3 }, new int[]{ 1, 2 } );

    if( result[ 0 ] == 7 && result[ 1 ] == 6 ) {
      System.out.println( "Test passed." );
      //return true;
    } else {
      System.out.println( "Test failed." );
     // return false;
    }
  }
}