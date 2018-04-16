import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Screen extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    Player player;
    int x = 395;
    int y = 400;
    
    ArrayList<Missile> missiles;
    public Screen() {
        this.setLayout(null);
        player = new Player(50, 50);
        Thread pThread = new Thread(player);
        pThread.start();
        
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        missiles = new ArrayList<Missile>();
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1600,800);
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 1600, 800);
        player.drawMe(g);
        for(int i = 0;i < player.missiles.size();i++) {
            if(player.missiles.get(i).visible) {
                player.missiles.get(i).drawMe(g);
            }
            else {
                player.missiles.remove(i);
                i--;
            }
        }
        
    }
    public void animate() {
        while(true) {   
            repaint();
        }
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        player.shoot(e.getX(), e.getY());
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {
        player.rotate(e.getX(), e.getY());
        repaint();
        //System.out.println("("+e.getX()+", "+e.getY()+")");
    }
    public void mouseDragged(MouseEvent e) {}
}