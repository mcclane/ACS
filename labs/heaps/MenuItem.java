public class MenuItem {
    public String name;
    public double price;
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public MenuItem copy() {
        return new MenuItem(this.name, this.price);
    }
    public String toString() {
        return name+" - $"+price;
    }
}