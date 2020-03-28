package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* (Societte Generale test)
	Pyramid Pattern Printing
Given a string, height and direction print the pyramid (see below).

The direction of movement alternates between left to right and right to left at every new line of output.

Input Format
The first line of input contains an integer t which is the number of test cases. Then t test cases follow.

Each test case contains three space separated tokens viz. the string s, height of the pyramid h. The next token is either 1 for upright pyramid and -1 for inverted pyramid.

Output Format
For each test case, print a pyramid as explained above.

Sample Input
2
abc 6 1
1234567890 10 -1
Sample Output
     a
    acb
   bcabc
  acbacba
 bcabcabca
cbacbacbacb
1234567890123456789
 65432109876543210
  789012345678901
   4321098765432
    56789012345
     432109876
      5678901
       65432
        789
         0

 */
class PyramidUpsideDown {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        List<Pyramid> pList = new ArrayList<Pyramid>();
        for (int n = 0; n < t; n++) {
        	String str = sc.next();
        	int h = sc.nextInt();
        	int dir = sc.nextInt();
        	if(h<1 || h>128 || str.length()<1 || str.length()> 16) {
        		continue;
        	}
        	Pyramid p = new Pyramid(str, h, dir);
        	pList.add(p);
        }
        pList.stream().forEach(p-> p.print());
    }
    
}

class Pyramid{
	
	private final String str;
	private final int h;
	private final int dir;
	private int ind;
	private boolean rev;
	
	Pyramid(String str, int h, int dir){
		this.str=str;
		this.h=h;
		this.dir=dir;
	}
	
	public void print() {
		if(dir == 1) {
			for(int i=1;i<=h;i++) {
				prepare(i);
			}
		}else {
			for(int i=h;i>=1;i--) {
	    		prepare(i);
	    	}
		}
    }

	private void prepare(int i) {
		for(int k=0;k<h-i; k++) {
			System.out.print(" ");
		}
		StringBuilder sb= new StringBuilder("");
		for(int k=0; k<2*i-1; k++) {
			sb.append(getChar());
		}
		if(rev) sb=sb.reverse();
		System.out.println(sb.toString());
		rev = !rev;
	}
	
	private char getChar() {
		if(ind == str.length()) {
			ind = 0;
		}
		return str.charAt(ind++);
	}
}
