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
class DLList<E extends Comparable<E>> {
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
    public void addInOrder(E data) {
        int counter = 0;
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            if(current.get().compareTo(data) > 0) {
                add(counter, data);
                return;
            }
            counter++;
        }
        add(data);
    }
    public int size() {
        return size;
    }
}
public class HashTable<E extends Comparable<E>> {
    private DLList<E>[] table;
    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new DLList[400];
    }
    public void add(E data) {
        int index = data.hashCode()%table.length;
        if(table[index] == null) {
            table[index] = new DLList<E>();
        }
        table[index].addInOrder(data);
    }
    public DLList<E> get(int i) {
        return table[i];
    }
}