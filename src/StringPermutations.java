/**
 * Generate all permutations of a given string For a given 'n', print all valid
 * combination of brackets. Eg. n=2, then (()), ()()
 */
public class StringPermutations {

	static void permute(int level, String permuted, boolean used[], String original) {
		int length = original.length();
		if (level == length) {
			System.out.println(permuted);
		} else {
			for (int i = 0; i < length; i++) {
				if (!used[i]) {
					used[i] = true;
					permute(level + 1, permuted + original.charAt(i), used, original);
					used[i] = false;
				}
			}
		}
	}

	static void generateBrackets(int leftCount, int rightCount, int n, String permuted) {
		if (leftCount == n) {
			for (int i = rightCount; i < n; i++) {
				permuted += ")";
			}
			System.out.println(permuted);
			return;
		} else {
			for (int i = leftCount; i < n; i++) {
				permuted += "(";
				leftCount++;
				generateBrackets(leftCount, rightCount, n, permuted);
				if (leftCount != n) { // if left count has reached end, then the
										// above recursive call will take care
										// of filling right brackets
					permuted += ")";
					rightCount++;
					generateBrackets(leftCount, rightCount, n, permuted);
				}
			}
		}
	}

	static void brackets2(int leftCount, int rightCount, int n, String permuted) {
		if (rightCount == n) {
			System.out.println(permuted);
			return;
		} else {
			if (leftCount > rightCount) {
				permuted += ")";
				brackets2(leftCount, rightCount + 1, n, permuted);
			}
			if (leftCount < n) {
				permuted += "(";
				brackets2(leftCount + 1, rightCount, n, permuted);
			}
		}

	}

	public synchronized static void main(String[] args) {
		String s = "hello";
		boolean used[] = { false, false, false, false, false };
		permute(0, "", used, s);
		generateBrackets(0, 0, 2, "");
	}
}
