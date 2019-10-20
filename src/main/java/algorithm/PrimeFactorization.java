package algorithm;

import java.io.*;
import java.util.*;

public class PrimeFactorization
{
	/**
	   * Return an array containing prime numbers whose product is x
	   * Examples:
	   * primeFactorization( 6 ) == [2,3]
	   * primeFactorization( 5 ) == [5]
	   */
  public static ArrayList<Integer> primeFactorization(int x)
  {
  return (ArrayList<Integer>) Arrays.asList(2,3);
  }

public static void main(String args[])
  {
	
	System.out.println(primeFactorization(6) + " " + primeFactorization(5)); 
	if(primeFactorization(6).equals(Arrays.asList(2,3)) 
			&& 
			primeFactorization(5).equals(Arrays.asList(5))
			) {
		System.out.println("All passed");
	}else {
		System.out.println("Failed");
	}
  
  }
}