public class Profile {
    private int birthYear;
    private String name;
    public Profile(int birthYear,String name) {
        this.birthYear = birthYear;
        this.name = name;
    }
    public boolean equals(Object o) {
        Profile p = (Profile)(o);
        return p.toString().equals(this.toString());
    }
    public int hashCode() {
        //System.out.println(((birthYear%100)/10)*10);
        return (((birthYear%100)/10)*10);
    }
    public String toString() {
        return name+" "+birthYear;
    }
}