import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class Player implements Runnable {
    ArrayList<Missile> missiles;
    double angle = Math.PI/4;
    double x, y;
    double dx, dy;
    int width = 25;
    int height = 25;
    double t = 0;
    int speed = 1;
    int leftBound = 0;
    int rightBound = 400;
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        missiles = new ArrayList<Missile>();
    }
    public void rotate(int mx, int my) {
        double dx = mx - x;
        double dy = my - y;
        double magnitude = Math.sqrt(dx*dx + dy*dy);
        this.dx = dx/magnitude;
        this.dy = dy/magnitude;
        if(dx != 0 && dy != 0) {
            angle = Math.atan(dy/dx);
        }
    }
    public void drawMe(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.red);
        g2.rotate(angle, x+width/2, y+height/2);
        g2.drawRect((int)x, (int)y, height, width);
    }
    public void shoot(int mx, int my) {
        double dx = mx - x;
        double dy = my - y;
        double magnitude = Math.sqrt(dx*dx + dy*dy);
        dx = dx/magnitude;
        dy = dy/magnitude;
        Missile m = new Missile((int)x, (int)y, dx, dy);
        Thread t = new Thread(m);
        t.start();
        missiles.add(m);
    }
    public void run() {
        while(true) {
            x = x + dx;
            y = y + dy;
            try {
                Thread.sleep(5);
            } catch(InterruptedException e) {
                return;
            }
        }
    }
}