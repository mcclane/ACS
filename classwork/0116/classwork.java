import java.util.Scanner;

class Node<E> {
    private Node<E> previous;
    private Node<E> next;
    private E data;
    public Node(E data) {
        this.data = data;
        previous = null;
        next = null;
    }
    public void setNext(Node<E> next) {
        this.next = next;
    }
    public void setPrev(Node<E> previous) {
        this.previous = previous;
    }
    public E get() {
        return data;
    }
    public Node<E> next() {
        return next;
    }
    public Node<E> prev() {
        return previous;
    }
}
class DLList<E> {
    private Node<E> dummy;
    private int size;
    public DLList() {
        dummy = new Node<E>(null);
        size = 0;
    }
    private Node<E> getNode(int i) {
        if(i > 0 && i >= size) {
            return null;
        }
        Node<E> current = dummy;
        int counter = 0;
        while(counter < i) {
            current = current.next();
            counter++;
        }
        return current.next();
    }
    public E get(int i) {
        return getNode(i).get();
    }
    public int size() {
        return size;
    }
    public void add(int i, E data) {
        if(i > 0 && i >= size) {
            return;
        }
        Node<E> tba = new Node<E>(data);
        Node<E> current = dummy;
        int counter = 0;
        while(counter < i) {
            current = current.next();
            counter++;
        }
        if(current.next() == null) {
            add(data);
        }
        else {
            size++;
            current.next().setPrev(tba);
            tba.setNext(current.next());
            tba.setPrev(current);
            current.setNext(tba);
        }
    }
    public void add(E data) {
        size++;
        Node<E> tba = new Node<E>(data);
        if(dummy.prev() == null) {
            dummy.setNext(tba);
            dummy.setPrev(tba);
            tba.setPrev(dummy);
            tba.setNext(dummy);
        }
        else {
            dummy.prev().setNext(tba);
            tba.setPrev(dummy.prev());
            dummy.setPrev(tba);
            tba.setNext(dummy);
        }
    }
    public String toString() {
        String out = "[";
        Node<E> current = dummy.next();
        while(current != dummy) {
            out += current.get()+", ";
            current = current.next();
        }
        out = out.substring(0, out.length()-2) + "]";
        return out;
    }
}
public class classwork {
    public static void main(String[] args) {
        DLList<String> dl = new DLList<String>();
        dl.add("Cat");
        dl.add("Dog");
        dl.add("Bird");
        dl.add("Bear");
        dl.add("Pig");
        dl.add(0, "0");
        dl.add(2, "2");
        System.out.println(dl);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter in an animal");
        String animal = sc.next();
        System.out.println("Enter in an index");
        int i = sc.nextInt();
        dl.add(i, animal);
        System.out.println(dl);
        System.out.println("Enter in an index");
        i = sc.nextInt();
        System.out.println(dl.get(i));
    }
}
