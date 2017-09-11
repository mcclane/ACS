import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Runner
{
	public static void main(String[] args)
  {
    ArrayList<Student> students=new ArrayList<>();
    Scanner input=new Scanner(System.in);
    while(true)
    {
      Iterator<Student> it = students.iterator();
    	while(it.hasNext()) {
			System.out.println(it.next());
    	}
      //boolean remove=false;
      System.out.println("Please enter a name grade level 0/1 or stop to end entering students");
      String ip = input.nextLine();
      if(ip.equals("exit")) {
        break;
      }
      else {
		String[] comps = ip.split(" ");
        String name = comps[0];
        int gradeLevel = Integer.parseInt(comps[1]);
        boolean delete = comps[2].equals("0");
        students.add(new Student(name, gradeLevel));
        if(delete)
        {
          Iterator<Student> rit = students.iterator();
          while(rit.hasNext()) {
            if(rit.next().toString().equals(name+" "+gradeLevel)) {
              rit.remove();
            }
          }
        }
      }
    }
  }
}

class Student
{
  String name;
  int gradeLevel;
  public Student(String name, int gradeLevel) {
    this.name = name;
    this.gradeLevel = gradeLevel;
  }
  public String toString() {
    return name+" "+gradeLevel;
  }
}