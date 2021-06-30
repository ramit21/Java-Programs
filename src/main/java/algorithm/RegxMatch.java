package algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * Given a text and a pattern, determine if the pattern matches with the text completely or not at all by using 
 * regular expression matching. For simplicity, assume that the pattern may contain only two operators: ‘.’ and ‘*’.
 * Operator '’ in the pattern means that the character preceding ‘*’ may not appear or may appear any number of times in the text.
 * Operator ‘.’ matches with any character in the text exactly once.
 */
class RegxMatch {
	  public static boolean regxMatchRec(String text, String pattern, int i, int j) {
	    if (text.length() == i && pattern.length() == j) {
	      return true;
	    }
	
	    if (j<pattern.length() - 1 && pattern.charAt(j + 1) == '*') {
	      for (int k = i; k<= text.length(); ++k) {
	        if (regxMatchRec(text, pattern, k, j + 2)) {
	          return true;
	        }
	
	        if (k >= text.length()) {
	          return false;
	        }
	
	        if (pattern.charAt(j) != '.' && text.charAt(k) != pattern.charAt(j)) {
	          return false;
	        }
	      }
	    } else if (i<text.length() && j<pattern.length() &&
	      (pattern.charAt(j) == '.' || pattern.charAt(j) == text.charAt(i))) {
	      return regxMatchRec(text, pattern, i + 1, j + 1);
	    }
	
	    return false;
	  }
	
	  public static boolean regxMatch(String text, String pattern) {
	    return regxMatchRec(text, pattern, 0, 0);
	  }
	
	  public static void main(String[] args) {
	    String s = "fabbbc";
	    String p = ".ab*c";
	    boolean output2 = regxMatch(s, p);
	
	    //Pattern pattern = Pattern.compile(p);
	    //Matcher matcher = pattern.matcher(s);
	    
	    if (output2) {
				System.out.print(s + " " + p + " match");
			} else {
				System.out.print(s + " " + p + " did not match.");
			}
		}
}
