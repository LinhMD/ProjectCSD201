package lzw;

import java.util.ArrayList;
import java.util.Hashtable;

public class LZW_Decompressor {
	Hashtable<Integer, String> dict = new Hashtable<>();
	String unzippedStr;
	int code = 256;
	boolean unzipped = false;
	public LZW_Decompressor(){
		for (int i = 0; i < 256; i++) {
			dict.put(i, (char) i + "");
		}
	}
	private void putToDict(String term){
		dict.put(code++, term);
	}

	private void output(String entry){
		unzippedStr += entry;
	}

	public void unzip(ArrayList<Integer> zippedCodes){
		unzippedStr = "";
		int prevCode, curCode;
		char ch;
		String entry;
		prevCode = zippedCodes.get(0);
		String curTranslation,prevTranslation = dict.get(prevCode);
		entry = prevTranslation;
		output(entry);
		int i = 1;
		while (i < zippedCodes.size()){
			curCode = zippedCodes.get(i);
			curTranslation = dict.get(curCode);
			entry = curTranslation;
			output(entry);
			ch = entry.charAt(0);
			putToDict(prevTranslation + ch);
			prevTranslation = curTranslation;
			i++;
		}
		unzipped = true;
	}

	public String getUnzippedStr() {
		return unzipped? unzippedStr: null;
	}

	public static void main(String[] args) {
		String src = "lZW starts out with a dictionary of 256 character\n" +
				"(in the case of 8 bits) and used those as the \"standard\" character set.\n" +
				"It then reads data 8 bit at a time and encodes\n" +
				"the data as the number that represents its index in the dictionary.\n" +
				"Everytime it comes across a new sup string";
		System.out.println(src.length());
		LZW_Compressor compressor = new LZW_Compressor();
		compressor.zip(src);
		System.out.println(compressor.zippedData);
		LZW_Decompressor decompressor = new LZW_Decompressor();
		decompressor.unzip(compressor.zippedData);
		System.out.println(decompressor.getUnzippedStr());
	}
}
