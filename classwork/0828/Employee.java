public class Employee extends People
{
  private double salary;
  
  public Employee(String name, int age, double salary)
  {
    super(name,age);
    this.salary=salary;
  }
  
  public String toString()
  {
    return(super.toString()+" "+salary);
  }
}