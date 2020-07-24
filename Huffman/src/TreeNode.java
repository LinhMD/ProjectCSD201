public class TreeNode implements Comparable<TreeNode>{
	int symbol;
	int freq;
	TreeNode left, right;
	public TreeNode(int symbol, int freq){
		this.symbol = symbol;
		this.freq = freq;
		left = right = null;
	}

	public boolean isLeaf(){
		return left == null && right == null;
	}
	@Override
	public int compareTo(TreeNode o) {
		return Integer.compare(this.freq , o.freq);
	}
}
