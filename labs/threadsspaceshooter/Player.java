import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class Player {
    int lives = 3;
    boolean shooting = false;
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
    Graphics2D g2;
    int ticker = 0;
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        missiles = new ArrayList<Missile>();
    }
    public void rotate(int mx, int my) {
        double dx = mx - (x+width/2);
        double dy = my - (y+height/2);
        double magnitude = Math.sqrt(dx*dx + dy*dy);
        this.dx = dx/magnitude;
        this.dy = dy/magnitude;
        if(dx != 0) {
            angle = Math.atan(dy/dx);
            if(dx < 0) {
                angle += Math.PI;
            }
        }
    }
    public void render(Graphics g) {
        g2 = (Graphics2D) g.create();
        g2.setColor(Color.white);
        g.drawString("Lives: "+lives, 1500, 50);
        g2.rotate(angle, x+width/2, y+height/2);
        g2.drawRect((int)x+height/2, (int)y+height/2, width*2, height/4);
        g2.drawRect((int)x, (int)y, height, width);
        for(int i = 0;i < missiles.size();i++) {
            if(missiles.get(i).visible) {
                missiles.get(i).render(g);
            }
        }
    }
    public synchronized void shoot() {
        Missile m = new Missile((int)x+width/2-Missile.width/2, (int)y+height/2-Missile.height/2, dx, dy);
        Thread t = new Thread(m);
        t.start();
        missiles.add(m);
    }
    public void move() {
        x += dx;
        y += dy;
        if(shooting && ticker % 25 == 0) {
                shoot();
        }
        ticker++;
    }
}