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
    Character c;
    HashMap<String, Boolean> keys;
    TreeSet<Item> items;
    public Screen() {
        this.setLayout(null);
        setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);
        c = new Character(100, 100);
        keys = new HashMap<String, Boolean>();
        keys.put("up", false);
        keys.put("down", false);
        keys.put("left", false);
        keys.put("right", false);
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1000,800);
    }
    public void paintComponent(Graphics g) {
		// draw background
        Font font = new Font("Arial", Font.PLAIN, 25);
        g.setFont(font);
        g.setColor(Color.black);
        g.fillRect(0,0,1000,700);
        g.setColor(Color.white);
        g.fillRect(0, 700, 1000, 100);
        g.setColor(Color.black);
        g.drawString("Your things", 300, 720);
        c.drawMe(g);
    }
    public void actionPerformed(ActionEvent e) {
        
    }
    public void animate() {
 
        while (true) {
            if(keys.get("left")) {
                c.move(-1, 0);
            }
            if(keys.get("right")) {
                c.move(1, 0);
            }
            if(keys.get("up")) {
                c.move(0, -1);
            }
            if(keys.get("down")) {
                c.move(0, 1);
            }
            //Wait 
            try {
                Thread.sleep(10); //milliseconds
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
            case 65:
                keys.put("left", true);
                break;
            case 68:
                keys.put("right", true);
                break;
            case 87:
                keys.put("up", true);
                break;
            case 83:
                keys.put("down", true);
                break;
        }
        System.out.println("key code: " + e.getKeyCode()); 
    }
    public void keyReleased(KeyEvent e) {
        int keyDown = e.getKeyCode();
        switch(keyDown) {
            case 65:
                keys.put("left", false);
                break;
            case 68:
                keys.put("right", false);
                break;
            case 87:
                keys.put("up", false);
                break;
            case 83:
                keys.put("down", false);
                break;
        }
    }
    public void keyTyped(KeyEvent e) {}
}
