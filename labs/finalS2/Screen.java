import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


class Screen extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    ClientInterface ci;
    int playerHashCode;
    HashMap<Integer, Thing> state;
    public Screen(ClientInterface ci) {
        this.setLayout(null);
        this.setFocusable(true);

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        this.ci = ci;
        playerHashCode = (int)(Math.random()*100000);
        ci.send(new Event("player_connect", playerHashCode));

        state = new HashMap<Integer, Thing>();
    }
    public void update(HashMap<Integer, Thing> state) {
        synchronized(state) {
            //System.out.println(state);
            this.state = state;
        }
        repaint();
    }
    public Dimension getPreferredSize() {
        return new Dimension(1200, 800);
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 1200, 800);
        synchronized(state) {
            for(int key : state.keySet()) {
                //System.out.println(key);
                state.get(key).render(g);
            }
        }
    }
    public void keyPressed(KeyEvent e) {
        int keyDown = e.getKeyCode();
        // w - up
        if(keyDown == 87) {
            ci.send(new Event("player_move", playerHashCode, "up"));
        }
        // s - down
        else if(keyDown == 83) {
            ci.send(new Event("player_move", playerHashCode, "down"));
        }
        // d - right
        else if(keyDown == 68) {
            ci.send(new Event("player_move", playerHashCode, "right"));
        }
        // a - left
        else if(keyDown == 65) {
            ci.send(new Event("player_move", playerHashCode, "left"));
        }
    }
    public void mousePressed(MouseEvent e) {
        ci.send(new Event("player_shoot", playerHashCode, e.getX(), e.getY()));
    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {

    }
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}