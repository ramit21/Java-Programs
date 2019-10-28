package algorithm;

/*
**  Below formation is called Pascals Triangle.
**
**  Example:
**               1
**              1 1
**             1 2 1
**            1 3 3 1
**           1 4 6 4 1
**         1 5 10 10 5 1
**        1 6 15 20 15 6 1
**
**  Complete the 'pascal' function below so that given a
**  col and a row it will return the value in that position.
**
**  Example, pascal(1,2) should return 2
**
*/
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PascalTriangle {

  public static  int pascal(int col, int row){
	  //prepare the first 2 rwos
	Map<Integer, List<Integer>> pascalMap = new LinkedHashMap<>();  
	List<Integer> pascalList = new ArrayList<Integer>();
	pascalList.add(1);
	pascalMap.put(0, pascalList);
	
	pascalList = new ArrayList<Integer>();
	pascalList.add(1);
	pascalList.add(1);
	pascalMap.put(1, pascalList);
	
	//prepare the remaining rows starting and endning at 1, and rest as per prev rows values
	List<Integer> prevList = null;
	for(int i=2; i<= row; i++) {
		prevList = pascalMap.get(i-1);
		List<Integer> newList = new ArrayList<Integer>();
		newList.add(1);
		for(int j=0; j< prevList.size()-1; j++) {
			newList.add(prevList.get(j) + prevList.get(j+1));
		}
		newList.add(1);
		pascalMap.put(i, newList);	
	}
    return pascalMap.get(row).get(col);
  }

  public static void main(String[] args) {
	  if(PascalTriangle.pascal(0,0) == 1 &&
			  PascalTriangle.pascal(1,2) == 2 &&
			  PascalTriangle.pascal(5,6) == 6 &&
			  PascalTriangle.pascal(4,8) == 70 &&
			  PascalTriangle.pascal(6,6) == 1) {
		  System.out.println("Pass");		  
	  }else {
		  System.out.println("Failed");
	  }
  }
}
