import java.util.HashMap;
import java.util.TreeMap;
import java.util.Scanner;

class Employee implements Comparable {
  String name;
  int id;
  public Employee(String name, int id) {
    this.name = name;
    this.id = id;
  }
  public String toString() {
    return name+" "+id;
  }
  
  public boolean equals(Object o) {
    Employee e = (Employee)(o);
    if(e.name.equals(name) && e.id == id) {
      return true;
    }
    return false;
  }
  public int compareTo(Object o) {
  	Employee e = (Employee)(o);
    return (10000*name.compareTo(e.name)) +  (id - e.id);
  }
  public int hashCode() {
    return 31*name.hashCode()+31*id;
  }
}

public class Classwork {
  public static void main(String[] args) {
    Scanner input=new Scanner(System.in);
    HashMap<Employee, Double> hm = new HashMap<Employee, Double>();
    hm.put(new Employee("John", 1234) , 500000.00 );
    hm.put(new Employee("Puala", 4321), 60000.00 );
    hm.put(new Employee("Puala", 4321), 61000.00 );
  	hm.put(new Employee("Jose", 1111), 70000.00 );
    hm.put(new Employee("John", 1234), 50000.00 );
    hm.put(new Employee("John", 1232), 51000.00 );    

    TreeMap<Employee, Double> tm = new TreeMap<Employee, Double>();
    tm.put(new Employee("Henry", 1234), 55000.00);
    tm.put(new Employee("Henry", 1221), 60000.00);
    tm.put(new Employee("Jennifer", 4321), 71000.00);
    tm.put(new Employee("Jose", 1111), 70000.00);
    tm.put(new Employee("John", 1234), 50000.00);
		tm.put(new Employee("John", 1234), 50000.00);
           
  	for(Employee e:hm.keySet())
    {
      System.out.println(e.toString()+" "+hm.get(e));
    }
           
    for(Employee e:tm.keySet())
    {
      System.out.println(e.toString()+" "+tm.get(e));
    }
           
    System.out.print("Search User: ");
    String user=input.next();
    System.out.print("Search ID: ");
    int id=input.nextInt();
    
    for(Employee e:hm.keySet())
    {
    	if(e.toString().equals(user+" "+id))
      {
        System.out.println(hm.get(e));
        break;
      }
    }
    
    for(Employee e:tm.keySet())
    {
    	if(e.toString().equals(user+" "+id))
      {
        System.out.println(tm.get(e));
        break;
      }
    }
  }
}