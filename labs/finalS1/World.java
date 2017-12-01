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
import javax.swing.ImageIcon;

public class World {
    int horizontalBound = 0;
    int verticalBound = 0;
    int viewWindowSize = 20;
    HashMap<Location, Thing> grid;
    public World() {
        grid = new HashMap<Location, Thing>();
        
        //read in the file to set up level
        try {
            Scanner scan = new Scanner(new File("level1.txt"));
            int x = 0;
            int y = 0;
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                for(int i = 0;i < line.length();i++) {
                    Location loc = new Location(x, y);
                    switch(line.charAt(i)) {
                        case 'n':
                            grid.put(loc, new Nothing());
                            break;
                        case 'O':
                            grid.put(loc, new Item("Orange"));
                            break;
                        case 'A':
                            grid.put(loc, new Item("Apple"));
                            break;
                        case 'w':
                            grid.put(loc, new Wall());
                            break;
                        case 'o':
                            grid.put(loc, new Obstacle());
                            break;
                    }
                    x = (x+1)%line.length();
                }
                y++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void drawMe(Graphics g) {
        int wx = 0;
        int wy = 0;
        for(int x = horizontalBound;x < horizontalBound+viewWindowSize;x++) {
            for(int y = verticalBound;y < verticalBound+viewWindowSize;y++) {
                Location temp = new Location(x, y);
                if(grid.containsKey(temp)) {
                    grid.get(temp).drawMe(g, wx*50, wy*50);
                }
                wy = (wy+1)%viewWindowSize;
            }
            wx = (wx+1)%viewWindowSize;
        }
    }
    public void move(int dx, int dy) {
        horizontalBound += dx;
        verticalBound += dy;
    }
}