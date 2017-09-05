public class Profile {
	private String name;
	public Profile(String name) {
		this.name = name;
	}
	public String saying() {
		return "My name is "+name;
	}
}