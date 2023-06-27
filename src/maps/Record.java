package maps;

public class Record<K,V> {
    private final K key;
    private V value;
    public Record(K key, V value) {
        this.key = key; this.value = value;
    }
    K getKey() {
        return key;
    }
    V getValue() {
        return value;
    }

    void setValue(V newValue) { value = newValue; }

    public String toString() {
        return "[" + key.toString() + ", " + value.toString() + "]";
    }
}