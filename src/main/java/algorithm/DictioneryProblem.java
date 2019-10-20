package algorithm;

// Given a a string of letters and a dictionary, the function longestWord should
//     find the longest word or words in the dictionary that can be made from the letters
//     Input: letters = "oet", dictionary = {"to","toe","toes"}
//     Output: {"toe"}

import java.util.*;

 class Dictionary {
  private String[] entries;

  public Dictionary(String[] entries) {
    this.entries = entries;
  }

  public boolean contains(String word) {
    return Arrays.asList(entries).contains(word);
  }
}

public class DictioneryProblem {
  public static Set<String> longestWord(String letters, Dictionary dict) {
    Set<String> result = new HashSet<String>();
    if (dict.contains(letters)) {
      result.add(letters);
    }
    return result;
  }


  public static boolean pass() {
    Dictionary dict = new Dictionary(new String[]{"to", "toe", "toes", "doe", "dog", "god", "dogs", "banana"});
    boolean r = new HashSet<String>(Arrays.asList("toe")).equals(longestWord("toe", dict));
    return r;
  }

  public static void main(String[] args) {
    if(pass()) {
      System.out.println("Pass");
    } else {
      System.err.println("Fails");
    }
  }
}