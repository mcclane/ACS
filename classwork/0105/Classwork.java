class Student {
    int id;
    int age, gradeLevel;
    String name;
    public Student(String name, int gradeLevel, int age, int id) {
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.age = age;
        this.id = id;
    }
    public String nameId() {
        return name+" "+id;
    }
    public String toString() {
        return name+" Grade: "+gradeLevel+", age: "+age+", id: "+id;
    }
}
class Node<T> {
    private T data;
    private Node next;
    public Node(T data) {
        this.data = data;
        next = null;
    }
    public T get() {
        return data;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node n) {
        next = n;
    }
}

public class Classwork {
    public static void main(String[] args) {
        Node n1 = new Node<Student>(new Student("Cat", 11, 17, 654321));
        Node n2 = new Node<Student>(new Student("Dog", 11, 17, 654321));
        n1.setNext(n2);
        Node n3 = new Node<Student>(new Student("Bird", 11, 17, 654321));
        n2.setNext(n3);
        Node n4 = new Node<Student>(new Student("Bear", 11, 17, 654321));
        n3.setNext(n4);
        Node n5 = new Node<Student>(new Student("Pig", 11, 17, 654321));
        n4.setNext(n5);
        Node curr = n1;
        while(curr != null) {
            System.out.println(curr.get());
            curr = curr.getNext();
        }
    }
}