public class MyHashMap<K,V> {
  private Entry<V>[] table;
  private DLList<K> keys;
  
  @SuppressWarnings("unchecked")
  public MyHashMap()
  {
    table = new Entry[9999];
    keys = new DLList<K>();
  }
  
  public void put(K key,V value)
  {
    if(table[key.hashCode()] == null) {
        keys.add(key);
    }
    table[key.hashCode()] = new Entry<V>(value);
  }
  
  public DLList<K> keySet()
  {
    return(keys);
  }
  
  public String toString()
  {
    String msg="";
    int k = 0;
    for(int i = 0;i < keys.size();i++) {
        msg += keys.get(i)+" "+table[keys.get(i).hashCode()]+"\n";
    }
    return(msg);
  }
  
  public void remove(K key,V value)
  {
    table[key.hashCode()] = null;
    keys.remove(key);
    //keys.remove(key);
  }
  
  public void remove(K key)
  {
    table[key.hashCode()] = null;
    keys.remove(key);
  }
  public V get(K key) {
    if(key.hashCode() > 0 && key.hashCode() < table.length && table[key.hashCode()] != null) {
        return table[key.hashCode()].get();
    }
    return null;
  }
}
class Entry<V> {
    private V data;
    public Entry(V data) {
        this.data = data;
    }
    public V get() {
        return data;
    }
}
class Node<E> {
    private E data;
    private Node<E> next;
    private Node<E> prev;
    public Node(E data) {
        this.data = data;
        next = null;
        prev = null;
    }
    public void setNext(Node<E> next) {
        this.next = next;
    }
    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }
    public E get() {
        return data;
    }
    public Node<E> next() {
        return next;
    }
    public Node<E> prev() {
        return prev;
    }
}