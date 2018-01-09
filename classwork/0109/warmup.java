import java.util.Scanner;

class Node<T> {
    T data;
    Node<T> next;
    public Node(T data) {
        this.data = data;
        next = null;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }
    public T getData() {
        return data;
    }
    public Node<T> getNext() {
        return next;
    }
}
class SLList<T> { 
    Node<T> head;
    public SLList() {
        head = null;
    }
    public void add(T data) {
        if(head == null) {
            head = new Node<T>(data);
            return;
        }
        Node<T> current = head;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(new Node<T>(data));
    }
    public String toString() {
        Node<T> current = head;
        String out = "[";
        while(current != null) {
            out += current.getData()+", ";
            current = current.getNext();
        }
        out = out.substring(0, out.length()-2);
        return out+"]";
    }
    public boolean contains(T data) {
        Node<T> current = head;
        while(current != null) {
            if(current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public void remove(T data) {
        if(head.getData().equals(data)) {
            head = head.getNext();
        }
        Node<T> previous = head;
        Node<T> current = head.getNext();
        while(current != null) {
            if(current.getData().equals(data)) {
                previous.setNext(current.getNext());
                break;
            }
            previous = current;
            current = current.getNext();
        }
    }
    public T get(int index) {
        int currentIndex = 0;
        Node<T> current = head;
        while(currentIndex < index && current.getNext() != null) {
            current = current.getNext();
            currentIndex++;
        }
        if(currentIndex < index) {
            throw new IndexOutOfBoundsException("no.");
        }
        return current.getData();
    }
    public int indexOf(T data) {
        int index = 0;
        Node<T> current = head;
        while(current != null) {
            if(current.getData().equals(data)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }
    public void set(int index, T data) {
        if(index == 0) {
            Node<T> newNode = new Node<T>(data);
            newNode.setNext(head.getNext());
            head = newNode;
            return;
        }
        int counter = 1;
        Node<T> previous = head;
        Node<T> current = head.getNext();
        while(current != null) {
            System.out.println(counter+" adsf");
            if(counter == index) {
                System.out.println("set");
                Node<T> newNode = new Node<T>(data);
                previous.setNext(newNode);
                newNode.setNext(current.getNext());
                return;
            }
            previous = current;
            current = current.getNext();
            counter++;
        }
    }

}
public class warmup {
    public static void main(String[] args) {
        SLList<String> sl = new SLList<String>();
        sl.add("Cat");
        sl.add("Dog");
        sl.add("Pig");
        sl.add("Bird");
        System.out.println(sl);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an index");
        int index = sc.nextInt();
        System.out.println(sl.get(index));
        System.out.println("enter a word");
        String word = sc.next();
        System.out.println(sl.contains(word));
        if(sl.contains(word)) {
            System.out.println(sl.indexOf(word));
        }
        System.out.println("enter a word:");
        word = sc.next();
        sl.remove(word);
        System.out.println(sl);
        System.out.println("enter a location");
        int loc = sc.nextInt();
        sl.set(loc, "Hello");
        System.out.println(sl);
    }
}
