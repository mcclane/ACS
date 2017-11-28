import java.awt.Graphics;
import java.awt.Color;

public class Item implements Comparable<Item> {
    int x, y;
    String name;
    public Item(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public void drawMe(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 25, 25);
    }
    public void drawMe(Graphics g, int x, int y) {
        g.setColor(Color.red);
        g.fillRect(x, y, 25, 25);
    }
    public int compareTo(Item i) {
        return name.compareTo(i.name);
    }
}