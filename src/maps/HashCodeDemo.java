package maps;

import introduction.Point;

public class HashCodeDemo {
	public static void main(String[] args) {
		String s1 = "UNF is FUN";
		String s2 = "FUN is UNF";
		String s3 = "UNF iss FUN";
		System.out.println(s1.hashCode() + " ");
		System.out.println(s2.hashCode() + " ");
		System.out.println(s3.hashCode());

		double d1 = 101.98;
		System.out.print(Double.hashCode(d1) + " ");

		d1 = 101.981;
		System.out.print(Double.hashCode(d1) + " ");

		d1 = -101.981;
		System.out.print(Double.hashCode(d1) + " ");

		Point p = new Point(1.0,2.0);
		Point q = new Point(1.0,2.0);
		System.out.println(p.hashCode());
		System.out.println(q.hashCode());
	}
}
