package algorithm;

/*
 * Implement a run length encoding function.
 * For a string input the function returns output encoded as follows:
 *
 * "a"     -> "a1"
 * "aa"    -> "a2"
 * "aabbb" -> "a2b3"
 */
public class RunLengthEncoding {

  public static String rle(String input) {
    // Your code goes here
    return "";
  }


 public static void main(String[] args)  {
    
	  if("".equals(rle("")) && 
			  "a1".equals(rle("a")) && 
			  "a3".equals(rle("aaa"))){
		  System.out.println("Passed");
	  }else {
		  System.out.println("Failed");
	  }
  }
}