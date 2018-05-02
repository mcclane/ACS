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


class Screen extends JPanel implements MouseListener, MouseMotionListener {
    Input input;
    int playerCount = 0;
    ClientInterface ci;
    int playerHashCode;
    HashMap<Integer, Thing> state;
    int view = 0;
    public Screen(ClientInterface ci) {
        this.setLayout(null);
        this.setFocusable(true);

        input = new Input(this);

        addMouseListener(this);
        addMouseMotionListener(this);

        this.ci = ci;
        playerHashCode = (int)(Math.random()*100000);
        //ci.send(new Event("player_connect", playerHashCode));

        state = new HashMap<Integer, Thing>();
    }
    public synchronized void update(HashMap<Integer, Thing> state) { // this functions as an animate
        synchronized(state) {
            //System.out.println(state);
            this.state = state;
            // send a move event for smooth movement.
            if(input.keyboard[87]) {
                ci.send(new Event("player_move", playerHashCode, "up"));
            }
            // s - down
            if(input.keyboard[83]) {
                ci.send(new Event("player_move", playerHashCode, "down"));
            }
            // d - right
            if(input.keyboard[68]) {
                ci.send(new Event("player_move", playerHashCode, "right"));
            }
            // a - left
            if(input.keyboard[65]) {
                ci.send(new Event("player_move", playerHashCode, "left"));
            }
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
            if(view == 0) {
                ImageReader.drawImage(g, "images/start_screen.png", 0, 0, 1200, 800);
                // count the number of players connected:
                playerCount = 0;
                for(int key : state.keySet()) {
                    try {
                        if(state.get(key).type.equals("player")) {
                            playerCount++;
                        }
                    } catch(NullPointerException e) {
                        System.out.println("ooooh, another null pointer exception");
                    }
                }
                g.setColor(Color.black);
                g.drawString(""+playerCount, 1020, 620);
            }
            // draw a cursor on the screen
            //ImageReader.drawImage(g, "images/cursor.png", Input.x-12, Input.y-12, 25, 25);
            for(int key : state.keySet()) {
                //System.out.println(key);
                try {
                    state.get(key).render(g);
                } catch(NullPointerException e) {
                    System.out.println("Ooooh, another null pointer exception");
                }
            }
        }
    }
    /*public void keyPressed(KeyEvent e) {
        int keyDown = e.getKeyCode();
        if(view == 1) {
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
    }*/
    public void mousePressed(MouseEvent e) {
        if(view == 0) {
            ci.send(new Event("player_connect", playerHashCode));
            view++;
        }
        else if(view == 1) {
            ci.send(new Event("player_shoot", playerHashCode, e.getX(), e.getY()));
        }
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