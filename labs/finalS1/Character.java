import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Stack;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Character {
    boolean right = true;
    Stack<Integer> health;
    Location l;
    int width, height;
    TreeMap<Tile, Integer> inventory;
    Font font = new Font("Arial", Font.PLAIN, 25);
    boolean showInventory = false;
    HashMap<String, BufferedImage> images;
    BufferedImage display;
    public Character(Location l) {
        this.l = l;
        width = 50;
        height = 50;
        images = new HashMap<String, BufferedImage>();
         try {
            images.put("characterUp", ImageIO.read(new File("characterUp.png")));
            images.put("characterDown", ImageIO.read(new File("characterDown.png")));
            images.put("characterLeft", ImageIO.read(new File("characterLeft.png")));
            images.put("characterRight", ImageIO.read(new File("characterRight.png")));
            //strawberryImg = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        display = images.get("characterDown");
        inventory = new TreeMap<Tile, Integer>();
        health = new Stack<Integer>();
        for(int i = 0;i < 20;i++) {
            health.push(1);
        }
    }
    public void drawMe(Graphics g) {
        g.setFont(font);
        /*g.setColor(Color.white);
        g.fillRect(l.x*width, l.y*height, width, height);
        g.setColor(Color.black);
        g.fillRect(l.x*width+width/4, l.y*height+height/4, width/2, height/2);*/
        g.drawImage(display, l.x*50, l.y*50, null);
        if(showInventory) 
            drawInventory(g, 1300, 100);
        drawHealth(g, 1300, 40);
    }
    public void drawInventory(Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.fillRect(x-10, y-10, 320, 520);
        g.setColor(Color.white);
        g.fillRect(x, y, 300, 500);
        g.setColor(Color.black);
        g.drawString("Inventory", x+20, y+20);
        for(Tile t : inventory.keySet()) {
            t.drawMe(g, x+20, y+50);
            g.drawString(t.itemType+" x "+inventory.get(t), x+100, y+75);
            y += 50;
        }       
    }
    public void drawHealth(Graphics g, int x, int y) {
        g.setFont(font);
        Iterator<Integer> it = health.iterator();
        g.setColor(Color.black);
        g.fillRect(x-10, y-10, 440, 70);
        g.setColor(Color.white);
        g.fillRect(x, y, 420, 50);
        g.setColor(Color.black);
        g.drawString("Health", x+180, y+20);
        g.setColor(Color.green);
        while(it.hasNext()) {
            it.next();
            g.fillRect(x+10, y+20, 20, 10);
            x += 20;
        }
    }
    /*public void move(int dx, int dy, HashMap<Location, Thing> context) {
        Location futureLocation = new Location(l.x+dx, l.y+dy); 
        if(context.containsKey(futureLocation)) {
            //we can move if there is a "Nothing"
            if(context.get(futureLocation) instanceof Nothing) {
                l.x += dx;
                l.y += dy;
            }
            //if we find an item, pick it up and move to where it was
            else if(context.get(futureLocation) instanceof Item) {
                addToInventory((Item)context.get(futureLocation));
                //remove the item from the grid
                context.put(futureLocation, new Nothing());
                l.x += dx;
                l.y += dy;
            }
            //if we hit an obstacle
            else if(context.get(futureLocation) instanceof Obstacle) {
                System.out.println("obstacle");
                health.pop();
                l.x += dx;
                l.y += dy;
            }
        }
    }*/
    public void addToInventory(Tile t) { 
        if(inventory.containsKey(t))
            inventory.put(t, inventory.get(t)+1);
        else
            inventory.put(t, 1);  
    }
    public void hurt() {
        health.pop();
    }
    public void toggleInventory(boolean newState) {
        showInventory = newState;
    }
    public void setOrientation(String state) {
        display = images.get(state);
    }
}
