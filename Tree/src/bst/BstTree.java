package bst;

import java.util.LinkedList;

public class BstTree {
	BstNode root;
	
	public BstTree() {
		this.root = null;
	}
	
	private boolean add(Comparable e){
		BstNode newNode = new BstNode(e);
		if (root == null) root = newNode;
		else {
			BstNode nodeBefore = root;
			BstNode nodeAfter = null;
			while(nodeBefore != null && nodeBefore.key.compareTo(e) != 0){
				nodeAfter = nodeBefore;
				if(nodeBefore.key.compareTo(e) > 0){
					nodeBefore = nodeBefore.left;
				}else nodeBefore = nodeBefore.right;
			}
			
			if(nodeBefore != null) return false;
			
			if(nodeAfter.key.compareTo(e) > 0) nodeAfter.left = newNode;
			else nodeAfter.right = newNode;
			return true;
		}
		return true;
	}
	
	public void add(Comparable ... e){
		for (Comparable comparator : e) this.add(comparator);
	}
	public void printLevelBased(){
		if(this.root == null) System.out.println("Tree empty");
		else{
			LinkedList<BstNode> queue = new LinkedList<>();
			queue.addLast(this.root);
			while(!queue.isEmpty()){
				BstNode node = queue.removeFirst();
				System.out.println(node + ", ");
				if(node.left != null) queue.addLast(node.left);
				if(node.right != null) queue.addLast(node.right);
			}
		}
	}
	private void printNLR(BstNode node){
		if(node != null){
			System.out.println(node + ", ");
			printNLR(node.left);
			printNLR(node.right);
		}
	}
	public void printNLR(){
		if(root != null){
			printNLR(root);
		}else System.out.println("Tree is empty");
	}
	
	private void printAlign(BstNode node, int space){
		if(node != null){
			System.out.print(" ".repeat(space));
			System.out.println(node);
			printAlign(node.left,space + 3);
			printAlign(node.right,space + 3);
		}
	}
	public void printAlign(){
		if(root != null) printAlign(root, 0);
		else System.out.println("tree empty");
	}
	public BstNode search(Comparable value){
		BstNode result = root;
		while(result != null){
			if(result.key.compareTo(value) == 0)return result;
			else if(result.key.compareTo(value) > 0) result = result.left;
			else result = result.right;
		}
		return null;
	}

	public Comparable getMax(){
		if(root == null)
			throw new RuntimeException("empty tree");
		BstNode node = root.right;
		while(node.right != null) node = node.right;
		return node.key;
	}
	public int getHeight(){
		class Node_level{
			final BstNode node;
			final int level;

			public Node_level(BstNode node, int level) {
				this.node = node;
				this.level = level;
			}
		}

		if(root == null) return 0;
		LinkedList<Node_level> q = new LinkedList<>();
		Node_level v;
		int result = 0;
		q.add(new Node_level(root, 1));
		while (!q.isEmpty()) {
			v = q.removeFirst();
			int currentLvl = v.level;
			if(result < currentLvl) result = currentLvl;

			BstNode left = v.node.left;
			BstNode right = v.node.right;
			if(left != null) q.add(new Node_level(left, currentLvl +1));
			if(right != null) q.add(new Node_level(right, currentLvl +1));
		}
		return result;
	}

	public boolean removeLeaf(BstNode leaf){
		if(!leaf.isLeaf()) return false;
		if(leaf == root && root.isLeaf()) root = null;
		else {
			BstNode father = leaf.father;
			if (father.left == leaf) father.left = null;
			else father.right = null;
		}
		return true;
	}

	private boolean removeOneChildNode(BstNode node){
		if(node == null || node.isLeaf() || node.isHave2Child()) return false;

		if(node == root) {
			if (root.left == null) root = root.right;
			else root = root.left;
		}else{
			BstNode grandDad = node.father;
			BstNode child = node.left != null? node.left: node.right;

			if (node == grandDad.right) grandDad.right = child;
			else grandDad.left = child;
		}
		return  true;
	}

	private boolean deleteByCopying(BstNode delNode){
		if(!delNode.isHave2Child()) return false;

		BstNode rightMost = delNode.left;
		while(rightMost.right != null) rightMost = rightMost.right;

		delNode.key = rightMost.key;

		if(rightMost.isLeaf()) removeLeaf(rightMost);
		if(rightMost.isHaveOneChild()) removeOneChildNode(rightMost);
		return true;
	}

	public boolean deleteByCopying(Comparable value){
		BstNode node = this.search(value);
		if(node == null) return false;
		if(node.isLeaf()) return this.removeLeaf(node);
		if(node.isHaveOneChild()) return this.removeOneChildNode(node);
		else return this.deleteByCopying(node);
	}

	public static void main(String[] args) {
		BstTree tree = new BstTree();
		tree.add(32,11, 57, 6,18,40, 80,2,8,16,22,35,50,75,90);
		tree.printAlign();
	}
}
