package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * This program prints set of anagrams together in given string 
 * 
 * eg. 
 * setOfAnagrams("cat dog tac sat tas god dog") should print "cat tac dog god dog sat tas"
 *
 */

public class GroupAnagram {
  
  static String input = "cat dog tac sat tas god dog";
  
  static void setOfAnagrams(String inputString){ 
    
    String[] stringArr = input.split("\\s+");
    
   Map<String, List<String>> groupedMap = new LinkedHashMap<>();
   
    Arrays.stream(stringArr).forEach(s-> {
    	String sorted = sortCharacters(s);
    	List<String> groupedList = groupedMap.get(sorted);
    	if(groupedList == null) {
    		groupedList = new ArrayList<>();
    	}
    	groupedList.add(s);
    	groupedMap.put(sorted, groupedList);
    });
    
    StringBuffer sb = new StringBuffer();
    groupedMap.values().stream().forEach(l-> {
    	String str = l.stream().map(Object::toString).collect(Collectors.joining(" ")); //Note how to convert list to a string
    	sb.append(str);
    	sb.append(" ");
    }); 
    
    System.out.println(sb.toString());
  }
  
  private static String sortCharacters(String str) {
	  char[] charArr = str.toCharArray();
	  Arrays.sort(charArr);
	  return new String(charArr);
  }
  
  public static void main(String[] args) {
    
    String input = "cat dog tac sat tas god dog";
    setOfAnagrams(input);
    
  }
}