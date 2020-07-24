import java.io.Serializable;
import java.util.ArrayList;

public class CodewordTable extends ArrayList<Codeword> implements Serializable {
	public CodewordTable(){
		super(256);
		for (int i = 0; i < 256; i++) this.add(new Codeword(i));
	}

	public CodewordTable reduce(){
		for (int i = this.size() - 1; i >= 0; i--){
			if (this.get(i).codeword == null || this.get(i).codeword.length() == 0)
				this.remove(i);
		}
		return this;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		for (Codeword codeword : this) {
			if (codeword != null && codeword.codeword.length() > 0) result.append(codeword).append(",");
		}
		return result.append("]").toString();
	}
}
