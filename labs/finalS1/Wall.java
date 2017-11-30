import java.awt.Graphics;
import java.awt.Color;

public class Wall extends Thing {
    public Wall() {}
    public void drawMe(Graphics g, int x, int y) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}
