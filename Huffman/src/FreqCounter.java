import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class FreqCounter {
	int numOfSymbols = 0;
	Sym_Freq[] freqObjets = new Sym_Freq[256];

	public FreqCounter(){
		this.freqObjets = new Sym_Freq[256];
	}

	public FreqCounter (InputStream source){
		if(!buildFromSource(source))
			throw new RuntimeException("counting frequently failed");
	}

	private void reset(){
		this.numOfSymbols = 0;
		for (int i = 0; i < 256; i++) {
			freqObjets[i] = new Sym_Freq(i, 0);
		}
	}

	public boolean buildFromSource(InputStream source){
		this.reset();
		source.mark(0);
		try{
			if (source == null || source.available() == 0) {
				System.out.println("source is empty");
				return false;
			}
			int symbol;
			while ((symbol = source.read())  != -1){
				freqObjets[symbol].freq++;
				numOfSymbols++;
			}
			source.reset();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void decreaseSort(){
		Arrays.sort(freqObjets);
	}

	public int getNumOfSymbols() {
		return numOfSymbols;
	}

	public void setNumOfSymbols(int numOfSymbols) {
		this.numOfSymbols = numOfSymbols;
	}

	public Sym_Freq[] getFreqObjets() {
		return freqObjets;
	}

	public void setFreqObjets(Sym_Freq[] freqObjets) {
		this.freqObjets = freqObjets;
	}

	@Override
	public String toString() {
		String result = "" + this.numOfSymbols + " [";
		for (Sym_Freq freqObjet : this.freqObjets) {
			if(freqObjet.freq > 0) result += freqObjet + " ,";
		}
		return result;
	}
}
