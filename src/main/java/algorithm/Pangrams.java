package algorithm;

/**
 * Pangram FInder
 *
 * The sentence "The quick brown fox jumps over the lazy dog" contains every
 * single letter in the alphabet. Such sentences are called pangrams. Write a
 * function findMissingLetters, which takes a String `sentence`, and returns all
 * the letters it is missing
 *
 */
public class Pangrams {

	private static class PanagramDetector {

		//your code goes here
		public String findMissingLetters(String sentence) {
			String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
			sentence = sentence.toLowerCase();
			for(int i=0; i< sentence.length(); i++) {
				int index = ALPHABETS.indexOf(sentence.charAt(i));
				if(index != -1) {
					ALPHABETS = ALPHABETS.substring(0,index) + ALPHABETS.substring(index+1);
				}
			}
			return ALPHABETS;
		}
	}

	public static void main(String[] args) {
		PanagramDetector pd = new PanagramDetector();
		boolean success = true;

		success = success && "".equals(pd.findMissingLetters("The quick brown fox jumps over the lazy dog"));
		success = success && "abcdefghijklmnopqrstuvwxyz".equals(pd.findMissingLetters(""));
		success = success && "dg".equals(pd.findMissingLetters("The quick brown fox jumps over the lazy"));
		if (success) {
			System.out.println("Pass ");
		} else {
			System.out.println("Failed");
		}
	}
}