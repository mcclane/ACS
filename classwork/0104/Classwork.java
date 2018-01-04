import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;

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
public class Classwork {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name;
        Iterator<Student> it;
        LinkedList<Student> students = new LinkedList<Student>();
        LinkedList<Student> sorted = new LinkedList<Student>();
        students.add(new Student("John", 11, 17, 654321));
        students.add(new Student("Jack", 10, 16, 123456));
        students.add(new Student("Jill", 12, 18, 789012));
        sorted = students;
        String sortChoice = "last added";
        while(true) {
            System.out.println("1. View Students\n2. View Student by Name\n3. Delete a student by name\n4. Sort by student id\n5. sort alphabetically by name\n6. Sort by time added\n7. Add a student\n8. quit\n");
            int selection = input.nextInt();
            switch(selection) {
                case 1:
                    it = sorted.iterator();
                    while(it.hasNext()) {
                        System.out.println(it.next().nameId());
                    }
                    break;
                case 2: 
                    System.out.println("Enter a name");
                    name = input.next();
                    it = sorted.iterator();
                    while(it.hasNext()) {
                        Student curr = it.next();
                        if(curr.name.equals(name)) {
                            System.out.println(curr);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter a name");
                    name = input.next();
                    it = students.iterator();
                    int counter = 0;
                    int found = -1;
                    while(it.hasNext()) {
                        Student curr = it.next();
                        if(curr.name.equals(name)) {
                            found = counter;
                            break;
                        }
                        counter++;
                    }
                    if(found != -1) {
                        students.remove(found);
                    }
                    break;
                case 4:
                    sortChoice = "id";
                    sorted = sort(students, sortChoice);
                    /*sorted = new LinkedList<Student>();
                    for(int i = 0;i < students.size();i++) {
                        boolean added = false;
                        for(int j = 0;j < sorted.size();j++) {
                            if(students.get(i).id < sorted.get(j).id) {
                                sorted.add(j, students.get(i));
                                added = true;
                                break;
                            }
                        }
                        if(!added) {
                            sorted.add(students.get(i));
                        }
                    }*/
                    break;
                case 5:
                    sortChoice = "name";
                    sorted = sort(students, sortChoice);

                    /*sorted = new LinkedList<Student>();
                    for(int i = 0;i < students.size();i++) {
                        boolean added = false;
                        for(int j = 0;j < sorted.size();j++) {
                            if(students.get(i).name.compareTo(sorted.get(j).name) < 0) {
                                sorted.add(j, students.get(i));
                                added = true;
                                break;
                            }
                        }
                        if(!added) {
                            sorted.add(students.get(i));
                        }
                    }*/
                    break;
                case 6:
                    sorted = students;
                    break;
                case 7:
                    System.out.println("enter name, gradelevel, age, and id separated by commas");
                    input.nextLine();
                    String[] split = input.nextLine().split(",");
                    Student s = new Student(split[0], Integer.parseInt(split[1].trim()), Integer.parseInt(split[2].trim()), Integer.parseInt(split[3].trim()));
                    students.add(s);
                    sorted = sort(students, sortChoice);
                    break;
                case 8:
                    System.exit(0);
            }
        }
    }
    public static LinkedList<Student> sort(LinkedList<Student> students, String selection) {
        LinkedList<Student> sorted;
        switch(selection) {
            case "id":
                sorted = new LinkedList<Student>();
                for(int i = 0;i < students.size();i++) {
                    boolean added = false;
                    for(int j = 0;j < sorted.size();j++) {
                        if(students.get(i).id < sorted.get(j).id) {
                            sorted.add(j, students.get(i));
                            added = true;
                            break;
                        }
                    }
                    if(!added) {
                        sorted.add(students.get(i));
                    }
                }
                return sorted;
            case "name":
                sorted = new LinkedList<Student>();
                for(int i = 0;i < students.size();i++) {
                    boolean added = false;
                    for(int j = 0;j < sorted.size();j++) {
                        if(students.get(i).name.compareTo(sorted.get(j).name) < 0) {
                            sorted.add(j, students.get(i));
                            added = true;
                            break;
                        }
                    }
                    if(!added) {
                        sorted.add(students.get(i));
                    }
                }
                return sorted;
        }
        return students;

    }
}
