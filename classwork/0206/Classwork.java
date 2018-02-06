public class Classwork
{
  public static void main(String[] args)  {
    MyHashMap<Student,String> map=new MyHashMap<Student,String>();
    
    map.put(new Student("Jessica",1234), "Calculus BC" );
    map.put(new Student("Jessica",1234), "AP Computer Science" );
    map.put(new Student("Jessica",1234), "Economics" );
    
    map.put(new Student("Jose",4321), "AP Computer Science" );
    map.put(new Student("Jose",4321), "Statistics" );
    map.put(new Student("Jose",4321), "Psychology" );
    
    map.put(new Student("John",1111), "Geometry" );
   	map.put(new Student("John",1111), "App and Game Design" );
    map.put(new Student("John",1111), "Economics" );
    
    map.remove(new Student("John", 1111));
    System.out.println(map);
    map.remove(new Student("Jessica", 1234), "Economics");
    System.out.println(map);  
  }
}

class MyHashMap<K,V>
  
{
  private DLList<V>[] table;
  private DLList<K> keys;
  
  @SuppressWarnings("unchecked")
  public MyHashMap()
  {
    table=new DLList[9999];
    keys = new DLList<K>();
  }
  
  public void put(K key,V value)
  {
    if(table[key.hashCode()] == null) {
        table[key.hashCode()] = new DLList<V>();
        keys.add(key);
    }
    table[key.hashCode()].add(value);
  }
  
  public DLList<K> getKeys()
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
    table[key.hashCode()].remove(value);
    //keys.remove(key);
  }
  
  public void remove(K key)
  {
    table[key.hashCode()] = null;
    keys.remove(key);
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
class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        tail.setPrev(head);
        head.setNext(tail);
    }
    public void add(E data) {
        Node<E> newNode = new Node<E>(data);
        Node<E> temp = tail.prev();
        tail.setPrev(newNode);
        temp.setNext(newNode);
        newNode.setPrev(temp);
        newNode.setNext(tail);
        size++;
    }
    public E get(int i) {
        int counter = 0;
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            if(counter == i) {
                return current.get();
            }
            counter++;
        }
        return null;
    }
    public boolean contains(E data) {
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            if(current.get().equals(data)) {
                return true;
            }
        } 
        return false;
    }
  public void remove(E data) {
  	  for(Node<E> current = head.next();current != tail;current = current.next()) {
        if(current.get().equals(data)) {
          			current.prev().setNext(current.next());
          			current.next().setPrev(current.prev());
          			size--;
                return;
        }
     }
  }
    public void add(int i, E data) {
        int counter = 0;
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            if(counter == i) {
                Node<E> newNode = new Node<E>(data);
                Node<E> temp = current.prev();
                current.setPrev(newNode);
                temp.setNext(newNode);
                newNode.setPrev(temp);
                newNode.setNext(current);
                size++;
                return;
            }
            counter++;
        }
        //otherwise add at the end
        add(data);
    }
    public int size() {
        return size;
    }
    public String toString() {
        String out = "";
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            out += " "+current.get().toString();
        }
        return out;
    }
}
        

class Student {
  private String name;
  private int id;
  public Student(String name, int id) {
    this.name = name;
    this.id = id;
  }
  public int hashCode() {
    return id;
  }
  public String toString() {
    return name+" "+id;
  }
  public boolean equals(Object o) {
    Student s = (Student)(o);
    return s.hashCode() == hashCode();
  }
}