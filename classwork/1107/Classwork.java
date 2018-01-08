import java.util.ArrayList;
import java.util.Scanner;

class Student implements Comparable<Student> {
  String name;
  int age;
  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }
  public String toString() {
    return name+" "+age;
  }
  public int compareTo(Student s) {
    if(s.age > age) {
      return 1;
    }
    return -1;
  }
}

class MyPriorityQueue {
  ArrayList<Student> list=new ArrayList<Student>();
  public void add(Student s)
  {
    list.add(s);
    list.sort(null);
  }
  
  public Student poll()
  {
    return list.remove(0);
  }
  public Student peek() {
    return list.get(0);
  }
  public String toString() {
    return list.toString();
  }
  public boolean isEmpty()
  {
    return list.size()==0;
  }
}

public class Classwork {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    MyPriorityQueue q = new MyPriorityQueue();
    while(true) {
    	System.out.println("1. Add a student\n2. See the top student\n3. Remove and see the top student\n4. View the entire student list\n5. Quit Program");
        int choice=scanner.nextInt();
                           
        switch(choice) {
          case 1:
          	System.out.println("Enter name: ");
          	String name = scanner.next();
          	System.out.println("Enter in age: ");
          	int age = scanner.nextInt();
          	q.add(new Student(name, age));
          	break;
          case 2:
              System.out.println(q.peek());
              break;
          case 3:
          	System.out.println(q.poll());
            break;
          case 4:
          	System.out.println(q.toString());
            break;
          case 5:
          	System.exit(0);
            break;
          default:
          	System.out.println("Invalid Input");
          	break;
        }
        System.out.println();
    }
  }
}