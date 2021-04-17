package algorithm;

public class SmallestNumber {

	/*
	 * public static int FindMin(int a[]) Returns the smallest number in array that
	 * has been rotated For example - Array {3,4,5,6,1,2} returns 1
	 * 
	 * https://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/
	 */

	public static int FindMin(int a[]) {
		return findMin(a, 0, a.length-1);
	}

	private static int findMin(int a[], int start, int end) {
		// This condition is needed to handle the case when array 
        // is not rotated at all 
        if (a[start] < a[end])  return a[0]; 
  
        // If there is only one element left 
		if(start == end) {
			return a[start];
		}
		
		if(start+1 == end) {
			return Math.min(a[start], a[end]);
		}
		
		int mid = (start + end)/2;
		if(a[mid] < a[mid-1]) {
			return a[mid];
		}else if(a[mid] < a[end]) {
			return findMin(a, start, mid-1);
		}else {
			return findMin(a, mid+1, end);
		}
		
	}

	public static void main(String args[]) {
		boolean result = true;
		result = result && FindMin(new int[] { 3, 4, 5, 6, 1, 2 }) == 1;
		result = result && FindMin(new int[] { 2, 1 }) == 1;
		result = result && FindMin(new int[] { 1 }) == 1;

		try {
			FindMin(null);
			result = false;
		} catch (Exception e) {
			result = result && true;
		}

		if (result) {
			System.out.println("All tests pass");
		} else {
			System.out.println("There are test failures");
		}
	}
}