package algorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an array of integers. Starting from the first element of the array, one can take steps equal or less than the number at that index.
 * For Example, if value at current index is 3, then one can move either 1, 2 or 3 steps further, and likewise.
 * A 0 is a dead end. If you step on it, then you can not proceed any further.
 * Find the minimum jumps required to reach the end of the array. 
 */
public class FroggyJump {
	
	public static void main(String[] args) {
		int[] arr = {2,1,3,0,3,0,1};
		System.out.println(minJumpsBfs(arr));
		System.out.println(minJumpsDfs(arr, 0, 0));//no path exists if MAX_VALUE is returned
	}
	
	private static int minJumpsDfs(int[] arr, int index, int jumps){
		int cur = arr[index];
		if(cur == 0 || index >= arr.length){
			return Integer.MAX_VALUE;
		}
		List<Integer> curJumps = new ArrayList<>();
		for(int i=1; i<=cur; i++){
			if(index+i == arr.length){
				return jumps+1;
			}else{
				curJumps.add(minJumpsDfs(arr, index + i, jumps+1));
			}
		}
		Collections.sort(curJumps);
		return curJumps.get(0);
	}
	
	private static int minJumpsBfs(int[] arr){
		Queue<JumpEntry> q = new LinkedList<JumpEntry>();
		q.add(new JumpEntry(0, arr[0], 0));
		List<Integer> jumps = new LinkedList<Integer>();
		while(!q.isEmpty()){
			JumpEntry entry = q.poll();
			for(int i=1; i<= entry.value; i++){
				if(entry.index + i == arr.length-1){//record no. of jumps if last element reached
					jumps.add(entry.jumps + 1);
				}else if((entry.index + i < arr.length-1) && arr[entry.index + i]!=0){
					JumpEntry newEntry = new JumpEntry(entry.index + i, arr[entry.index + i], entry.jumps + 1);
					q.add(newEntry);
				}
			}
		}
		Collections.sort(jumps); // find the shortest jump
		if(jumps.size()>0){
			return jumps.get(0);
		}
		return -1;
	}
}

class JumpEntry{
	int index;
	int value;
	Integer jumps;
	
	public JumpEntry(int index, int value, int jumps){
		this.index = index;
		this.value = value;
		this.jumps = jumps;
	}

	@Override
	public String toString() {
		return index + " " + value + " " + jumps;
	}
	public int getIndex() {
		return index;
	}

	public int getValue() {
		return value;
	}

	public int getJumps() {
		return jumps;
	}
}
