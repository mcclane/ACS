public class Profile {
    private String name;
    private int birthYear;
    public Profile(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }
    public String toString() {
        return name+":"+birthYear;
    }
    public String getName() {
        return name;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public int hashCode() {
        return 31*name.hashCode()+birthYear;
    }
    public boolean equals(Object o) {
        Profile p = (Profile)(o);
        if(p.getName().equals(name) && p.getBirthYear() == birthYear) {
            return true;
        }
        return false;
    }
}