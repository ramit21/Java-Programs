package algorithm;

public class Atoi {

	// Takes a string str and returns the int value represented by
	// the string.
	// For example, atoi("42") returns 42.

	public static int atoi(String str) {
		int result = 0;
		int i=0;
		boolean isNegative = false;
		if(str.charAt(0)=='-') {
			isNegative = true;
			i++;
		}
		
		for(; i< str.length(); i++) {
			result = result*10 + (str.charAt(i) - 48); //ASCII of numbers start from 48
		}
		
		if(isNegative) {
			result = -1*result;
		}
		
		return result;
	};

	public static boolean pass() {
		boolean result = true;
		result = result && (atoi("35") == 35);
		result = result && (atoi("-31") == -31);
		result = result && (atoi("0") == 0);

		return result;
	};

	public static void main(String[] args) {
		if (pass()) {
			System.out.println("Pass");
		} else {
			System.out.println("Some fail");
		}
	}
}