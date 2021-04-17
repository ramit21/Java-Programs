package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//  Find the best average grade.
//  Given a list of student test scores
//  Each student may have more than one test score in the list.

public class BestAverageGrade {
	
	public static Double bestAvgGrade(String[][] scores) {
		Map<String, List<Integer>> avgScoreListMap = new HashMap<String, List<Integer>>();

		Arrays.stream(scores).forEach(s -> {
			List<Integer> scoreList = avgScoreListMap.get(s[0]);
			if (scoreList == null) {
				scoreList = new ArrayList<Integer>();
			}
			scoreList.add(Integer.valueOf(s[1]));
			avgScoreListMap.put(s[0], scoreList);
		});

		//Converting a map of list a map of double
		Map<String, Double> avgScoreMap = avgScoreListMap.entrySet().stream().collect(Collectors.toMap(
				e -> e.getKey(),
				e -> e.getValue().stream().mapToInt(v -> v).average().getAsDouble()
				));
		return avgScoreMap.values().stream().mapToDouble(v -> v).max().getAsDouble();
	}

	public static void main(String[] args) {
		String[][] s1 = { { "Rohan", "59" }, { "Sachin", "102" }, { "Ishan", "40" }, { "Sachin", "18" } };
		Double expectedScore = new Double(60.0);
		int result = bestAvgGrade(s1).compareTo(expectedScore);
		if(result == 0) {
			System.out.println("Pass"); 
		}else {
			System.out.println("Fail");
		}
	}
}
