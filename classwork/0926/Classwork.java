import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

class Student implements Comparable<Student> {
  String name;
  int id;
  
  public Student(String n, int i)
  {
    name=n;
    id=i;
  }
  
  public String toString() {
  	return name+" "+id;
  }
  public String getName() {
    return name;
  }
  @Override
  public int compareTo(Student s)
  {
    if(s.id > id) {
      return 1;
    }
    else if(s.id == id && s.name.equals(name)) {
        return 0;
    }
    return -1;
  }
  @Override
  public boolean equals(Object O)
  {
    Student s = (Student)(O);
    if(s.name.equals(name) && s.id == id) {
      return true;
    }
    return false;
  }
  @Override
  public int hashCode()
  {
    int nameHash=name.hashCode();
    return(17*nameHash+id);
  }
}
public class Classwork  {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Set<Student> studentsHash = new HashSet<Student>();
    Set<Student> studentsTree = new TreeSet<Student>();
    System.out.println("Enter in name and id for hash set: ");
    for(int i = 0;i < 5;i++) {
      String line[] = sc.nextLine().split(" ");
      studentsHash.add(new Student(line[0], Integer.parseInt(line[1])));
    }
    System.out.println("Enter in name and id for tree set: ");
    for(int i = 0;i < 5;i++) {
      String line[] = sc.nextLine().split(" ");
      studentsTree.add(new Student(line[0], Integer.parseInt(line[1])));
    }
    System.out.println("Hash Set: ");
    Iterator<Student> it = studentsHash.iterator();
    while(it.hasNext()) {
      System.out.print(it.next().toString()+" ");
    }
    System.out.println("Tree Set: ");
    it = studentsTree.iterator();
    while(it.hasNext()) {
      System.out.print(it.next().toString()+" ");
    }
  }
}