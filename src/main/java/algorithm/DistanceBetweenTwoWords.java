package algorithm;

public class DistanceBetweenTwoWords {

	// Input two words returns the shortest distance between their two midpoints in
	// number of characters
	// Words can appear multiple times in any order and should be case insensitive.

	// E.g. for the document="Example we just made up"
	// shortestDistance( document, "we", "just" ) == 4
	// https://www.geeksforgeeks.org/minimum-distance-between-words-of-a-string/
	//special handling for word2 behind word 1, for case insensitiveness
	//TODO handling for comma and full-stops

	public static double shortestDistance(String document, String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		document= document.toLowerCase();
		
		int shortestDist = Integer.MAX_VALUE;
		String[] words = document.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				for (int j = 0; j < words.length; j++) {
					if (words[j].equals(word2)) {
						int start = Math.min(i, j);
						int end = Math.max(i, j);
						int dist = end - start; // no of spaces
						dist += (word1.length() + word2.length())/ 2;
						for (int k = start + 1; k < end; k++) {
							dist += words[k].length();
						}
						shortestDist = Math.min(shortestDist, dist);
					}
				}
			}
		}
		System.out.println("word1 = "+word1+", word2 = "+word2+", shortestDist = "+shortestDist);
		return shortestDist;
	}

	public static boolean pass() {
		return  shortestDistance(document, "and", "graphic") == 6d 
				&& shortestDistance(document, "transfer", "it") == 14d
				&& shortestDistance(document, "Design", "filler") == 25d;
	}

	public static void main(String[] args) {
		if (pass()) {
			System.out.println("Pass");
		} else {
			System.out.println("Some Fail");
		}
	}

	private static final String document;
	static {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"In publishing and graphic design, lorem ipsum is a filler text commonly used to demonstrate the graphic elements");
		sb.append(
				" lorem ipsum text has been used in typesetting since the 1960s or earlier, when it was popularized by advertisements");
		sb.append(
				" for Letraset transfer sheets. It was introduced to the Information Age in the mid-1980s by Aldus Corporation, which");

		document = sb.toString();
	}
}