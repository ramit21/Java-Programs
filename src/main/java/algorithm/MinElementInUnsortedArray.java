package algorithm;
/*
 * O(log(n)) solution using binary search like divide and conquer
 */
public class MinElementInUnsortedArray {

	public static int minElement(int[] arr, int start, int end) {
		if (start == end) {
			return arr[start];
		}

		if (start == end - 1) {
			return Math.min(arr[start], arr[end]);
		}

		int mid = (start + end) / 2;
		return Math.min(minElement(arr, start, mid), minElement(arr, mid + 1, end));
	}

	public static void main(String[] args) {
		boolean isPass = true;
		int[] arr = new int[] { 1, 2 };
		isPass &= (minElement(arr, 0, arr.length - 1) == 1);
		arr = new int[] { -3, 2 ,8,12,0,9,0 };
		isPass &= (minElement(arr, 0, arr.length - 1) == -3);
		arr = new int[] { 9, 2 ,8,12,0,9,-4 };
		isPass &= (minElement(arr, 0, arr.length - 1) == -4);
		arr = new int[] { 0,0,0 };
		isPass &= (minElement(arr, 0, arr.length - 1) == 0);
		if (isPass) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}
	}

}
