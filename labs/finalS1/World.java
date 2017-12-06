import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class World {
    int horizontalOffset = 45;
    int verticalOffset = 45;
    int horizontalViewWindowSize = 37;
    int verticalViewWindowSize = 20;

    HashMap<Location, Tile> grid;
    HashMap<Location, Enemy> enemies;

    public World(String filename) {
        enemies = new HashMap<Location, Enemy>();
        enemies.put(new Location(70, 70), new Enemy());
        grid = new HashMap<Location, Tile>();
        
        //read in the file to set up level
        try {
            BufferedImage mapImage = ImageIO.read(new File(filename));
            for(int r = 0;r < 150;r++) {
                for(int c = 0;c < 150;c++) {
                    Location loc = new Location(c, r);
                    int clr = mapImage.getRGB(c, r);
                    Color read = new Color((clr & 0x00ff0000) >> 16, (clr & 0x0000ff00) >> 8, clr & 0x000000ff);
                    if(read.getRed() == 0 && read.getGreen() == 0 && read.getBlue() == 0) {
                        //nothing
                    }
                    else if(read.getRed() == 255 && read.getGreen() == 255 && read.getBlue() == 255) {
                        grid.put(loc, new Tile("dirt", "dirt.png"));
                    }
                    else if(read.getRed() == 0 && read.getGreen() == 255 && read.getBlue() == 0) {
                        grid.put(loc, new Tile("tree", "palmtreewithdirt.png"));
                    }
                    else if(read.getRed() == 102 && read.getGreen() == 51 && read.getBlue() == 0) {
                        grid.put(loc, new Tile("wall", "rock.png"));
                    }
                    else if(read.getRed() == 0 && read.getGreen() == 0 && read.getBlue() == 255) {
                        grid.put(loc, new Tile("water", "water.png"));
                    }
                    else if(read.getRed() == 255 && read.getGreen() == 0 && read.getBlue() == 0) {
                        grid.put(loc, new Tile("mine", "mine.png"));
                    }
                    else if(read.getRed() == 50 && read.getGreen() == 100 && read.getBlue() == 150) {
                        grid.put(loc, new Tile("item", "Sail", "sail.png"));
                    }
                    else if(read.getRed() == 255 && read.getGreen() == 255 && read.getBlue() == 0) {
                        grid.put(loc, new Tile("sand", "sand.png"));
                    }
                    else if(read.getRed() == 200 && read.getGreen() == 50 && read.getBlue() == 200) {
                        grid.put(loc, new Tile("item", "Mast", "mast.png"));
                    }
                    else if(read.getRed() == 100 && read.getGreen() == 150 && read.getBlue() == 155) {
                        grid.put(loc, new Tile("item", "Rudder", "rudder.png"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawMe(Graphics g) {
        int wx = 0;
        int wy = 0;
        Location temp;
        for(int x = horizontalOffset;x < horizontalOffset+horizontalViewWindowSize;x++) {
            for(int y = verticalOffset;y < verticalOffset+verticalViewWindowSize;y++) {
                temp = new Location(x, y);
                if(grid.containsKey(temp)) {
                    grid.get(temp).drawMe(g, wx*50, wy*50);
                }
                if(enemies.containsKey(temp)) {
                    enemies.get(temp).drawMe(g, wx*50, wy*50);
                }
                wy = (wy+1)%verticalViewWindowSize;
            }
            wx = (wx+1);
        }
    }
    public void move(int dx, int dy) {
        horizontalOffset += dx;
        verticalOffset += dy;
    }
    public void moveEnemies(Character c) {
        int clx = c.l.x + horizontalOffset;
        int cly = c.l.y + verticalOffset;
        HashMap<Location, Enemy> newEnemies = new HashMap<Location, Enemy>();
        ArrayList<Location> adjacent;
        Location actualCharacterLocation = new Location(c.l.x+horizontalOffset, c.l.y+verticalOffset);
        for(Location el : enemies.keySet()) {
            Enemy e = enemies.get(el);
            newEnemies.put(getMove(getCounters(el, actualCharacterLocation, enemies), el), enemies.get(el));
        }
        enemies = newEnemies;
    }
    public Location getMove(HashMap<Location, Integer> counters, Location l) {
        ArrayList<Location> adjacent = new ArrayList<Location>();
        adjacent.add(new Location(l.x-1, l.y));
        adjacent.add(new Location(l.x+1, l.y));
        adjacent.add(new Location(l.x, l.y+1));
        adjacent.add(new Location(l.x, l.y-1));
        for(int i = 0;i < adjacent.size();i++) {
            if(!counters.containsKey(adjacent.get(i))) {
                    adjacent.remove(i);
                    i--;
            }
        }
        Location smallestCounter = adjacent.get(0);
        for(int i = 0;i < adjacent.size();i++) {
            if(counters.get(adjacent.get(i)) < counters.get(smallestCounter))
                smallestCounter = adjacent.get(i);
        }
        return smallestCounter;
    }
    public HashMap<Location, Integer> getCounters(Location S, Location O, HashMap<Location, Enemy> enemies) {
        HashMap<Location, Integer> counters = new HashMap<Location, Integer>();
        Queue<Location> queue = new LinkedList<Location>();
        queue.add(O);
        counters.put(new Location(O.x, O.y), 0);
        int counter = 0;
        ArrayList<Location> adjacent;
        boolean done = false;
        while(!done) {
            O = queue.poll();
            adjacent = new ArrayList<Location>();
            adjacent.add(new Location(O.x-1, O.y));
            adjacent.add(new Location(O.x+1, O.y));
            adjacent.add(new Location(O.x, O.y+1));
            adjacent.add(new Location(O.x, O.y-1));
            for(int i = 0;i < adjacent.size();i++) {
                if(!isAllowed(adjacent.get(i))) {
                    adjacent.remove(i);
                    i--;
                }
            }
            for(int i = 0;i < adjacent.size();i++) {
                if(counters.containsKey(adjacent.get(i)))
                    continue;
                else if(adjacent.get(i).x == S.x && adjacent.get(i).y == S.y)
                    done = true;
                else {
                    counters.put(adjacent.get(i), counter);
                    queue.add(adjacent.get(i));
                }
            }
            counter++;
        }
        return counters;
    }
    public boolean isAllowed(Location l) {
        if(grid.get(l) == null)
            return false;
        switch(grid.get(l).type) {
            case "dirt":
                return true;
            case "hole":
                return true;
            case "sand":
                return true;
            case "mine":
                return true;
            case "item":
                return true;
        }
        return false;
    }
    public void move(int dx, int dy, Character c) {
        Location possibleCharacterLocation = new Location(horizontalOffset+c.l.x+dx, verticalOffset+c.l.y+dy);
        if(grid.containsKey(possibleCharacterLocation)) {
            switch(grid.get(possibleCharacterLocation).type) {
                case "dirt":
                    horizontalOffset += dx;
                    verticalOffset += dy;
                    break;
                case "hole":
                    horizontalOffset += dx;
                    verticalOffset += dy;
                    break;
                case "sand":
                    horizontalOffset += dx;
                    verticalOffset += dy;
                    break;
                case "mine":
                    c.hurt();
                    grid.put(possibleCharacterLocation, new Tile("hole", "hole.png"));
                    horizontalOffset += dx;
                    verticalOffset += dy;
                    break;
                case "item":
                    c.addToInventory(grid.get(possibleCharacterLocation));
                    grid.put(possibleCharacterLocation, new Tile("dirt", "dirt.png"));
                    horizontalOffset += dx;
                    verticalOffset += dy;
                    break;
            }
        }
    }
}