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
        g.fillRect(l.x*width+width/4, l.y*height+height/4, width/2, height/2);
        drawInventory(g, 50, 930);
    }
    public void drawInventory(Graphics g, int x, int y) {
       for(Item i : inventory.keySet()) {
           i.drawMe(g, x, y);
           x += 50;
       }
    }
    public void move(int dx, int dy, HashMap<Location, Thing> context) {
        Location futureLocation = new Location(l.x+dx, l.y+dy); 
        if(context.containsKey(futureLocation)) {
            //we can move if there is a "Nothing"
            if(context.get(futureLocation) instanceof Nothing) {
                System.out.println("we get to move to nothing!");
                l.x += dx;
                l.y += dy;
            }
            //if we find an item, pick it up and move to where it was
            else if(context.get(futureLocation) instanceof Item) {
                System.out.println("you found an item");
                addToInventory((Item)context.get(futureLocation));
                //remove the item from the grid
                context.put(futureLocation, new Nothing());
                l.x += dx;
                l.y += dy;
            }
        }
    }
    public void addToInventory(Item i) { 
        if(inventory.containsKey(i))
            inventory.put(i, inventory.get(i)+1);
        else
            inventory.put(i, 1);  
    }
}