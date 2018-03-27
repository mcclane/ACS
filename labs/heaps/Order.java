import java.util.ArrayList;

public class Order implements Comparable<Order> {
    public int priority;
    public ArrayList<MenuItem> items;
    public Order(int priority) {
        this.priority = priority;
        items = new ArrayList<MenuItem>();
    }
    public void add(MenuItem mi) {
        items.add(mi);
    }
    public int compareTo(Order o) {
        return priority - o.priority;
    }
    public double price() {
        double price = 0;
        for(MenuItem i : items) {
            price += i.price;
        }
        return price;
    }
    public String toString() {
        return items.toString();
    }
}