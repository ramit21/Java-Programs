package algorithm;
/*
 * https://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/
 * Naive solution is to use two loops: outer for sub array start, and inner for sub array end
 * Better solution given belo for O(n) solution
 */

public class SubArrayExceedingSum {
	
	public static int subArrayExceedsSum(int arr[], int target) {
		int start = 0;
		int end = 0;
		int curSum =0;
		int n = arr.length;
		int minLength = n+1;
		
		while(end<n) {
			//Move end till cursum less/equal to target
			while(end< n && curSum<= target) {
				curSum+= arr[end++];
			}
			
			//Once cursum have exceeded the target, try moving the start to find smallest (end-start)
			while(start< n && curSum > target) {
				minLength = Math.min(minLength, end-start);
				curSum-= arr[start++];
			}
			
		}
		
		if(start ==0 && end == n && curSum <= target) {
			return -1;// Special handling if elements never add up
		}
		
		return minLength;
	}

	public static void main(String[] args) {
		boolean result = true;
		int[] arr = { 1, 2, 3, 4 };
		result = result && subArrayExceedsSum(arr, 6) == 2;
		result = result && subArrayExceedsSum(arr, 12) == -1;
		
		int[] arr2 = {1, 4, 45, 6, 0, 19};
		result = result && subArrayExceedsSum(arr2, 51) == 3;

		if (result) {
			System.out.println("All tests pass\n");
		} else {
			System.out.println("There are test failures\n");
		}
	}
};