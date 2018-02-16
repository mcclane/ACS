import java.io.Serializable;

public class Country implements Serializable {
  
    private static final long serialVersionUID = 2L;


	private String abbreviation, name;

	public Country(String abbreviation, String name) {
		this.abbreviation = abbreviation;
		this.name = name;
	}
	public int hashCode() {
		return (abbreviation.charAt(0)-65)*11+(abbreviation.charAt(1)-65);
	}
	public String toString() {
		return name+" - "+abbreviation;
	}
    public String abbreviation() {
        return abbreviation;
    }
    public String name() {
        return name;
    }
}
