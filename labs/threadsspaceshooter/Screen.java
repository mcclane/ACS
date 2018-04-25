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
    public Screen() {
        this.setLayout(null);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        game = new Game();
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1600,800);
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1600, 800);
		game.render(g);
    }
    public void animate() {
        while(true) {
            game.gameLoop();
            try {
                Thread.sleep(10);
            }
            catch(InterruptedException e) {

            }
            repaint();
        }
    }
    public void keyPressed(KeyEvent e) {
        int keyDown = e.getKeyCode();
        if(keyDown == 32 && game.levelFinished()) {
            game.stop();
            game.next();
        }
    }
    public void mousePressed(MouseEvent e) {
        game.startShooting();
    }
    public void mouseReleased(MouseEvent e) {
        game.stopShooting();
    }
    public void mouseMoved(MouseEvent e) {
        game.rotatePlayer(e.getX(), e.getY());
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
