import java.awt.Graphics;
import java.awt.Color;

public class Obstacle extends Thing {
    int id;
    double orientationX = 0;
    double orientationY = 0;
    public Obstacle(double x, double y, int width, int height) {
        super("obstacle", x, y, 0, 0);
        this.width = width;
        this.height = height;
    }
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int)(x), (int)(y), width, height);
    }
}