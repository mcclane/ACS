import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class classwork {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Screen");
 
        Screen sc = new Screen();
        frame.add(sc);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        sc.animate();
    }
}
class Screen extends JPanel implements KeyListener {
    Thing thing;
    int x = 395;
    int y = 400;
    
    ArrayList<Missile> missiles;
    public Screen() {
        this.setLayout(null);
        thing = new Thing(50, 50);
        Thread thread = new Thread(thing);
        thread.start();
        
        setFocusable(true);
        addKeyListener(this);
        
        missiles = new ArrayList<Missile>();
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,400);
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 400);
        thing.drawMe(g);
        for(int i = 0;i < missiles.size();i++) {
            if(missiles.get(i).visible) {
                missiles.get(i).drawMe(g);
            }
            else {
                missiles.remove(i);
                i--;
            }
        }
        
    }
    public void animate() {
        while(true) {   
            repaint();
        }
    }
    public void keyPressed(KeyEvent e) {
        int keyDown = e.getKeyCode();
        if(keyDown == 32) {
            Missile m = new Missile(x, y);
            Thread th = new Thread(m);
            th.start();
            missiles.add(m);
        }
        else if(keyDown == 37) { // left
            x -= 10;
        }
        else if(keyDown == 39) { // right
            x += 10;
        }
    }
    public void keyReleased(KeyEvent e) {
        int keyDown = e.getKeyCode();
    }
    public void keyTyped(KeyEvent e) {}
}
class Missile implements Runnable {
    int x = 375;
    int y = 400;
    int speed = 1;
    boolean visible = true;
    public Missile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void drawMe(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(x, y, 10, 20);
    }
    public void run() {
        while(true) {
            y -= speed;
            if(y < 0) {
                visible = false;
                return;
            }
            try {
                Thread.sleep(5);
            } catch(InterruptedException e) {
                return;
            }
        }
    }
}
class Thing implements Runnable {
    int x, y;
    double t = 0;
    int speed = 1;
    int leftBound = 0;
    int rightBound = 400;
    public Thing(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void drawMe(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(x, y, 20, 20);
    }
    public void run() {
        while(true) {
            x = (int)(Math.cos(t)*100.0)+100;
            y = (int)(Math.sin(t)*100.0)+100;
            t += 0.01;
            try {
                Thread.sleep(5);
            } catch(InterruptedException e) {
                return;
            }
        }
    }
}