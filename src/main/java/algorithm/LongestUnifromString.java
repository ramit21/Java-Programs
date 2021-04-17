package algorithm;

/**
 *  
 *  e.g.
 *      for the input: "abbbccda" the longest uniform substring is "bbb" (which starts at index 1 and is 3 characters long).
 */

import java.util.*;

public class LongestUnifromString {

  private static final Map<String, int[]> testCases = new HashMap<String, int[]>();

  static int[] longestUniformSubstring(String input){
    int longestStart = -1;
    int longestLength = 0;
    int curStart = -1;
    int curLength = 0;
    char prevChar = Character.MIN_VALUE;
    
    for(int i=0; i< input.length(); i++) {
    	if(input.charAt(i) == prevChar) {
    		curLength++;
    	}else {
    		if(curLength > longestLength) {
    			longestLength = curLength;
    			longestStart = curStart;
    		}
    		curStart = i;
    		curLength = 1;
    	}
    	prevChar = input.charAt(i);
    }
    
    return new int[]{ longestStart, longestLength };
  }

  public static void main(String[] args) {
    testCases.put("", new int[]{-1, 0});  //Always write code testing for edge conditions
    testCases.put("10000111", new int[]{1, 4});
    testCases.put("aabbbbbCdAA", new int[]{2, 5});

    boolean pass = true;
    for(Map.Entry<String,int[]> testCase : testCases.entrySet()){
      int[] result = longestUniformSubstring(testCase.getKey());
      pass = pass && (Arrays.equals(result, testCase.getValue()));
    }
    if(pass){
      System.out.println("Pass!");
    } else {
      System.out.println("Failed! ");
    }
  }
}