package algorithm;

public class ReverseStringBugFix {
	/**
	 * public static String reverseStr( String str ) Example: reverseStr(str) where
	 * str is "abcd" returns "dcba".
	 */
	public static String reverseStr(String str) {
		if (str == null || str.length() == 1) {
			return str;
		}
		return str.charAt(str.length() - 1) + reverseStr(str.substring(0, str.length() - 1));
	};

	public static void main(String[] args) {

		boolean result = true;
		result = result && reverseStr("abcd").equals("dcba");

		if (result) {
			System.out.println("All tests pass");
		} else {
			System.out.println("There are test failures");
		}

	}
}