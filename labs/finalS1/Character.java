import java.awt.Graphics;
import java.awt.Color;
import java.util.TreeMap;
import java.util.HashMap;

public class Character {
    boolean right = true;
    Location l;
    int width, height;
    TreeMap<Item, Integer> inventory;
    public Character(Location l) {
        this.l = l;
        width = 50;
        height = 50;
        inventory = new TreeMap<Item, Integer>();
    }
    public void drawMe(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(l.x*width, l.y*height, width, height);
        g.setColor(Color.black);
        drawInventory(g, 50, 730);
    }
    public void drawInventory(Graphics g, int x, int y) {
       for(Item i : inventory.keySet()) {
           i.drawMe(g, x, y);
           x += 50;
       }
    }
    public void move(int dx, int dy, HashMap<Location, Thing> context) {
        Location newLocation = new Location(l.x+dx, l.y+dy); 
        System.out.println(newLocation);
        if(context.containsKey(newLocation)) {
            System.out.println("it's here");
            if(context.get(newLocation).type.equals("Nothing")) {
                l.x += dx;
                l.y += dy;
            }
        }
    }
    public void addToInventory(Item i) {            
    }
}