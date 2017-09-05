public class CollegeStudent extends Student
{
  String major;
  
  public CollegeStudent(String name, int age, String school, String major)
  {
    super(name,age,school);
    this.major=major;
  }
  
  public String toString()
  {
    return(super.toString()+" "+major);
  }
}