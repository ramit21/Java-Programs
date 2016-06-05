import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// Sort a map as per its keys
public class SortingMapKeys {
Map <String,String> myMap = new HashMap<String,String>();
SortingMapKeys(){
	myMap.put("question 3", "3");
	myMap.put("question 1", "1");
	myMap.put("question 5", "5");
	myMap.put("question 9", "9");
	myMap.put("question 1", "1");
}

public void sortKEys() {
	TreeSet <String> keys = new TreeSet<String>(myMap.keySet());
	for(String key:keys){
		System.out.println(" "+myMap.get(key));
		
	}
	
}

public static void main(String[] args) {
	SortingMapKeys obj = new SortingMapKeys();
	obj.sortKEys();
}
}
