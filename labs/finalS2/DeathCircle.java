import java.awt.Graphics;
import java.awt.Color;


public class DeathCircle extends Thing {
    int center_x, center_y;
    int radius = 100;
    public DeathCircle() {
        super("death_circle", 0, 0, 0, 0);
        center_x = 1000;
        center_y = 1000;
    }
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(center_x, center_y, radius, radius);
    }
    public void contract() {
        radius -= 25;   
    }
    public boolean contains(Thing thing) {
        double distance = Math.sqrt(Math.pow(thing.x - center_x, 2) + Math.pow(thing.y - center_y, 2));
        System.out.println(distance);
        return distance < radius;
    }
}