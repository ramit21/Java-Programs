package algorithm;
/* Problem Name is &&& Snowpack &&& PLEASE DO NOT REMOVE THIS LINE. 
 * Solution: https://www.geeksforgeeks.org/trapping-rain-water/
 * Space com: O(n), time: O(n)
 * */

public class SnowPack {
	/*
	 ** Find the amount of snow that could be captured.
	 */
	public static Integer computeSnowpack(Integer[] arr) {
		int[] left = new int[arr.length];
		int[] right = new int[arr.length];
		
		left[0] = 0;
		for(int i=1; i < arr.length; i++) {
			left[i] = Math.max(arr[i], left[i-1]);
		}
		
		right[arr.length-1] = arr[arr.length-1];
		for(int i= arr.length-2; i >= 0; i--) {
			right[i] = Math.max(arr[i], right[i+1]);
		}
		
		int snow = 0;
		for(int i=0; i< arr.length; i++) {
			snow+= (Math.min(left[i], right[i]) - arr[i]);
		}
		
		return snow;
	}

	/*
	 ** Returns true if the tests pass. Otherwise, returns false;
	 */
	public static boolean doTestsPass() {
		boolean result = true;
		result &= computeSnowpack(new Integer[] { 0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0 }) == 13;

		return result;
	}

	/*
	 ** Execution entry point.
	 */
	public static void main(String[] args) {
		if (doTestsPass()) {
			System.out.println("All tests pass");
		} else {
			System.out.println("Tests fail.");
		}
		
		System.out.println(computeSnowpack(new Integer[] { 0, 1, 3, 0, 4 }));
	}
}