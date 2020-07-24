import java.io.Serializable;

public class Codeword implements Serializable {
	int symbol;
	String codeword;

	public Codeword(int symbol){
		this.symbol = symbol;
		this.codeword = "";
	}

	@Override
	public String toString() {
		return "Codeword{" +
				"symbol=" + symbol +
				", codeword='" + codeword + '\'' +
				'}';
	}
}
