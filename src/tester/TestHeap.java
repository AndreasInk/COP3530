package tester;

import priorityqueues.*;

public class TestHeap {

	public static void main(String[] args) {
		Heap<String> H = new Heap<>();
		
		H.insert("Alica");
		H.insert("Bob");
		H.insert("Melissa");
		H.insert("Kate");
		H.insert("Ryan");
		H.insert("Jack");
		H.insert("Rose");
		H.insert("Oliver");
		H.insert("William");
		H.insert("James");
		H.insert("Benjamin");
		H.insert("Noah");

		while( !H.isEmpty() )
			System.out.print(H.removeMin() + " ");

	}
}
