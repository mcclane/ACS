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
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Screen extends JPanel implements KeyListener, ActionListener, MouseListener {
    Character character;
    HashMap<Location, Thing> grid;
    TreeSet<Item> items;
    
    boolean up, down, right, left;
    public Screen() {
        this.setLayout(null);
        setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);
        
        grid = new HashMap<Location, Thing>();
        
        for(int x = 0;x < 20;x++) {
            for(int y = 0;y < 14;y++) {
                grid.put(new Location(x, y), new Nothing());
            }
        }
        character = new Character(new Location(5, 5));
        grid.put(new Location(10, 10), new Item("Orange"));
        items = new TreeSet<Item>();
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1000,800);
    }
    public void paintComponent(Graphics g) {
		// draw background
        Font font = new Font("Arial", Font.PLAIN, 25);
        g.setFont(font);
        g.setColor(Color.white);
        g.fillRect(0,0,1000,700);
        g.setColor(Color.white);
        g.fillRect(0, 700, 1000, 100);
        g.setColor(Color.black);
        g.drawString("Your things", 300, 720);
        
        for(Location l : grid.keySet()) {
            grid.get(l).drawMe(g, l.x*50, l.y*50);
        }
        character.drawMe(g);
    }
    public void actionPerformed(ActionEvent e) {
        
    }
    public void animate() {

        while (true) {
            //move the character
            if(up) {
                character.move(0, -1, grid);
            }
            if(down) {
                character.move(0, 1, grid);
            }
            if(left) {
                character.move(-1, 0, grid);
            }
            if(right) {
                character.move(1, 0, grid);
            }
            //check for character collisions
            
            //Wait 
            try {
                Thread.sleep(100); //milliseconds
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            repaint();
        }
 
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void keyPressed(KeyEvent e) {
        int keyDown = e.getKeyCode();
        switch(keyDown) {
            //left
            case 65:
                left = true;
                break;
            //right
            case 68:
                right = true;
                break;
            //up
            case 87:
                up = true;
                break;
            //down
            case 83:
                down = true;
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        int keyDown = e.getKeyCode();
        switch(keyDown) {
            //left
            case 65:
                left = false;
                break;
            //right
            case 68:
                right = false;
                break;
            //up
            case 87:
                up = false;
                break;
            //down
            case 83:
                down = false;
                break;
        }
    }
    public void keyTyped(KeyEvent e) {}
}
