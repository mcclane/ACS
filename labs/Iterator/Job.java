public class Job {
	private String title;
	private String company;
	private String location;
	private String start;
	private String end;
	public Job(String title, String company, String location, String start, String end) {
		this.title = title;
		this.company = company;
		this.location = location;
		this.start = start;
		this.end = end;
	}
	public String toString() {
		return title+", "+company+", "+location+", from "+start+" to "+end;
	}
	public int getDate() {
		return Integer.parseInt(start.replace("-",""));
	}
}