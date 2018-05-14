import java.awt.Graphics;
import java.awt.Color;

public class Obstacle extends Thing {
    int id;
    double orientationX = 0;
    double orientationY = 0;
    public Obstacle(double x, double y, int width, int height, int lives) {
        super("obstacle", x, y, 0, 0);
        this.width = width;
        this.height = height;
        this.lives = lives;
    }
    public void render(Graphics g) {
        g.setColor(Colors.WOOD);
        g.fillRect((int)(x), (int)(y), width, height);
        g.setColor(Colors.DARK_WOOD);
        g.fillRect((int)(x), (int)(y), width/5, height);
        g.fillRect((int)(x)+4*width/5, (int)(y), width/5, height);
        g.fillRect((int)(x), (int)(y), width, height/5);
        g.fillRect((int)(x), (int)(y)+4*height/5, width, height/5);
    }
    public void hit() {
        lives--;
        x += height/8;
        y += height/8;
        height -= height/4;
        width -= width/4;
    }
}