public class Item {
    String name;
    int price;
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public String toString() {
        return name+" - $"+price;
    }
    public boolean equals(Object O) {
        Item i = (Item)(O);
        if(i.name.equals(name) && i.price == price) {
            return true;
        }
        return false;
    }
    public int hashCode() {
        return name.hashCode()+price*31;
    }
}