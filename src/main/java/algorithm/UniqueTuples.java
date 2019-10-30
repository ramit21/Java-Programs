package algorithm;

import java.util.HashSet;

public class UniqueTuples {

	public static HashSet<String> uniqueTuples(String input, int len ) {
    HashSet<String> result = new HashSet<String>();
    createTuples(input, len, 0, result);
    return result;
  }

	private static void createTuples(String input, int len, int i, HashSet<String> result) {
		if(i>=len-1) {
			return;
		}
		
	}

	public static void main(String[] args) {
		String input = "aab";
		HashSet<String> result = uniqueTuples(input, 2);
		if (result.contains("aa") && result.contains("ab")) {
			System.out.println("Test passed.");

		} else {
			System.out.println("Test failed.");

		}
	}
}