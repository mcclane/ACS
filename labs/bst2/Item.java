import java.awt.Graphics;
import java.awt.Color;

public class Item implements Comparable<Item> {
    public String name;
    public double price;
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public void drawMe(Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.drawString(name, x, y);
    } 
    public int compareTo(Item i) {
        if(name.compareTo(i.name) == 0) {
            return (int)(price - i.price);
        }
        return name.compareTo(i.name);
    }
}