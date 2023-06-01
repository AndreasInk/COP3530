package arraysandLLs;

import java.util.Iterator;

public class CircularLinkedList<E> implements Iterable<E> {
    private static class Node<E> {
        final private E element; // stores data
        private Node<E> next; // points to the next node in the list

        public Node(E e, Node<E> refToTheNextNode) {
            element = e;
            next = refToTheNextNode;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> refToTheNextNode) {
            next = refToTheNextNode;
        }
    }
    //****************************************************//

    private Node<E> tail = null; // we maintain just the tail; head is not needed in our implementation
    // Note that tail.getNext() will give us the head
    private int elementsStoredSoFar = 0;

    public CircularLinkedList() {
    }

    public int size() {
        return elementsStoredSoFar;
    }

    public boolean isEmpty() {
        return elementsStoredSoFar == 0;
    }

    public E first() {
        if (isEmpty())
            return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if (isEmpty())
            return null;
        return tail.getElement();
    }

    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

    public void addFirst(E e) {
        Node<E> newest = new Node<>(e, null);

        if (isEmpty()) {
            tail = newest;
            tail.setNext(tail);
        } else {
            newest.setNext(tail.getNext());
            tail.setNext(newest);
        }

        elementsStoredSoFar++;
    }

    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    public E removeFirst() {
        if (isEmpty())
            return null;

        Node<E> head = tail.getNext();

        if (head == tail)
            tail = null; // the list has just one node currently; delete it
        else
            tail.setNext(head.getNext()); // else, the next node of tail must be the next node of the head

        elementsStoredSoFar--;
        return head.getElement();
    }

    public Iterator<E> iterator() {
        return new CircularLinkedListIterator<>(this);
    }

    public static class CircularLinkedListIterator<E> implements Iterator<E> {
        private Node<E> current;
        final private Node<E> tail;
        private boolean atTheHead = true;

        public CircularLinkedListIterator(CircularLinkedList<E> list) {
            tail = list.tail;
            current = tail.getNext();
        }

        public boolean hasNext() {
            if( atTheHead ) {
                atTheHead = false;
                return true;
            }
            return current != tail.getNext();
        }

        public E next() {
            E data = current.getElement();
            current = current.getNext();
            return data;
        }
    }
}