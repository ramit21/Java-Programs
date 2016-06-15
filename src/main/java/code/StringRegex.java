package code;

/**
 * Reverse a given sentence, with and without same sequence of spaces
 */
public class StringRegex {
	private static final String stmnt1 = "My name is Ramit";
	private static final String stmnt2 = "My   name  is    Ramit";

	private String reverseWords(String str) {
		String strArr[] = str.split(" ");
		StringBuffer buf = new StringBuffer();
		for (int i = strArr.length - 1; i >= 0; i--) {
			buf.append(strArr[i] + " ");
		}
		buf.deleteCharAt(buf.length() - 1);
		return buf.toString();
	}

	/*
	 * reverse the word keeping the spacing same
	 */
	private String reverseWordswithSpacing(String str) {
		StringBuffer buf = new StringBuffer();
		String[] whiteSpaces = str.split("\\w+");// plus means one or more word.
													// note that this gives
													// value at first index as
													// ""
		String[] words = str.split("\\s+");

		for (int i = 0; i < whiteSpaces.length; i++) {
			buf.append(whiteSpaces[i]);
			buf.append(words[words.length - i - 1]);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		StringRegex obj = new StringRegex();
		System.out.println("Reversre string : " + obj.reverseWords(obj.stmnt1));
		System.out.println("Reversre string 2: " + obj.reverseWordswithSpacing(obj.stmnt2));
	}

}
