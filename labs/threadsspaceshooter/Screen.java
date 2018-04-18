import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
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
	Game game;
    static Player player;
    int x = 395;
    int y = 400;
    boolean loaded = false;
    boolean started = false;
    static ArrayList<Enemy> enemies;
    Background background;
    public Screen() {
        this.setLayout(null);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        load();
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1600,800);
    }
    public void load() {
		game = new Game();
        player = new Player(1400, 400);

        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(100, 200));
        enemies.add(new Enemy(200, 200));
        enemies.add(new Enemy(300, 200));
        enemies.add(new Enemy(400, 200));
        enemies.add(new Enemy(500, 200));
        enemies.add(new Enemy(100, 300));
        enemies.add(new Enemy(200, 300));
        enemies.add(new Enemy(300, 300));
        enemies.add(new Enemy(400, 300));
        enemies.add(new Enemy(500, 300));
        enemies.add(new Enemy(100, 400));
        enemies.add(new Enemy(200, 400));
        enemies.add(new Enemy(300, 400));
        enemies.add(new Enemy(400, 400));
        enemies.add(new Enemy(500, 400));
        enemies.add(new Enemy(100, 500));
        enemies.add(new Enemy(200, 500));
        enemies.add(new Enemy(300, 500));
        enemies.add(new Enemy(400, 500));
        enemies.add(new Enemy(500, 500));

        background = new Background();
        Thread bThread = new Thread(background);
        bThread.start();

    }
    public void start() {
        Thread pThread = new Thread(player);
        pThread.start();
        for(Enemy e : enemies) {
            Thread th = new Thread(e);
            th.start();
        }
    }
    public void showStartScreen(Graphics g) {
        g.setFont(new Font("DIALOG", 0, 30));
        g.drawString("Press Space to Begin", 650, 400);
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1600, 800);
        background.render(g);
		game.render(g);
        if(!started) {
            showStartScreen(g);
        }
        else {
            player.render(g);
            for(int i = 0;i < enemies.size();i++) {
                if(enemies.get(i).visible) {
                    enemies.get(i).render(g);
                }
                else {
                    if(enemies.size() > 0) {
                        enemies.remove(i);
                        i--;
                    }
                    else {
                        break;
                    }
                }
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
            if(!started) {
                start();
            }
            started = true;
            repaint();
        }
    }
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
        if(Math.sqrt(Math.pow(player.x+player.width/2 - e.getX(), 2) + Math.pow(player.y+player.height/2 - e.getY(), 2)) > player.height) {
            player.rotate(e.getX(), e.getY());
        }
        //  repaint();
        //System.out.println("("+e.getX()+", "+e.getY()+")");
    }
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }
}