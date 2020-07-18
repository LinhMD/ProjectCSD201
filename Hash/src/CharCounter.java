import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CharCounter extends HashMap<Integer, Integer> {
	int numOfChar = 0;
	public CharCounter(){
		super();
	}

	public CharCounter(String fileName){
		super();
		try{
			FileReader reader = new FileReader(fileName);
			int code;
			while((code = reader.read()) != -1){
				if(this.containsKey(code)) this.replace(code, this.get(code) + 1);
				else this.put(code, 1);
				numOfChar++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void print(){
		for (Integer key : this.keySet()) {
			System.out.println((char) key.intValue() + ", " +
								this.get(key) + ", " +
								1.0*this.get(key)/numOfChar);
		}
	}

	public static void main(String[] args) {
		CharCounter charCounter = new CharCounter("E:\\Classes\\csd201\\Project\\Hash\\src\\text.txt");
		charCounter.print();
	}
}
