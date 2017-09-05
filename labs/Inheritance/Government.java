public class Government extends Employee {
	private String cityName;
	public Government(String name, String photoFile, String jobTitle, String cityName) {
		super(name, photoFile, jobTitle);
		this.cityName = cityName;
	}
	public double getSalary() {
		if(getJobTitle().equals("Banker")) {
			return 100.00;
		}
		return 200.00;
	}
	public String toString() {
		return super.toString()+" "+cityName;
	}
}