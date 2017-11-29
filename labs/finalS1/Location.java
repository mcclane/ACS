public class Location implements Comparable<Location> {
    int x, y;
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int compareTo(Location l) {
        if(l.x == x && l.y == y) {
            return 0;
        }
        return -1;
    }
    public boolean equals(Location l) {
        return l.x == x && l.y == y;
    }
    public int hashCode() {
        return x*1000+y;
    }
    public String toString() {
        return x+", "+y;
    }
}