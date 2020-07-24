import java.util.ArrayList;

public class HuffmanTree {
	TreeNode root;
	FreqCounter freqTable;

	public HuffmanTree(){
	}

	public HuffmanTree(FreqCounter freqTable){
		this.freqTable = freqTable;
		createTree();
	}

	private void createTree() {
		freqTable.decreaseSort();
		ArrayList<TreeNode> nodeList = new ArrayList<>();
		for (int i = 0; i < 256; i++) {
			int symbol = freqTable.freqObjets[i].symbol;
			int freq = freqTable.freqObjets[i].freq;
			if(freq > 0){
				nodeList.add(new TreeNode(symbol, freq));
			}
			else break;
		}

		int n = nodeList.size();
		int i;
		while (n > 1){
			TreeNode node1 = nodeList.get(n - 2);
			TreeNode node2 = nodeList.get(n - 1);
			int sumFreq = node1.freq + node2.freq;
			TreeNode newNode = createIntermediate(sumFreq);
			newNode.left = node1;
			newNode.right = node2;
			nodeList.remove(n - 1);
			nodeList.remove(n - 2);
			i = n - 3;
			while (i >= 0 && nodeList.get(i).freq <= newNode.freq) i--;
			nodeList.add(i + 1, newNode);
			n = nodeList.size();
		}
		root = nodeList.get(0);
	}

	private TreeNode createIntermediate(int Freq) {
		return new TreeNode(-1, Freq);
	}

	public void reBuild(int symbol, String codeword){
		int n = codeword.length();
		char lastBit = codeword.charAt(n - 1);
		TreeNode t = this.root;
		for (int i = 0; i < codeword.length() - 1; i++) {
			char digit = codeword.charAt(i);
			if (digit == '0'){
				if (t.left == null) t.left = createIntermediate(0);
				t = t.left;
			}else {
				if(t.right == null) t.right = createIntermediate(0);
				t = t.right;
			}
		}

		TreeNode treeNode = new TreeNode(symbol, 0);
		if (lastBit == '0') t.left = treeNode;
		else t.right = treeNode;
	}

	public boolean reBuild(CodewordTable table){
		root = createIntermediate(0);
		for (Codeword value : table) {
			int symbol = value.symbol;
			String codeword = value.codeword;
			reBuild(symbol, codeword);
		}
		return true;
	}
}
