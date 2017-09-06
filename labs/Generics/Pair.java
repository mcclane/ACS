public class Pair<K,V> {
    private K object1;
    private V object2;
    public Pair(K object1, V object2) {
        this.object1 = object1;
        this.object2 = object2;
    }
    public K getObject1() {
        return object1;
    }
    public V getObject2() {
        return object2;
    }
}
