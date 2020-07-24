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
		String curTranslation, prevTranslation = dict.get(prevCode);
		entry = prevTranslation;
		output(entry);
		int i = 1;
		while (i < zippedCodes.size()){
			curCode = zippedCodes.get(i++);
			curTranslation = dict.get(curCode);
			entry = curTranslation;
			output(entry);
			ch = entry.charAt(0);
			putToDict(prevTranslation + ch);
			prevTranslation = curTranslation;
		}
		unzipped = true;
	}

	public String getUnzippedStr() {
		return unzipped? unzippedStr: null;
	}

	public static void main(String[] args) {
		String src = "We're no strangers to love\n" +
				"You know the rules and so do I\n" +
				"A full commitment's what I'm thinking of\n" +
				"You wouldn't get this from any other guy\n" +
				"I just wanna tell you how I'm feeling\n" +
				"Gotta make you understand\n" +
				"Never gonna give you up\n" +
				"Never gonna let you down\n" +
				"Never gonna run around and desert you\n" +
				"Never gonna make you cry\n" +
				"Never gonna say goodbye\n" +
				"Never gonna tell a lie and hurt you\n" +
				"We've known each other for so long\n" +
				"Your heart's been aching but you're too shy to say it\n" +
				"Inside we both know what's been going on\n" +
				"We know the game and we're gonna play it\n" +
				"And if you ask me how I'm feeling\n" +
				"Don't tell me you're too blind to see";
		System.out.println(src.length());
		LZW_Compressor compressor = new LZW_Compressor();
		compressor.zip(src);
		System.out.println(compressor.zippedData);
		LZW_Decompressor decompressor = new LZW_Decompressor();
		decompressor.unzip(compressor.zippedData);
		System.out.println(decompressor.getUnzippedStr());
	}
}
