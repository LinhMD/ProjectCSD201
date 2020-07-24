public class Sym_Freq implements Comparable<Sym_Freq>{
	int symbol;
	int freq;

	public Sym_Freq(int symbol, int freg) {
		this.symbol = symbol;
		this.freq = freg;
	}

	@Override
	public int compareTo(Sym_Freq o) {
		int d = this.freq - o.freq;
		return Integer.compare(0, d);
	}

	@Override
	public String toString() {
		return "Sym_Freq{" +
				"symbol=" + symbol +
				", freg=" + freq +
				'}';
	}
}
