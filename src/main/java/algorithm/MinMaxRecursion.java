package algorithm;

/*
 * Given a number N the task is to find an optimal solution to reach N from 0 using 2 operations ie
1. Double the number 
2. Add one to the number

Input  : N = 8
Output : 4
0 + 1 = 1, 1 + 1 = 2, 2 * 2 = 4, 4 * 2 = 8

Input  : N = 7
Output : 5
0 + 1 = 1, 1 + 1 = 2, 1 + 2 = 3, 3 * 2 = 6, 6 + 1 = 7
 */
public class MinMaxRecursion {
	
	public static int findOptimalCount(int n){
		if(n==0){
			return 0;
		}
		
		return 1+ Math.min(findOptimalCount(n-1), findOptimalCount(n/2)+n%2);
	}
	
	public static void main(String[] args) {
		System.out.println(findOptimalCount(8));
		System.out.println(findOptimalCount(7));
		System.out.println(findOptimalCount(4));
	}

}
