package algorithm;

// Given a string of letters and a dictionary,
//     find the longest word or words in the dictionary that can be made from the letters
//     Input: letters = "oet", dictionary = {"to","toe","toes"}
//     Output: {"toe"}
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DictioneryProblem {
	
	public static Set<String> longestWord(String letters, Dictionary dict) {
		Set<String> subsets = new HashSet<String>();
		
		return null;
	}
	
	private void generateSubsets(String letters, Set<String> subsets){
		for(int i=0; i< letters.length(); i++) {
			Character curChar = letters.charAt(i);
			letters = letters.substring(0,i) + letters.substring(i+1);
			subsets.add(curChar.toString());
		}
		
	}

	public static boolean pass() {
		Dictionary dict = new Dictionary(new String[] { "to", "toe", "toes", "doe", "dog", "god", "dogs", "banana" });
		boolean r = new HashSet<String>(Arrays.asList("toe")).equals(longestWord("toe", dict));
		return r;
	}

	public static void main(String[] args) {
		if (pass()) {
			System.out.println("Pass");
		} else {
			System.err.println("Fails");
		}
	}
}

class Dictionary {
	
	private String[] entries;

	public Dictionary(String[] entries) {
		this.entries = entries;
	}

	public boolean contains(String word) {
		return Arrays.asList(entries).contains(word);
	}
}