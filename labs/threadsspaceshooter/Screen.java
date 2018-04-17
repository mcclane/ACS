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
    static Player player;
    int x = 395;
    int y = 400;
    static ArrayList<Enemy> enemies;
    Background background;
    public Screen() {
        this.setLayout(null);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        player = new Player(800, 400);
        Thread pThread = new Thread(player);
        pThread.start();

        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(100, 200));
        for(Enemy e : enemies) {
            Thread th = new Thread(e);
            th.start();
        }

        background = new Background();
        Thread bThread = new Thread(background);
        bThread.start();
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1600,800);
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1600, 800);
        background.render(g);
        player.render(g);
        for(int i = 0;i < enemies.size();i++) {
            if(enemies.get(i).visible) {
                enemies.get(i).render(g);
            }
            else {
                enemies.remove(i);
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
        player.shooting = true;
        //player.shoot(e.getX(), e.getY());
        player.shoot();
    }
    public void mouseReleased(MouseEvent e) {
        player.shooting = false;
    }
    public void mouseMoved(MouseEvent e) {
        player.rotate(e.getX(), e.getY());
        repaint();
        //System.out.println("("+e.getX()+", "+e.getY()+")");
    }
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }
}