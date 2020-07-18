package bst;

public class DSWBstTree  extends  BstTree{
	public void rotateRight(BstNode grandDad, BstNode parent, BstNode child){
		if(parent != root){
			child.father = grandDad;
			grandDad.right = child;
		}
		parent.left = child.right;
		if(parent.left != null) parent.left.father = parent;
		child.right = parent;
		parent.father = child;
	}

	private int createBackBOne(){
		int numOfNode = 0;
		BstNode temp = root, grandDad, parent, child;
		while(temp != null){
			while (temp.left != null){
				grandDad = temp.father;
				parent = temp;
				child = temp.left;
				rotateRight(grandDad, parent, child);
				if(grandDad == null){
					root = child;
					root.father = null;
				}
				temp = child;
			}
			temp = temp.right;
			numOfNode++;
		}
		return numOfNode;
	}
	public void rotateLeft(BstNode grandDad, BstNode parent, BstNode child){
		if (grandDad == null) root = child;
		else grandDad.right = child;
		child.father = grandDad;
		parent.right = child.left;
		if(parent.right != null) parent.right.father = parent;
		child.left = parent;
		parent.father = child;
	}

	private void createBalancedTree(int numOfNodes){
		int n = numOfNodes;
		double Log2nPlus1 = Math.log(n + 1) / Math.log(2);
		int m = (int) Math.pow(2, (int) Log2nPlus1) - 1;

		BstNode grandDad = null, parent = root, child = parent.right;
		for (int i = 0; i < n - m; i++) {
			rotateLeft(grandDad, parent, child);
			grandDad = child;
			parent = grandDad.right;
			child = parent.right;
		}
		while(m > 1){
			 m = m/2;
			 grandDad = null;
			 parent = root;
			 child = parent.right;
			for (int i = 0; i < m; i++) {
				if(child != null) {
					rotateLeft(grandDad, parent, child);
					grandDad = child;
					parent = grandDad.right;
					if(parent != null) child = parent.right;
					else child = null;
				}
			}
		}
	}
}
