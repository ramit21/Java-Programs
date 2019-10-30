package algorithm;

// https://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/
public class SubArrayExceedingSum {
	
	public static int subArrayExceedsSum(int arr[], int target) {
		return 0;
	}

	public static void main(String[] args) {
		boolean result = true;
		int[] arr = { 1, 2, 3, 4 };
		result = result && subArrayExceedsSum(arr, 6) == 2;
		result = result && subArrayExceedsSum(arr, 12) == -1;

		if (result) {
			System.out.println("All tests pass\n");
		} else {
			System.out.println("There are test failures\n");
		}
	}
};