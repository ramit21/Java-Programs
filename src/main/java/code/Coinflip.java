package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/*
 * Given an array representing head and tails of coins, return min no. of flips required 
 * so that heads and tails alternate
 * eg. for [0,1,0] -> 0
 * [1,1,1,0] -> 1 (flip coin at second index)
 */
public class Coinflip {

	public static void main(String[] args) throws IOException {
		
		int [] T = new int[] {1, 1,0, 1,1};
		System.out.println(solution(T));
	}
	
	public static int solution(int[] A) {
		 // write your code in Java SE 8
		if(A.length < 2) return 0;
		return Math.min(countSwitches(A, 1, A[0]), 1 + countSwitches(A, 1, flip(A[0])));
    }
	
	private static int countSwitches(int[] A, int curIndex, int prevValue) {
		if(curIndex == A.length-1) {
			if(A[curIndex] == prevValue) {
				return 1;
			}else {
				return 0;
			}
		}
		if(A[curIndex] == prevValue) {
			return 1 + countSwitches(A, curIndex+1, flip(A[curIndex]));
		}else {
			return countSwitches(A, curIndex+1, A[curIndex]);
		}
	}
	
	private static int flip(int n) {
		if (n==0) {
			return 1;
		}else {
			return 0;
		}
	}

}
