package algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FirstNonRepeating {

	/**
	 * Finds the first character that does not repeat anywhere in the input string
	 * Given "apple", the answer is "a" Given "racecars", the answer is "e"
	 **/
	/**
	 * Solution: 
	 * Maintain a linked list of characters as they appear in the stream.
	 * Maintain a set of characters which have already appeared
	 * For the character being checked, if already present in set, remove it from the list
	 * Return the first character present in the list
	 */
	public static Character findFirst(String input) {
		List<Character> list = new LinkedList<Character>();
		Set<Character> presentChars = new HashSet<>();

		for (int i = 0; i < input.length(); i++) {
			Character c = input.charAt(i);
			if (presentChars.contains(c)) {
				list.remove(c);
			} else {
				list.add(c);
				presentChars.add(c);
			}
		}

		if (list.isEmpty())
			return null;

		return list.get(0);
	}

	public static void main(String args[]) {

		String[] inputs = { "apple", "racecars", "ababdc" };
		char[] outputs = { 'a', 'e', 'd' };

		boolean result = true;
		for (int i = 0; i < inputs.length; i++) {
			result = result && findFirst(inputs[i]) == outputs[i];
			if (!result)
				System.out.println("Test failed for: " + inputs[i]);
			else
				System.out.println("Test passed for: " + inputs[i]);
		}
	}

}


