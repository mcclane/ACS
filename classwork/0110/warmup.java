class Student {
    private String name;
    private int id;
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public boolean equals(Object s) {
        s = (Student)s;
        if(s.toString().equals(toString())) {
            return true;
        }
        return false;
    }
    public String toString() {
        return name+" "+id;
    }
}
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
        SLList<Student> sl = new SLList<Student>();
        sl.add(new Student("John", 1234));
        sl.add(new Student("Jen", 4321));
        sl.add(new Student("Jose", 1111));
        System.out.println(sl);
        sl.remove(new Student("John", 1234));
        System.out.println(sl);
    }
}
