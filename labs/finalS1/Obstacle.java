import java.awt.Graphics;
import java.awt.Color;

public class Obstacle extends Thing {
    public Obstacle() {}
    public void drawMe(Graphics g, int x, int y) {
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }
}
