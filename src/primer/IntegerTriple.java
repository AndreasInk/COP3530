package primer;

public class IntegerTriple {
	final int first, second, third;

	public IntegerTriple(int first, int second, int third){
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public int getFirst() { return first; }

	public int getSecond() { return second; }

	public int getThird() { return third; }

	public String toString() {
		return "<" + first + ", " + second + ", " + third + ">";
	}
}
