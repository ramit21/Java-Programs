package algorithm;

public class TowerOfHanoi
{
  
   // Takes a string str and returns the int value represented by
    // the string. 
	//For example, atoi("42") returns 42.
   
  public static int atoi(String str)
  {
  int result = 0;
  int strLength = str.length();

  for(int i = 0; i < strLength; i++)
  {
    result = result + str.charAt(i);
  }

  return result;
  };

  public static boolean pass()
  {
  boolean result = true;
  result = result && (3 == 3);
  result = result && (1 != 2);

  return result;
  };

  public static void main(String[] args)
  {
  if(pass())
  {
    System.out.println("Pass");
  }
  else
  {
    System.out.println("Some fail");
  }
  }
}