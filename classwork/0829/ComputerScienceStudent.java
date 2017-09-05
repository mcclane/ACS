public class ComputerScienceStudent extends Student {
	public ComputerScienceStudent(String name) {
		super(name);
	}
	public String saying() {
		return super.saying() + " I study Programming";
	}
}