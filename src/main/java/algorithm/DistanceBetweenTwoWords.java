package algorithm;

public class DistanceBetweenTwoWords {

  
   // Input two words returns the shortest distance between their two midpoints in number of characters
   // Words can appear multiple times in any order and should be case insensitive.
   
   // E.g. for the document="Example we just made up"
   //   shortestDistance( document, "we", "just" ) == 4
   // https://www.geeksforgeeks.org/minimum-distance-between-words-of-a-string/
   
  public static double shortestDistance(String document, String word1, String word2) {
    double shortest = document.length();
    

    return shortest;
  }

  public static boolean pass() {
    return  shortestDistance(document, "and", "graphic") == 6d &&
        shortestDistance(document, "transfer", "it") == 14d &&
        shortestDistance(document, "Design", "filler" ) == 25d ;
  }

  public static void main(String[] args) {
    if (pass()) {
      System.out.println("Pass");
    } else {
      System.out.println("Some Fail");
    }
  }

  private static final String document;
  static{
    StringBuffer sb = new StringBuffer();
    sb.append("In publishing and graphic design, lorem ipsum is a filler text commonly used to demonstrate the graphic elements");
    sb.append(" lorem ipsum text has been used in typesetting since the 1960s or earlier, when it was popularized by advertisements");
    sb.append(" for Letraset transfer sheets. It was introduced to the Information Age in the mid-1980s by Aldus Corporation, which");

    document = sb.toString();
  }
}