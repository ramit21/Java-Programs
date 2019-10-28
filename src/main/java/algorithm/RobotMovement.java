package algorithm;

import java.io.*;
import java.util.*;


//Implement the 'walk' method.
//Assume robot inital position is (0,0).
//walk method take path where each character corresponds to a movement of the robot.
// The robot moves up, down, left, and right represented by the characters 'U', 'D', 'L', and 'R'
// Ignore other characters.


public class RobotMovement{

  // your code in this function
  public static Integer[] walk( String path ) {
	  int x = 0;
	  int y = 0;
	  
	  char[] arr = path.toCharArray();
	  for(int i=0; i< arr.length; i++) {
		  if(arr[i] == 'U') y++;
		  if(arr[i] == 'D') y--;
		  if(arr[i] == 'L') x--;
		  if(arr[i] == 'R') x++;
	  }
	  
	  return new Integer[] {x, y};
  }

  public static boolean isEqual(Integer[] a, Integer[] b) {
	  return Arrays.equals(a, b);
  }

  public static boolean pass() {
  boolean res = true;

  {
    String test = "UUU";
    Integer[] result = walk(test);
    res &= isEqual(result, new Integer[]{0, 3});
  }

  {
    String test = "ULDR";
    Integer[] result = walk(test);
    res &= isEqual(result, new Integer[]{0, 0});
  }

  {
    String test = "ULLLDUDUURLRLR";
    Integer[] result = walk(test);
    res &= isEqual(result, new Integer[]{-2, 2});
  }

  {
    String test = "UP LEFT 2xDOWN DOWN RIGHT RIGHT UP UP";
    Integer[] result = walk(test);
    res &= isEqual(result, new Integer[]{1, 1});
  }

  return res;
  }

  public static void main(String[] args) {
  if(pass()){
    System.out.println("Pass");
  } else {
    System.out.println("Test failures");
  }
  }
}