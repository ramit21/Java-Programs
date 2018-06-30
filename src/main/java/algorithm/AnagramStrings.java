package algorithm;

/*
 * Check if two strings are anagrams without using extra memory
 * Hint: Recursion
 */
public class AnagramStrings {

	private static boolean checkIfAnagrams(String s1, String s2){
		if(s1.length() != s2.length()){
			return false;
		}
		if(s1.equals(s2)){
			return true;
		}
		char firstChar = s1.charAt(0);
		int index = s2.indexOf(firstChar);
		if(index == -1){
			return false;
		}
		return checkIfAnagrams(s1.substring(1),
				s2.substring(0, index) + s2.substring(index+1));
	}
	
	public static void main(String[] args) {
		System.err.println(checkIfAnagrams("ramit", "timra"));
		System.err.println(checkIfAnagrams("ramit", "r"));
		System.err.println(checkIfAnagrams("ramit", "ramit"));
		System.err.println(checkIfAnagrams("ramit", "amrit"));
		System.err.println(checkIfAnagrams("ramit", "simra"));
	}
}
