package tester;

import priorityqueues.*;

public class TestHeap {

	public static void main(String[] args) {
		MinHeap<String> H = new MinHeap<>();
		
		H.insert("Alice");
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

		// iterating over all the items in H
		for(var item : H )
			System.out.print(item + " ");

		System.out.println("\nMin element: " + H.min());
		H.removeMin();
		for(var item : H )
			System.out.print(item + " ");

		System.out.println("\nMin element: " + H.min());
	}
}
