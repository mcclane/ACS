public class Item {
    String name;
    double price;
    int quantity;
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }
    public String toString() {
        return quantity+" x "+name+" $"+price;
    }
    public boolean equals(Object o) {
        Item i = (Item)o;
        if(i.name.equals(name) && i.price == price) {
            return true;
        }
        return false;
    }
}
