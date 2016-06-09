package code;

//to check if a no is a power of two
public class PowerOf2 {
int no = 15;

public boolean isPowerOf2(){
	return ((no & -no) == no);
}

public static void main(String[] args) {
	PowerOf2 obj= new PowerOf2();
	
	System.out.println(	obj.isPowerOf2() );
	
	int i = 5;
	int j = -5;
	//move from right to left, leave the first 1 encountered, then flip all other bits (2's complement)
	String s1 = Integer.toBinaryString(i);
	String s2 = Integer.toBinaryString(j);
	
	System.out.println(s1+" "+s2);
}
}
