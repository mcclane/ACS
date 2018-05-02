public class Profile implements Comparable<Profile> {
    private String name;
    private int id;
    public Profile(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public int id() {
        return id;
    }
    public int compareTo(Profile p) {
        return this.id - p.id;
    }
    public String toString() {
        return name+":"+id;
    }
}