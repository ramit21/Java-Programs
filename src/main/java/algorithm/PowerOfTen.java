package algorithm;

public class PowerOfTen {
	/**
	 * Returns true if y is a power-of-10.
	 * 
	 * To check if y is power of x, simple sol. is to calculate powers of x and see
	 * if they become equal to y or if they crossover y
	 * 
	 * The more optimal solution is given below
	 */
	public static boolean isPowerOf10(int x, int y) {
		// logarithm function to
		// calculate value
		int res1 = (int) Math.log(y) / (int) Math.log(x);

		// Note : this is double
		double res2 = Math.log(y) / Math.log(x);

		// compare to the result1 or
		// result2 both are equal
		System.out.println(res1+", res2 = "+res2);
		return (res1 == res2);
	}

	public static boolean doTestsPass() {
		int[] isPowerList = { 1000};
		int[] isNotPowerList = { 3, 1100 };

		for (int i : isPowerList) {
			if (!isPowerOf10(10, i)) {
				System.out.println("Test failed for: " + i);
				return false;
			}
		}

		for (int i : isNotPowerList) {
			if (isPowerOf10(10, i)) {
				System.out.println("Test failed for: " + i);
				return false;
			}
		}

		System.out.println("All tested passed");
		return true;
	};

	public static void main(String args[]) {
		doTestsPass();
	}
}