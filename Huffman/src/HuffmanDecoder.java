import java.io.*;
import java.util.ArrayList;

public class HuffmanDecoder {
	HuffmanZipResult zipResult;
	byte[] zippedData;
	int numberOfLastBit;
	CodewordTable codeTable;
	HuffmanTree tree;
	byte[] srcData;
	boolean decoded = false;
	String decodingBitString = "";

	public HuffmanDecoder(HuffmanZipResult zipResult){
		this.zipResult = zipResult;
		zippedData = zipResult.zippedData;
		codeTable = zipResult.minCodeTable;
		numberOfLastBit = zipResult.numberOfLastBit;
	}

	public HuffmanDecoder(File decodeFile){
		decoded = false;
		if (!decodeFile.exists()) {
			System.out.println("file not exist");
			System.exit(0);
		}else {
			try{
				FileInputStream fi = new FileInputStream(decodeFile);
				ObjectInputStream oi = new ObjectInputStream(fi);
				zipResult =(HuffmanZipResult) oi.readObject();
				codeTable = zipResult.minCodeTable;
				zippedData = zipResult.zippedData;
				numberOfLastBit = zipResult.numberOfLastBit;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	private String convert8 (int aByte){
		StringBuilder bitStr = new StringBuilder(Integer.toBinaryString(aByte));
		int length = bitStr.length();
		if(length > 8) bitStr = new StringBuilder(bitStr.substring(length - 8, length));
		else while (bitStr.length() < 8) bitStr.insert(0, '0');
		return bitStr.toString();
	}

	private byte decodeSymbol(){
		TreeNode t = tree.root;
		int i = 0;
		while (!t.isLeaf()){
			char bit = decodingBitString.charAt(i++);
			if(bit == '0') t = t.left;
			else t = t.right;
		}
		byte aSymbol = (byte) t.symbol;
		decodingBitString = decodingBitString.substring(i);
		return aSymbol;
	}

	public void decode(){
		tree = new HuffmanTree();
		tree.reBuild(codeTable);
		int l = zippedData.length;
		ArrayList<Byte> temp = new ArrayList<>();
		String bitStr8 = "";
		decodingBitString = "";
		for (byte data : zippedData) {
			bitStr8 = convert8(data);
			decodingBitString += bitStr8;
			if (decodingBitString.length() > 15){
				while (decodingBitString.length() > 15) temp.add(decodeSymbol());
			}
		}
		String lastByte = convert8(zippedData[l - 1]);
		lastByte = lastByte.substring(0, numberOfLastBit);
		decodingBitString += lastByte;
		while ((decodingBitString.length() > 0)){
			temp.add(decodeSymbol());
		}
		int size = temp.size();
		this.srcData = new byte[size];
		for (int i = 0; i < size; i++) {
			srcData[i] = temp.get(i);
			decoded = true;
		}
	}

	public boolean writeDecodeResult(String fileName){
		if(!decoded) this.decode();
		File file = new File(fileName);
		try{
			FileOutputStream fo = new FileOutputStream(file);
			fo.write(srcData);
			fo.flush();
			fo.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}

	public String getSrcString(){
		if(!decoded) return null;
		return new String(srcData);
	}

	public byte[] getSrcData() {
		return srcData;
	}

	public CodewordTable getCodeTable() {
		return codeTable;
	}

	public HuffmanTree getTree() {
		return tree;
	}
}

