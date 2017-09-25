public class Student {
	private String fname, lname;
	private int age;
	public Student(String fname, String lname, int age) {
		this.fname = fname;
		this.lname = lname;
		this.age = age;
	}
	public String toString() {
		return lname+", "+fname+" - "+age+"\n";
	}
	public String getLastName() {
		return lname;
	}
}