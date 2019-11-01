package algorithm;

import java.util.HashSet;
// https://www.geeksforgeeks.org/print-subsets-given-size-set/

public class UniqueTuples {

	public static HashSet<String> uniqueTuples(String input, int len) {
		HashSet<String> result = new HashSet<String>();
		createTuples(input, "", len, result);
		return result;
	}

	private static void createTuples(String input, String subStr, int len, HashSet<String> result) {
		if (subStr.length() == len) {
			result.add(subStr);
			return;
		}
		if (input.isEmpty()) { //this should be checked after above if statement, else you will miss few combinations
			return;
		}
		for (int i = 0; i < input.length(); i++) {
			char cur = input.charAt(i);
			input = input.substring(i + 1); //take only the leftover string, unless you want 
											//to generate all possible combinations like 'ba'
			createTuples(input, subStr, len, result); //current not considered
			createTuples(input, subStr + cur, len, result); //current considered
		}
	}

	public static void main(String[] args) {
		String input = "aabc";
		HashSet<String> result = uniqueTuples(input, 2);
		System.out.println(result);
		if (result.contains("aa") && result.contains("ab")) {
			System.out.println("Test passed.");

		} else {
			System.out.println("Test failed.");

		}
	}
}