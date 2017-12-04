import java.awt.Graphics;
import java.awt.Color;

public abstract class Thing {
    int x, y;
    int width = 50;
    int height = 50;
    public void drawMe(Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);
    }
}