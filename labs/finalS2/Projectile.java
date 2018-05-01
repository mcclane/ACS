import java.awt.Graphics;
import java.awt.Color;

public class Projectile extends Thing {
    public Projectile(double x, double y, double dx, double dy) {
        super("projectile", x, y, dx, dy);
    }
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.drawRect((int)x, (int)y, 5, 5);
    }
}