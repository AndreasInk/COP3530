package maps;

import java.util.*;

@SuppressWarnings("unchecked")
public class HashMapSeparateChaining<K,V> implements Iterable<Record<K,V>>, MapADT<K,V>  {
    final double desiredLoadFactor = 0.75; // Java chose this number in their implementation
    final int initialNumberOfBuckets = 16; // Java chose this number in their implementation

    //****************************************************//
    private static class RecordsSinglyLinkedList<K,V> implements Iterable<Record<K,V>> {
        protected static class Node<Record> {
            final private Record rec;
            private Node<Record> next;

            public Node(Record r, Node<Record> refToTheNextNode) {
                rec = r;
                next = refToTheNextNode;
            }
        }
        protected Node<Record<K,V>> head = null;
        protected Node<Record<K,V>> tail = null;
        protected int size = 0;

        public RecordsSinglyLinkedList() { }

        public boolean isEmpty() { return (size == 0); }

        public void addLast(Record<K,V> r) {
            Node<Record<K,V>> newest = new Node<>(r,null);
            if( isEmpty() ) head = newest;
            else tail.next = newest;
            tail = newest;
            size++;
        }

        public V remove(K key) {
            Node<Record<K,V>> current = head, prev = null;

            while(current != null) {
                if(current.rec.getKey().equals(key)) {
                    if( prev == null ) head = current.next;
                    else prev.next = current.next;
                    return current.rec.getValue();
                }
                prev = current;
                current = current.next;
            }

            return null;
        }

        public V updateValue(K key, V newValue){
            Node<Record<K,V>> current = head;

            while(current != null) {
                if(current.rec.getKey().equals(key)) {
                    V holdValue = current.rec.getValue();
                    current.rec.setValue(newValue);
                    return holdValue;
                }
                current = current.next;
            }

            return null;
        }

        public Iterator<Record<K,V>> iterator() { return new RecordsSinglyLinkedList.SinglyLinkedListIterator<>(this); }

        public static class SinglyLinkedListIterator<Record> implements Iterator<Record> {
            private Node<Record> current;
            public SinglyLinkedListIterator(RecordsSinglyLinkedList<?,?> L)   {
                current = (RecordsSinglyLinkedList.Node<Record>) L.head;
            }

            public boolean hasNext()  { return current != null; }

            public Record next() {
                Record data  = current.rec;
                current = current.next;
                return data;
            }
        }

        public String toString() {
            StringBuilder str = new StringBuilder();
            var current = head;
            while( current != null ) {
                str.append( current.rec.toString() );
                if( current.next != null ) str.append(" -> ");
                current = current.next;
            }
            return str.toString();
        }

    }
    //****************************************************//
    RecordsSinglyLinkedList<K,V>[] buckets;

    int numberOfRecordsPresent = 0;

    public HashMapSeparateChaining() {
        buckets = (RecordsSinglyLinkedList<K,V>[]) new RecordsSinglyLinkedList[initialNumberOfBuckets];
        for(int i = 0; i < buckets.length; i++)
            buckets[i] = new RecordsSinglyLinkedList<>();
    }

    public double loadFactor() {
        return numberOfRecordsPresent / (double)buckets.length;
    }

    private int compressionFunction( int x, int N ) {
        return ( x % N );
    }

    public boolean put(K key, V value){
        if(key == null)   throw new IllegalArgumentException("Key cannot be null.");
        if(value == null) throw new IllegalArgumentException("Value cannot be null.");

        int hashCode = key.hashCode(), bucketIndex = compressionFunction(hashCode, buckets.length);

        for( var record : buckets[bucketIndex])
            if( record.getKey().equals(key) )
                return false;

        buckets[ bucketIndex ].addLast(new Record<>(key,value));
        numberOfRecordsPresent++;

        if( loadFactor() > desiredLoadFactor )
            rehash();

        return true;
    }

    private void rehash(){
        var newBuckets = (RecordsSinglyLinkedList<K,V>[]) new RecordsSinglyLinkedList[buckets.length * 2];
        for(int i = 0; i < newBuckets.length; i++)
            newBuckets[i] = new RecordsSinglyLinkedList<>();

        for (RecordsSinglyLinkedList<K, V> L : buckets)
            for (var record : L) {
                int hashCode = record.getKey().hashCode();
                newBuckets[compressionFunction(hashCode, newBuckets.length)].addLast(record);
            }
        buckets = newBuckets;
    }


    public V get(K key) {
        int hashCode = key.hashCode(), bucketIndex = compressionFunction(hashCode, buckets.length);
        for( var record : buckets[bucketIndex] )
            if( record.getKey().equals(key) )
                return record.getValue();
        return null;
    }

    public boolean isPresent(K key) {
        int hashCode = key.hashCode(), bucketIndex = compressionFunction(hashCode, buckets.length);
        for( var record : buckets[bucketIndex] )
            if( record.getKey().equals(key) )
                return true;
        return false;
    }

    public V remove(K key) {
        int hashCode = key.hashCode(), bucketIndex = compressionFunction(hashCode, buckets.length);
        return buckets[bucketIndex].remove(key);
    }

    public V updateValue(K key, V newValue) {
        int hashCode = key.hashCode(), bucketIndex = compressionFunction(hashCode, buckets.length);
        return buckets[bucketIndex].updateValue(key,newValue);
    }

    public void getAllKeys( Collection<K> S ) {
        for (Record<K, V> r : this)
            S.add(r.getKey());
    }

    public void getAllValues( Collection<V> S ) {
        for (Record<K, V> r : this)
            S.add(r.getValue());
    }

    public int size() {
        return numberOfRecordsPresent;
    }

    public void clear() {
        buckets = (RecordsSinglyLinkedList<K,V>[]) new RecordsSinglyLinkedList[initialNumberOfBuckets];
        for(int i = 0; i < buckets.length; i++)
            buckets[i] = new RecordsSinglyLinkedList<>();
        numberOfRecordsPresent = 0;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < buckets.length; i++) {
            str.append("Bucket ");
            str.append(i);
            str.append(": ");
            str.append(buckets[i].toString());
            str.append("\n");
        }
        return str.toString();
    }

    public Iterator<Record<K,V>> iterator() {
        return new HashMapIterator<>(this);
    }

    public class HashMapIterator<E> implements Iterator<E> {
        RecordsSinglyLinkedList.SinglyLinkedListIterator<E> it;
        int currentBucket = 0;
        HashMapSeparateChaining<K,V> H;

        public HashMapIterator(HashMapSeparateChaining<K,V> H)   {
            this.H = H;
            it = (RecordsSinglyLinkedList.SinglyLinkedListIterator<E>) H.buckets[0].iterator();
        }

        public boolean hasNext()  {
            while( currentBucket < H.buckets.length-1 && (!it.hasNext() || H.buckets[currentBucket].isEmpty()) )
                it = (RecordsSinglyLinkedList.SinglyLinkedListIterator<E>) H.buckets[++currentBucket].iterator();
            return it.hasNext();
        }

        public E next() {
            return it.next();
        }
    }
}
