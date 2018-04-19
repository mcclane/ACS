import java.awt.Graphics;
import java.awt.Color;

public class Enemy implements Runnable {
    double x, y, dx, dy;
    Player player;
    static int width = 20;
    static int height = 20;
    int lives;
    public boolean visible = true;
    public Enemy(int x, int y, int lives, Player player) {
        this.x = x;
        this.y = y;
        this.lives = lives;
        this.player = player;
    }
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawRect((int)x, (int)y, width, height);
        g.drawString(""+lives, (int)x + width/2, (int)y + width/2);
    }
    public void hit() {
        lives--;
    }
    public boolean dead() {
        return lives <= 0;
    }
    public void run() {
        while(true) {
            dx = player.x - x;
            dy = player.y - y;
            double magnitude = Math.sqrt(dx*dx + dy*dy);
            dx = dx/magnitude;
            dy = dy/magnitude;
            x = x + dx;
            y = y + dy;
            if(y < 0 || y > 800 || x < 0 || x > 1600) {
                visible = false;
                return;
            }
            if(Thread.interrupted()) {
                return;
            }
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                return;
            }
        }
    }
    public boolean collision(int x, int y, int height, int width) {
        if(this.x + this.width > x && this.x < x + width && this.y + this.height > y && this.y < y + height) {
            return true;
        }
        return false;
    }
}