package code;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Depth first and Breadth first tree traversal
 *
 */
public class BinaryTree {
	public static void main(String[] args) {
		Node root = constructTree();
		System.out.println("In Order Trversal: ");
		inOrderTraversal(root);
		System.out.println("\nBFS Trversal: ");
		breadthFirstTraversal(root);
	}

	private static void inOrderTraversal(Node root) {
		if(root == null){
			return;
		}
		inOrderTraversal(root.getLeft());
		System.out.print(root.getValue()+" ");
		inOrderTraversal(root.getRight());
	}
	
	private static void breadthFirstTraversal(Node root){
		Queue <Node>queue = new LinkedList<Node>();
		queue.add(root);
		Node cur = null;
		while(!queue.isEmpty()){
			cur = queue.poll();
			System.out.println(cur.getValue());
			if(cur.getLeft()!=null){
				queue.add(cur.getLeft());
			}
			if(cur.getRight()!=null){
				queue.add(cur.getRight());
			}
		}
	}
	
	private static Node constructTree() {
		Node n1 = new Node (null,null,"7");
		Node n2 = new Node (n1,null,"5");
		Node n3 = new Node (null,null,"4");
		Node n4 = new Node (n3,n2,"2");
		Node n5 = new Node (null,null,"6");
		Node n6 = new Node (n5,null,"3");
		Node n7 = new Node (n4,n6,"1");
		return n7;
	}
}

class Node{
	Node left;
	Node right;
	String value;
	public Node(Node left, Node right, String value){
		this.left = left;
		this.right = right;
		this.value = value;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}