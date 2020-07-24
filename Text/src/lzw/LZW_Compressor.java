package lzw;

import java.util.ArrayList;
import java.util.Hashtable;

public class LZW_Compressor {
	Hashtable<String, Integer> dict;
	ArrayList<Integer> zippedData;
	boolean zipped = false;
	int code = 256;

	public LZW_Compressor(){
		this.dict = new Hashtable<>();
		this.zippedData = new ArrayList<>();
		initDict();
	}

	private void initDict(){
		for (int i = 0; i < code; i++) {
			dict.put("" + (char) i, i);
		}
	}

	private void putToDict(String s){
		this.dict.put(s, code++);
	}
	private void output(int code){
		this.zippedData.add(code);
	}

	public void zip(String src){
		Integer code;
		String subString = "";
		String newSubString;
		char c;
		int i = 0;
		int n = src.length();
		while(i < n){
			c = src.charAt(i);
			newSubString = subString + c;
			if(dict.containsKey(newSubString)) subString = newSubString;
			else{
				code = this.dict.get(subString);
				output(code);
				putToDict(newSubString);
				subString = c + "";
			}
			i++;
		}
		code = dict.get(subString);
		output(code);
		zipped = true;
		System.out.println(dict.keySet().size());

	}

	public Hashtable<String, Integer> getDict() {
		return dict;
	}

	public void setDict(Hashtable<String, Integer> dict) {
		this.dict = dict;
	}

	public ArrayList<Integer> getZippedData() {
		return zippedData;
	}

	public void setZippedData(ArrayList<Integer> zippedData) {
		this.zippedData = zippedData;
	}

	public boolean isZipped() {
		return zipped;
	}

	public void setZipped(boolean zipped) {
		this.zipped = zipped;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
