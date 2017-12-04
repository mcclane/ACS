public class Location implements Comparable<Location> {
    int x, y;
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Location l) {
        if(l.x == x && l.y == y) {
            return 0;
        }
        return -1;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj==null || !(obj instanceof Location))
            return false;
        Location l = (Location)(obj);
        return l.x == x && l.y == y;
    }
    @Override
    public int hashCode() {
        return x*1000+y;
    }
    public String toString() {
        return "("+x+", "+y+")";
    }
}