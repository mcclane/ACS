import java.awt.Graphics;
import java.awt.Color;

public class Projectile extends Thing {
    double scaleFactor = 2.5;
    public Projectile(double x, double y, double dx, double dy) {
        super("projectile", x, y, dx, dy);
    }
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.drawLine((int)(x), (int)(y), (int)(x + dx*scaleFactor), (int)(y + dy*scaleFactor));
    }
}