import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Item extends Thing implements Comparable<Item> {
    String name;
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
        g.setColor(Color.black);
        Font font = new Font("Arial", Font.PLAIN, 8);
        g.setFont(font);
        g.drawString(name, x+width/10, y+height/5);
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