public class Country {

	private String abbreviation, name;

	public Country(String abbreviation, String name) {
		this.abbreviation = abbreviation;
		this.name = name;
	}
	public int hashCode() {
		return (abbreviation.charAt(0)-65)*11+(abbreviation.charAt(1)-65);
	}
	public String toString() {
		return abbreviation+" "+name;
	}
    public String abbreviation() {
        return abbreviation;
    }
}
