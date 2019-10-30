package algorithm;

/* 
 * Approach: https://www.youtube.com/watch?v=LPFhl65R7ww 
 */
public class MedianOfTwoSortedArrays {

	// a: 1,2,5,11,15 // b: 3 4 13 17 18
	public double findMedianSortedArrays(int input1[], int input2[]) {
        //if input1 length is greater than switch them so that input1 is smaller than input2.
        if (input1.length > input2.length) {
            return findMedianSortedArrays(input2, input1);
        }
        int xlength = input1.length;
        int ylength = input2.length;
        int low = 0;
        int high = xlength;
        while (low <= high) {
            int partitionX = (low + high)/2;
            int partitionY = (xlength + ylength + 1)/2 - partitionX; 
            //+1 here takes care of both even and odd before divide by 2

          System.out.println(partitionX +"\t"+partitionY);
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
            int minRightX = (partitionX == xlength) ? Integer.MAX_VALUE : input1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
            int minRightY = (partitionY == ylength) ? Integer.MAX_VALUE : input2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) { //Break condition
              
                if ((xlength + ylength) % 2 == 0) {
                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                } else {
                    return (double)Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
                high = partitionX - 1;
            } else { //we are too far on left side for partitionX. Go on right side.
                low = partitionX + 1;
            }
        }

        //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        throw new IllegalArgumentException();
    }





	public static void main(String[] args) {

		MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();

		System.out.println("Case 1: When arrays have odd number of elements in them.");
		int[] a = { 1, 2, 3, 4, 5 };
		int[] b = { 6, 7, 8, 9, 10 };

		System.out.println("Median: " + solution.findMedianSortedArrays(a, b));

		System.out.println("-----------------");

		System.out.println("Case 2: When arrays have even number of elements in them.");
		int[] c = { 1, 2, 99, 100 };
		int[] d = { 4, 5, 101, 102 };

		System.out.println("Median: " + solution.findMedianSortedArrays(c, d));
		
		int[] e = { -9,-4,0,3,6,12};
		int[] f = { -3,1,5};

		System.out.println("Median: " + solution.findMedianSortedArrays(f, e));
	}
}