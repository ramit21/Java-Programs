package algorithm;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * Print all permutations of a string (or even a number) maintaining lexical order.
 * Steps:
 * 1. Sort the string (as first answer is string in non decreasing sequence)
 * 2. From right, find 'firstChar' that is smaller than char on its right
 * 3. Find 'secondChar' to the right of 'firstChar' which is ceiling of firstChar 
 * 	  (min of all bigger than firstChar)
 * 4. Swap first and second chars
 * 5. reverse the substring after the original position of 'firstChar'
 * 6. Repeat steps 2-5 till no firstChar can be found
 */
public class StringPermutationLexical {

	public static void permute(String s, Set<String> solutions){
		char[] strArr = s.toCharArray();
		int firstCharIndex = -1;
		int secondCharIndex = -1;
		for(int i = strArr.length-2; i>=0; i--){
			if(strArr[i] < strArr[i+1]){
				firstCharIndex = i;
				break;
			}
		}
		if(firstCharIndex == -1) return;
		
		secondCharIndex = findCeilingIndex(strArr, firstCharIndex+1, strArr[firstCharIndex]);
		
		char tmp = strArr[firstCharIndex];
		strArr[firstCharIndex] = strArr[secondCharIndex];
		strArr[secondCharIndex] = tmp;
		reverseFromIndex(strArr, firstCharIndex+1);
		String newStr = new String(strArr);
		solutions.add(newStr);
		permute(newStr, solutions);
	}
	
	private static void reverseFromIndex(char[] strArr, int start) {
		int len = strArr.length;
		char[] revArr = new char[len-start];
		for(int i=0; i<len-start; i++){
			revArr[i] = strArr[len-i-1];
		}
		for(int i=0; i<len-start; i++){
			strArr[start+i] = revArr[i];
		}		
	}
	
	private static int findCeilingIndex(char[] strArr, int start, char c) {
		char ceiling = strArr[start];
		int ceilingIndex = start;
		for(int i=start+1; i<strArr.length; i++){
			if(strArr[i]<ceiling && strArr[i]>c){
				ceiling = strArr[i];
				ceilingIndex = i;
			}
		}
		return ceilingIndex;
	}

	private static String sortString(String s){
		if(s.length() <= 1) return s;
		char[] arr = s.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}
	
	public static void main(String[] args) {
		//String s = "bca";
		String s = "dbca";
		s = sortString(s);
		Set<String> solutions = new LinkedHashSet<>();
		solutions.add(s);
		permute(s, solutions);
		for(String sol: solutions){
			System.out.print(sol+" ");
		}
		
	}
}
