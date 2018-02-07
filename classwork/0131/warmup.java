import java.util.LinkedList;

class HashTable<E> {
    private LinkedList<E>[] table;
    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new LinkedList[2000];
        for(int i = 0;i < table.length;i++) {
            table[i] = new LinkedList<E>();
        }
    }
    public void add(E data) {
        table[data.hashCode()%table.length].add(data);
    }
    public String toString() {
        String out = "";
        for(int i = 0;i < table.length;i++) {
            if(table[i].size() > 0) {
                out += table[i].toString()+"\n";
            }
        }
        return out;
    }
}
class Item {
    private double price;
    private String name;
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public int hashCode() {
        return (int)(price*100.0);
    }
    public String toString() {
        return name+" "+price;
    }
}
public class warmup {
    public static void main(String[] args) {
        HashTable<Item> table = new HashTable<Item>();
        table.add(new Item("Milk", 2.99));
        table.add(new Item("Apples", 0.5));
        table.add(new Item("Cereal", 2.99));
        table.add(new Item("Juice", 0.99));
        table.add(new Item("Onions", 0.5));
        System.out.println(table);
    }
}