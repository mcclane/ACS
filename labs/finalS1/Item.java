import java.awt.Graphics;
import java.awt.Color;

public class Item extends Thing implements Comparable<Item> {
    String name;
    String type = "Item";
    public Item(String name) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void drawMe(Graphics g, int x, int y) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
    public int compareTo(Item i) {
        return name.compareTo(i.name);
    }
    public boolean equals(Item i) {
        if(i.name.equals(name))
            return true;
        return false;
    }
}