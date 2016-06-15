package code;

/**
 * Given an array of positive and negative integers, find the maximum sum
 * sub-array in O(n)
 * 
 */
public class MaxSubArray {

	// int arr[]={1,2,-7,0};
	// int arr[] ={-78,1,-2};
	int arr[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
	int start = 0;
	int end = 0;

	int MaxSum() {
		int sum = 0;

		int temp;
		int maxEndingHere = 0;
		for (int i = 0; i < arr.length; i++) {
			// maxEndingHere = Math.max(0,maxEndingHere + arr[i]);
			// sum = Math.max(sum,maxEndingHere);

			// starting index when sub array sum becomes positive
			temp = Math.max(0, maxEndingHere + arr[i]);
			if (maxEndingHere == 0 && temp != 0) {
				start = i;
			}
			maxEndingHere = temp;

			// Last index when the final max sum changes
			temp = Math.max(sum, maxEndingHere);
			if (temp != sum) {
				end = i;
			}
			sum = temp;
		}

		return sum;
	}

	public static void main(String[] args) {
		MaxSubArray obj = new MaxSubArray();
		System.out.println(" Max sum = " + obj.MaxSum() + ", start = " + obj.start + ", end = " + obj.end);
	}
}
