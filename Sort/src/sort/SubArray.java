package sort;

public class SubArray {
	int first;
	int last;


	public SubArray(int first, int last) {
		this.first = first;
		this.last = last;

	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}


	@Override
	public String toString() {
		return first + " " + last;
	}
}
