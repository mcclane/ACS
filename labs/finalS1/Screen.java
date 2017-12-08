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

public class Screen extends JPanel implements KeyListener {
    Character character;
    int counter = 0;
    int levelCounter = 1;
    //HashMap<Location, Thing> grid;
    World w;
    boolean levelStartScreen = true;
    
    boolean up, down, right, left;
    public Screen() {
        this.setLayout(null);
        setFocusable(true);
        addKeyListener(this);
        
        //grid = new HashMap<Location, Thing>();
        w = new World("level"+levelCounter+".png");
        
        character = new Character(new Location(18, 11)); 
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1900,1000);
    }
    public void paintComponent(Graphics g) {
		// draw background
        Font font = new Font("Arial", Font.PLAIN, 25);
        g.setFont(font);
        g.setColor(Color.white);
        g.fillRect(0,0,1900,1000);

        w.drawMe(g);
        character.drawMe(g);
        if(levelStartScreen) {
            if(levelCounter == 1) {
                g.setColor(Color.black);
                g.fillRect(0, 0, 1900, 1000);
                g.setColor(Color.white);
                g.drawString("You are stranded on an island. Collect three parts for your boat and come back to the sandy dock", 500, 500);
                g.drawString("Press any key to continue", 500, 700);
            }
            else if(levelCounter == 2) {
                g.setColor(Color.black);
                g.fillRect(0, 0, 1900, 1000);
                g.setColor(Color.white);
                g.drawString("You are stranded on another island. Collect three parts for your boat and come back to the sandy dock", 400, 500);
                g.drawString("Press any key to continue", 500, 700);
            }
        }
        g.setColor(Color.green);
        g.drawString("Level: "+levelCounter, 50, 50);

    }
    
    public void animate() {
        while (true) {
            //move the world
            if(up) {
                w.move(0, -1, character);
            }
            if(down) {
                w.move(0, 1, character);
            }
            if(left) {
                w.move(-1, 0, character);
            }
            if(right) {
                w.move(1, 0, character);
            }
            if(character.dead) {
                w = new World("level1.png");
                character = new Character(new Location(18, 11)); 
            }
            if(counter%(4-levelCounter) == 0)
                w.moveEnemies(character);
            if(counter%(7-levelCounter) == 0) {
                w.spawnEnemy(levelCounter*20);
            }
            if(w.checkDone(character))
                levelUp();
            w.checkEnemyCollisions(character);
            //Wait 
            try {
                Thread.sleep(100); //milliseconds
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            counter++;
            repaint();
        }
    } 
    public void levelUp() {
        System.out.println("level up!");
        levelCounter++;
        w = new World("level"+levelCounter+".png");
        w.spawnEnemy(levelCounter*20);
        levelStartScreen = true;
        //character = new Character(new Location(18, 11));
    }
    public void keyPressed(KeyEvent e) {
        levelStartScreen = false;
        int keyDown = e.getKeyCode();
        switch(keyDown) {
            //left
            case 65:
                left = true;
                character.setOrientation("characterLeft");
                break;
            //right
            case 68:
                right = true;
                character.setOrientation("characterRight");
                break;
            //up
            case 87:
                up = true;
                character.setOrientation("characterUp");
                break;
            //down
            case 83:
                down = true;
                character.setOrientation("characterDown");
                break;
            case 73:
                 character.toggleInventory(true);
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
            case 73:
                character.toggleInventory(false);
                break;
        }
        if(left)
            character.setOrientation("characterLeft");
        else if(right)
            character.setOrientation("characterRight");
        else if(up)
            character.setOrientation("characterUp");
        else if(down)
            character.setOrientation("characterDown");
    }
    public void keyTyped(KeyEvent e) {}
}
