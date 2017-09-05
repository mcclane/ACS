public class Student extends Profile{
	public Student(String name) {
		super(name);
	}
	public String saying() {
		return super.saying()+" I am a student";
	}
}