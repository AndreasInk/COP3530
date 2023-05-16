package introduction;
import java.util.*;

public class ArrayListDemo {
	public static void main(String[] args) {

		ArrayList<Integer> arr = new ArrayList<>();

		for(int i = 0; i < 15; i++) {
			Random generator = new Random();
			int randomInteger = generator.nextInt(100);
			arr.add(randomInteger);
		}

		System.out.println("The first integer: " + arr.get(0));
		System.out.println("The last integer: " + arr.get( arr.size()-1) );
		System.out.println(arr); // the toString method from the Integer class is called

		ArrayList<Point> arrayOfPoints = new ArrayList<>();

		for(int i = 0; i < 5; i++) {
			Random generator = new Random();
			double  x = generator.nextInt(1000)/10.0,
					y = generator.nextInt(1000)/10.0;
			Point randomPoint = new Point( x, y);
			arrayOfPoints.add(randomPoint);
		}

		System.out.print(arrayOfPoints); // the toString method from the Point class is called
	}
}
