class Node<T> {
    private T data;
    private Node<T> next;
    public Node(T data) {
        this.data = data;
        next = null;
    }
    public T get() {
        return data;
    }
    public Node<T> getNext() {
        return next;
    }
    public void setNext(Node<T> n) {
        next = n;
    }
}

class SLList<E> {
    private Node<E> head;
    private Node<E> tail;
    public SLList() {
        head = null;
        tail = null;
    }
    public void add(E data) {
        Node<E> newNode = new Node<E>(data);
        if(head == null) {
            head = newNode;
        }
        if(tail == null) {
            tail = head;
        }
        tail.setNext(newNode);
        tail = newNode;
    }
    public String toString() {
        Node<E> current = head;
        String out = "["; 
        while(current != null) {
            out += ""+current.get()+", ";
            current = current.getNext();
        }
        out = out.substring(0, out.lastIndexOf(","));
        return out+"]";
    }
}
public class Classwork {
    public static void main(String[] args) {
        SLList<String> sll = new SLList<String>();
        sll.add("Cat");
        sll.add("Dog");
        sll.add("Pig");
        sll.add("Bird");
        System.out.println(sll);
        SLList<Integer> slli = new SLList<Integer>();
        slli.add(5);
        slli.add(5);
        slli.add(2);
        slli.add(1);
        slli.add(7);
        slli.add(8);
        System.out.println(slli);

    }
}
