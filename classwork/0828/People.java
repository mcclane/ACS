//https://codeshare.io/5geBRR - Employee
//https://codeshare.io/aJdm1Z - Student
//https://codeshare.io/5OkjPg - CollegeStudent
//https://codeshare.io/5zXBpD - Runner
public class People  {
  private String name;
  private int age;
  public People(String name, int age) {
    this.name = name;
    this.age = age;
  }
  public String toString() {
    return name+" "+age;
  }
}