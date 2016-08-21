package code;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

public class SortingMapKeys {
	private final Map<String, Integer> myMap;

	public SortingMapKeys() {
		myMap = new HashMap<String, Integer>();
		myMap.put("a", 3);
		myMap.put("b", 1);
		myMap.put("c", 3);
		myMap.put("d", 5);
		myMap.put("e", 7);
		myMap.put("f", 8);
		myMap.put("g", 7);
	}

	public void sortByKeys() {// or use a treeMap directly
		TreeSet<String> keys = new TreeSet<String>(myMap.keySet());
		for (String key : keys) {
			System.out.println(key + " => " + myMap.get(key));
		}

	}

	public void sortByValues() {
		System.out.println("Sorted by Value:");
		// 1. Convert Map to List of Map
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(myMap.entrySet());
		// 2. Sort list with Collections.sort(), provide a custom Comparator
		// Try switch the o1 o2 position for a different order
		Collections.sort(list, new ValueComparator());
		// 3. Loop the sorted list and put it into a new LinkedHashMap
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		for (String key : sortedMap.keySet()) {
			System.out.println(key + " => " + sortedMap.get(key));
		}

	}

	public static void main(String[] args) {
		SortingMapKeys obj = new SortingMapKeys();
		obj.sortByKeys();
		obj.sortByValues();
	}
}

class ValueComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		return o1.getValue().compareTo(o2.getValue());
	}

}
