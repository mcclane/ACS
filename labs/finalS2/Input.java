import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
 
public class Input implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener {
    public static int x, y;
    public static boolean clicked;
    public static boolean entered;
    public static int mouseWheel;
    public static boolean[] mouseButtons = new boolean[3];
    public static boolean[] keyboard = new boolean[250];
 
    public Input(JPanel jpanel) {
        jpanel.setFocusable(true);
        jpanel.addKeyListener(this);
        jpanel.addMouseListener(this);
        jpanel.addMouseMotionListener(this);
        jpanel.addMouseWheelListener(this);
    }
    public void keyPressed(KeyEvent key) {
        //System.out.println("type:     "+(int)key.getKeyCode());
        keyboard[(int) key.getKeyCode()] = true;
    }
    public void keyReleased(KeyEvent key) {
        keyboard[(int) key.getKeyCode()] = false;
    }
    public void keyTyped(KeyEvent key) {
 
    }
    public void mousePressed(MouseEvent e) {
        clicked = true;
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                mouseButtons[0] = true;
                break;
            case MouseEvent.BUTTON2:
                mouseButtons[1] = true;
                break;
            case MouseEvent.BUTTON3:
                mouseButtons[2] = true;
                break;
        }
 
    }
    public void mouseReleased(MouseEvent e) {
        clicked = false;
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                mouseButtons[0] = false;
                break;
            case MouseEvent.BUTTON2:
                mouseButtons[1] = false;
                break;
            case MouseEvent.BUTTON3:
                mouseButtons[2] = false;
                break;
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        entered = true;
    }
    public void mouseExited(MouseEvent e) {
        entered = false;
    }
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseWheel += e.getWheelRotation();
    }
}