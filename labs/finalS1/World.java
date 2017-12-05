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

public class World {
    int horizontalOffset = 45;
    int verticalOffset = 45;
    int horizontalViewWindowSize = 37;
    int verticalViewWindowSize = 20;

    //HashMap<Location, Thing> grid;
    HashMap<Location, Tile> grid;
    public World() {
        grid = new HashMap<Location, Tile>();
        
        //read in the file to set up level
        try {
            BufferedImage mapImage = ImageIO.read(new File("level1.png"));
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
                        grid.put(loc, new Tile("item", "sail", "sail.png"));
                    }
                }
            }
            //strawberryImg = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            Scanner scan = new Scanner(new File("level1.txt"));
            int y = 0;
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                for(int i = 0;i < line.length();i++) {
                    Location loc = new Location(i, y);
                    switch(line.charAt(i)) {
                        case 'n':
                            //grid.put(loc, new Nothing());
                            grid.put(loc, new Tile("carpet", "carpet.jpg"));
                            break;
                        case 'O':
                            //grid.put(loc, new Item("Orange"));
                            break;
                        case 'A':
                            //grid.put(loc, new Item("Apple"));
                            break;
                        case 'w':
                            //grid.put(loc, new Wall());
                            grid.put(loc, new Tile("wall", "wall.jpg"));
                            break;
                        case 'o':
                            grid.put(loc, new Tile("obstacle", "obstacle.jpg"));
                            break;
                        case 'p':
                            grid.put(loc, new Tile("item", "paper", "paper.jpg"));
                            break;
                        case 'b':
                            grid.put(loc, new Tile("item", "basket", "basket.jpg"));
                            break;
                    }
                    //x = (x+1)%line.length();
                }
                y++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
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
                wy = (wy+1)%verticalViewWindowSize;
            }
            wx = (wx+1);
        }
    }
    public void move(int dx, int dy) {
        horizontalOffset += dx;
        verticalOffset += dy;
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
            }
            /*if(grid.get(possibleCharacterLocation).type.equals("carpet")) {
                horizontalOffset += dx;
                verticalOffset += dy;
            }
            else if(grid.get(possibleCharacterLocation).type.equals("obstacle")) {
                c.hurt();
                horizontalOffset += dx;
                verticalOffset += dy;
            }
            else if(grid.get(possible))
            /*else if(grid.get(possibleCharacterLocation) instanceof Item) {
                c.addToInventory((Item)grid.get(possibleCharacterLocation));
                grid.put(possibleCharacterLocation, new Nothing());
                horizontalOffset += dx;
                verticalOffset += dy;
            }*/
        }
    }
}