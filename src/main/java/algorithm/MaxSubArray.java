package algorithm;

/**
 * Given an array of positive and negative integers, find the maximum sum
 * sub-array in O(n)
 * Kadane's Algorithm
 * 
 */
public class MaxSubArray {
	
	//int arr[]={1,2,-7,0};
	int arr[] ={-78,1,-2};
	//int arr[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
	int start = 0;
	int end = 0;

	int MaxSum() {
		int maxSum = 0;
		int curStart= 0;
		int temp;
		int maxEndingHere = 0;
		for (int i = 0; i < arr.length; i++) {
			//Just keep track of curStart if sum turns negative
			temp = Math.max(arr[i], maxEndingHere + arr[i]);
			if (temp<0) {
				curStart = i+1;
			}
			maxEndingHere = temp;

			// Update Start and End when maxEndingHere > maxSum so far
			temp = Math.max(maxSum, maxEndingHere);
			if (temp > maxSum) {
				start = curStart;
				end = i;
			}
			maxSum = temp;
		}

		return maxSum;
	}

	public static void main(String[] args) {
		MaxSubArray obj = new MaxSubArray();
		System.out.println(" Max sum = " + obj.MaxSum() + ", start = " + obj.start + ", end = " + obj.end);
	}
}
