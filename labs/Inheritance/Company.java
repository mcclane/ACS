public class Company extends Employee {
	private String companyName;
	public Company(String name, String photoFile, String jobTitle, String companyName) {
		super(name, photoFile, jobTitle);
		this.companyName = companyName;
	}
	public double getSalary() {
		if(getJobTitle().equals("Teacher")) {
			return 100.00;
		}
		return 200.00;
	}
	public String toString() {
		return super.toString()+" "+companyName;
	}
}