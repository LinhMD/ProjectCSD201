package bst;

public class BstNode {
	Comparable key;
	BstNode left, right;
	BstNode father;
	
	public BstNode(Comparable key) {
		this.key = key;
		left = right = father = null;
	}
	
	@Override
	public String toString() {
		return key.toString();
	}
	
	public boolean isLeaf(){
		return (left == null && right == null);
	}
	
	public boolean isHaveOneChild(){
		return ((left == null && right != null) || (left != null && right == null));
	}
	
	public boolean isHave2Child(){
		return left != null && right != null;
	}
}
