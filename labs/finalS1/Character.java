import java.awt.Graphics;
import java.awt.Color;
import java.util.TreeMap;

public class Character {
    int x, y;
    boolean right = true;
    TreeMap<Item, Integer> inventory;
    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void move(int dx, int dy) {
        if(dx > 0) {
            right = false;
        }
        else if(dx < 0) {
            right = true;
        }
        x += dx;
        y += dy;
    }
    public void drawMe(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 65, 100);
        g.setColor(Color.black);
        if(!right)
            g.fillRect(x+25, y, 40, 75);
        else
            g.fillRect(x, y, 40, 75);
    }
    public void drawInventory(Graphics g, int x, int y) {
       
    }
}