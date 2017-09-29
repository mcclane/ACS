public class Item<K, V> {
    private K key;
    private V value;
    public Item(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
    public String toString() {
        return key+":"+value;
    }
}