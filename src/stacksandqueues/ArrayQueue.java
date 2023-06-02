package stacksandqueues;

import java.util.Iterator;

public class ArrayQueue<E> implements QueueADT<E>, Iterable<E> {
	final private E[] data;
	private int front = 0, size = 0;
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if( isEmpty() )
			return null;
		
		return data[front];
	}
	
	public void enqueue(E e) throws IllegalStateException {
		if( size == data.length )
			throw new IllegalStateException("Oops! queue is full.");
		
		int nextAvailableSpot = (front + size) % data.length;
		data[nextAvailableSpot] = e;
		size++;
	}

	public E dequeue() {
		if( isEmpty() )
			return null;
		
		E answer = data[front];
		data[front] = null; // optional
		front = (front + 1) % data.length;
		size--;
		return answer;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayQueueIterator<>(this);
	}

	public static class ArrayQueueIterator<E> implements Iterator<E> {
		ArrayQueue<E> Q;
		int current, elementsMetSoFar;
		public ArrayQueueIterator(ArrayQueue<E> Q){
			this.Q = Q;
			current = Q.front;
			elementsMetSoFar = 0;
		}

		public boolean hasNext() {
			return elementsMetSoFar < Q.size;
		}

		public E next() {
			E hold = Q.data[current];
			current = (current + 1) % Q.data.length;
			elementsMetSoFar++;
			return hold;
		}
	}
}
