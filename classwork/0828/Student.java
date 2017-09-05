public class Student extends People {
  private String schoolName;
  public Student(String name, int age, String schoolName) {
    super(name, age);
    this.schoolName = schoolName;
  }
  public String toString() {
    return super.toString()+" "+schoolName;
  }
}