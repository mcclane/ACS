import java.awt.Graphics;
import java.awt.Color;

public class Projectile extends Thing {
    double scaleFactor = 25;
    public Projectile(double x, double y, double dx, double dy) {
        super("projectile", x, y, dx, dy);
    }
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.drawLine((int)(x), (int)(y), (int)(x + dx*scaleFactor), (int)(y + dy*scaleFactor));
    }
    public void move() {
        x += dx * 20;
        y += dy * 20;
    }
    public boolean collision(Thing thing) {
        int steps = 100;
        
        int nx = (int)(x);
        int ny = (int)(y);
        return thing.width + (int)thing.x > nx && nx + width > (int)(thing.x) && thing.height + (int)thing.y > ny && ny + height > (int)(thing.y);
    }
}