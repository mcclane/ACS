import java.awt.Graphics;
import java.awt.Color;

public class Missile implements Runnable {
	Thread thread;
    static int height = 5;
    static int width = 5;
    double x = 375;
    double y = 400;
    double dx, dy;
    int speed = 1;
    boolean visible = true;
    public Missile(int x, int y, double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        this.x = x;
        this.y = y;
    }
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawRect((int)x, (int)y, width, height);
    }
    public void run() {
        while(true) {
            x = x + dx;
            y = y + dy;
            if(y < -100 || y > 900 || x < -100 || x > 1700) {
                visible = false;
                return;
            }
			if(Thread.interrupted()) {
				return;
			}
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                return;
            }
        }
    }
}