package algorithm;

public class SquareRoot {
	/*
	 * A More Optimal solution is here:
	 * https://www.geeksforgeeks.org/find-square-root-number-upto-given-precision-
	 * using-binary-search/
	 *
	 */

	public static double squareRoot(double no, int precision) {
		double squareRoot = 0;
		for(int i=1; i<= no; i++) {
			if(i*i == no) {
				return i;
			}
			if(i*i > no) {
				squareRoot = i-1;
			}
		}
		double increment = 0.1;
		for(int j=0; j< precision; j++) {
			while(squareRoot*squareRoot < no) {
				squareRoot+=increment;
				if(squareRoot*squareRoot == no) {
					return no;
				}
			}
			squareRoot-=increment;
			increment /= 10;
		}
		
		return squareRoot;
	}

	public static void main(String args[]) {
		double[] inputs = { 2, 4, 100 };
		double[] expected_values = { 1.41421, 2, 10 };
		double threshold = 0.001;
		for (int i = 0; i < inputs.length; i++) {
			if (Math.abs(squareRoot(inputs[i], 6) - expected_values[i]) > threshold) {
				System.out.printf("Test failed for %f, expected=%f, actual=%f\n", inputs[i], expected_values[i],
						squareRoot(inputs[i], 6));
			}
		}
		System.out.println("All tests passed");
	}
}