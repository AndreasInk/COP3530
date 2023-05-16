package primer;

public class GenericTriple<E,F,G> {
	final E first;
	final F second;
	final G third;
	
	public GenericTriple(E first, F second, G third){
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public E getFirst() {
		return first;
	}
	
	public F getSecond() {
		return second;
	}

	public G getThird() {
		return third;
	}

	public String toString() {
		return "<" + first + ", " + second + ", " + third + ">";
	}
}
