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
    boolean recoil = false;
    int recoilUntilTicker = 0;
    boolean invincible = false;
    int invincibleUntilTicker = 0;
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        missiles = new ArrayList<Missile>();
    }
    public boolean dead() {
        return lives <= 0;
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
        g.drawString("Lives: "+lives, 1500, 50);
        g2 = (Graphics2D) g.create();
        g2.setColor(Color.white);
        g2.rotate(angle, x+width/2, y+height/2);
        g2.drawRect((int)x+height/2, (int)y+height/2, width*2, height/4);
        g2.drawRect((int)x, (int)y, height, width);
        for(int i = 0;i < missiles.size();i++) {
            if(missiles.get(i).visible) {
                missiles.get(i).render(g);
            }
        }
    }
    public void makeInvincibleForALittle() {
        invincible = true;
        invincibleUntilTicker = ticker + 100;
    }
    public synchronized void shoot() {
        Sound.playSound("bam.wav");
        Missile m = new Missile((int)x+width/2-Missile.width/2, (int)y+height/2-Missile.height/2, dx, dy);
        Thread t = new Thread(m);
        t.start();
        missiles.add(m);
        recoil = true;
        recoilUntilTicker = ticker + 10;
    }
    public void move() {
        if(ticker > invincibleUntilTicker) {
            invincible = false;
        }
        if(ticker > recoilUntilTicker) {
            recoil = false;
        }
        if(recoil && ticker < recoilUntilTicker) {
            if(x - 4*dx > 0 && x - 4*dx < 1600) {
                x -= 4*dx;
            }
            if(y - 4*dy > 0 && y - 4*dy < 800) {
                y -= 4*dy;
            }
        }
        else {
            if(x + 2*dx > 0 && x + 2*dx < 1600) {
                x += 2*dx;
            }
            if(y + 2*dy > 0 && y + 2*dy < 800) {
                y += 2*dy;
            }
        }
        if(shooting && ticker % 25 == 0) {
            shoot();
        }
        ticker++;
    }
}