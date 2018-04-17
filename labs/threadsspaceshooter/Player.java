import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class Player implements Runnable {
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
        double dx = mx - x;
        double dy = my - y;
        double magnitude = Math.sqrt(dx*dx + dy*dy);
        this.dx = dx/magnitude;
        this.dy = dy/magnitude;
        if(dx != 0 && dy != 0) {
            angle = Math.atan(dy/dx);
        }
    }
    public void render(Graphics g) {
        g2 = (Graphics2D) g.create();
        g2.setColor(Color.red);
        g.drawString(""+lives, (int)x+width/2, (int)y+height/2);
        g2.rotate(angle, x+width/2, y+height/2);
        g2.drawRect((int)x, (int)y, height, width);
        for(int i = 0;i < missiles.size();i++) {
            if(missiles.get(i).visible) {
                missiles.get(i).render(g);
            }
            else {
                missiles.remove(i);
                i--;
            }
        }
    }
    public void shoot() {
        Missile m = new Missile((int)x+width/2-Missile.width/2, (int)y+height/2-Missile.height/2, dx, dy);
        Thread t = new Thread(m);
        t.start();
        missiles.add(m);
    }
    public void run() {
        while(true) {
            x = x + dx;
            y = y + dy;
            if(shooting && ticker % 25 == 0) {
                shoot();
            }
            ticker++;
            // check collisions:
            for(int i = 0;i < missiles.size();i++) {
                for(int j = 0;j < Screen.enemies.size();j++) {
                    if(Screen.enemies.get(j).collision((int)missiles.get(i).x, (int)missiles.get(i).y, Missile.width, Missile.height)) {
                        missiles.get(i).visible = false;
                        missiles.remove(i);
                        i--;
                        Screen.enemies.get(j).visible = false;
                        Screen.enemies.remove(j);
                        j--;
                    }
                }
            }
            for(int i = 0;i < Screen.enemies.size();i++) {
                if(Screen.enemies.get(i).collision((int)x, (int)y, width, height)) {
                    lives--;
                    Screen.enemies.get(i).visible = false;
                    Screen.enemies.remove(i);
                    i--;
                }
            }
            try {
                Thread.sleep(5);
            } catch(InterruptedException e) {
                return;
            }
        }
    }
}