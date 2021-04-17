package algorithm;

public class Power {

  /* Given base and integer exponent, compute value of base raised to the power of exponent.
   */
  public static double power(double base, int exp) {
	if(exp ==0) { //edge case
		return 1.0;
	}
    int modExp = Math.abs(exp);
    double finalVal = base;
    for(int i=2;i<= modExp; i++) {
    	finalVal*= base;
    }
    if(exp<0) {
    	finalVal = 1/finalVal;
    }
    return finalVal;
  }

  public static boolean doTestPass() {
    return power(2,3)==8.0 &&
    	   power(2,1)==2.0 &&
    	   power(2,0)==1.0 &&
    	   power(2,-4) == 0.0625;
  }

  public static void main( String[] args ) {
    if(doTestPass()){
      System.out.println("Pass");
    }
    else{
      System.out.println("There are failures");
    }
  }
}