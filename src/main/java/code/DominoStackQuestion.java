package code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
 * You are given 6 dominos. Each have two parts. Each part has a no on it. Task is to return YES
 * (else NO) if dominos can be stacked on top of each other to form a pyramid, so that all
 * cells in a column have equal values. Given an array of 12 nos, each pair of no. starting from 0 
 * represent (x,y)-> the 2 halves of 1 domino. The x,y can be rotated to become y,x.
 *  Eg. for the i/p [3,2, 7,3, 5,9, 7,3, 2,5, 2,3], one possible pyramid is as below:
 *  
 *  			|2,3|
 *  		  |5,2|3,7|
 *  	    |9,5|2,3|7,3|
 */
public class DominoStackQuestion {

	private final static Character A = 'a';
	private final static Character B = 'b';

	public static void main(String[] args) throws IOException {
		//int[] A = { 4, 3, 3, 4, 1, 2, 2, 1, 6, 5, 4, 5 };
		//int[] A = { 0, 0, 0, 1, 1, 12, 1, 0, 1, 1, 0, 5 };
		int[] A = { 3,2, 7,3, 5,9, 7,3, 2,5, 2,3};
		System.out.println("Solution: " + solution(A));
	}

	private static String solution(int[] arr) {
		
		List<Domino> dominoList = new ArrayList<>();
	
		for(int i = 0; i< arr.length-1; i+= 2) {
			Domino domino = new Domino(arr[i],arr[i+1]);
			dominoList.add(domino);
		}
		int [] usedArr = new int[6];
		printPyramid(dominoList);
		boolean result = createStack(dominoList, new ArrayList<>(), usedArr);
		return (result == true)? "YES" : "NO";
	}

	private static boolean createStack(List<Domino> dominoList, List<Domino> dominoListSoFar, int[] used) {
		int curIndex = dominoListSoFar.size();
		if(curIndex == dominoList.size()) {
			System.out.println("SOLUTION");
			printPyramid(dominoListSoFar);
			return true;
		}
		for (int i = 0; i < dominoList.size(); i++) {
			if (used[i] == 0) {
				used[i] = 1;
				Domino curDomino1 = new Domino(dominoList.get(i));
				if(isValidMove(dominoListSoFar, curDomino1)) {
					List<Domino> newDominoListThusFar1 = new ArrayList<Domino>(dominoListSoFar);
					newDominoListThusFar1.add(curDomino1);
					boolean result = createStack(dominoList, newDominoListThusFar1, used);
					if(result == true) {
						return true;
					}
				}
				Domino curDomino2 = new Domino(curDomino1.y, curDomino1.x);
				if(isValidMove(dominoListSoFar, curDomino2)) {
					List<Domino> newDominoListThusFar2 = new ArrayList<Domino>(dominoListSoFar);
					newDominoListThusFar2.add(curDomino2);
					boolean result = createStack(dominoList, newDominoListThusFar2, used);
					if(result == true) {
						return true;
					}
				}
				used[i] = 0;
			}
		}
		return false;
	}

	private static void printPyramid(List<Domino> dominoList) {
		dominoList.forEach(d-> {
			System.out.println(d);
		});
	}
	
	private static boolean isValidMove(List<Domino> dominoList, Domino curDomino) {
		if(dominoList.isEmpty()) {
			return true;
		}
		if(dominoList.size() == 1 && dominoList.get(0).getX() == curDomino.getY()) {
			return true;
		}
		if(dominoList.size() == 2 && dominoList.get(0).getY() == curDomino.getX()) {
			return true;
		}
		if(dominoList.size() == 3 && dominoList.get(1).getX() == curDomino.getY()) {
			return true;
		}
		if(dominoList.size() == 4 && dominoList.get(1).getY() == curDomino.getX() &&
								dominoList.get(2).getX() == curDomino.getY()) {
			return true;
		}
		if(dominoList.size() == 5 && dominoList.get(2).getY() == curDomino.getX()) {
			return true;
		}
		return false;
	}
}

class Domino {
	final int x;
	final int y;

	public Domino(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Domino(Domino d) {
		this.x = d.x;
		this.y = d.y;
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}