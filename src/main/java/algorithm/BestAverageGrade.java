package algorithm;

import java.io.*;
import java.util.*;

//  Find the best average grade.
//  Given a list of student test scores
//  Each student may have more than one test score in the list.


public class BestAverageGrade
{
  public static Integer bestAvgGrade(String[][] scores)
  {
    // write your code goes here
    return 0;
  }

  public static boolean pass()
  {
    String[][] s1 = { { "Rohan", "84" },
               { "Sachin", "102" },
               { "Ishan", "55" },
               { "Sachin", "18" } };

    return bestAvgGrade(s1) == 84;
  }

  public static void main(String[] args)
  {
    if(pass())
    {
      System.out.println("Pass");
    }
    else
    {
      System.out.println("Some Fail");
    }
  }
}


