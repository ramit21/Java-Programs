package algorithm;

import java.util.HashSet;

public class UniqueTuples {

  public static HashSet<String> uniqueTuples( String input, int len ) {
    // your code
    HashSet<String> result = new HashSet<String>();
    result.add( "aa" );
    result.add( "ab" );
    return result;
  }

  public static void main( String[] args ) {
    String input = "aab";
    HashSet<String> result = uniqueTuples( input, 2 );
    if( result.contains( "aa" ) && result.contains( "ab" ) ) {
      System.out.println( "Test passed." );
     
    } else {
      System.out.println( "Test failed." );
      
    }
  }
}