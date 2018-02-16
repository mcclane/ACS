import java.io.Serializable;

public class MyHashMap<K,V> implements Serializable {

  private static final long serialVersionUID = 1L;

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
class Entry<V> implements Serializable {
  
    private static final long serialVersionUID = 5L;

    private V data;
    public Entry(V data) {
        this.data = data;
    }
    public V get() {
        return data;
    }
}