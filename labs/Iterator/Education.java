public class Education {
	private String name;
	private String location;
	private String date; //YYYY-MM
	public Education(String name, String location, String date) {
		this.name = name;
		this.location = location;
		this.date = date;
	}
	public String toString() {
		return name+", "+location+", Graduation: "+date;
	}
	public int getDate() {
		return Integer.parseInt(date.replace("-",""));
	}
}