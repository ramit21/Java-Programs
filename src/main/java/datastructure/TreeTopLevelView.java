package datastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TreeTopLevelView {
	
	static void  topView(TreeNode root) {
	       class QueueData{
	    	   TreeNode node;
	           int hd;
	           public QueueData(TreeNode node, int hd){
	               this.node = node;
	               this.hd = hd;
	           }
	       }
	       
	       Map<Integer, Integer> map = new TreeMap<Integer, Integer>(); //Tree map to maintain sorting by hd
	       
	       List<QueueData> queue = new LinkedList<QueueData>(); //BFS with QueueData maintaining hd
	       queue.add(new QueueData(root, 0));
	       
	       while(!queue.isEmpty()){
	           QueueData qd = queue.remove(0);
	           TreeNode node = qd.node;
	           int hd = qd.hd;
	           if(!map.containsKey(hd)){
	               map.put(hd, node.data);
	           }
	           if(node.left != null) queue.add(new QueueData(node.left, hd-1));
	           if(node.right!= null) queue.add(new QueueData(node.right,hd+1));
	       }
	       
	       for(Map.Entry<Integer, Integer> entry: map.entrySet()){
	           System.out.print(entry.getKey()+" "+entry.getValue() + "\n");
	       }
	    
	    }
	/* Tree being tested:
	 *    		5
	 * 		4	
	 * 	3
	 * 		2
	 * 			1
	 * 				0
	 */
	
	
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(5);
		TreeNode n2 = new TreeNode(4);
		n1.left = n2;
		TreeNode n3 = new TreeNode(3);
		n2.left = n3;
		TreeNode n4 = new TreeNode(2);
		n3.right = n4;
		TreeNode n5 = new TreeNode(1);
		n4.right = n5;
		TreeNode n6 = new TreeNode(0);
		n5.right = n6;
		//TreeNode n7 = new TreeNode(6);
		//n1.right = n7;
		
		topView(n1);
		
	}

}

class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int data){
		this.data = data;
	}
}
