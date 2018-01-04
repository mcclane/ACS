import java.util.LinkedList;
import java.util.Iterator;

class Item {
    private String name;
    private double price;
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String toString() {
        return name+" $"+price;
    }
}
public class warmup {
    public static void main(String[] args) {
        LinkedList<Item> ll = new LinkedList<Item>();
        ll.add(new Item("Pencil", 0.25));
        ll.add(new Item("Pen", 0.49));
        ll.add(new Item("Paper", 0.10));
        Iterator<Item> it = ll.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
