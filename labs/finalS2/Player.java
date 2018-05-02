import java.awt.Graphics;
import java.awt.Color;

public class Player extends Thing {
    int id;
    double orientationX = 0;
    double orientationY = 0;
    public Player(int id, double x, double y) {
        super("player", x, y, 0, 0);
        this.id = id;
    }
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.drawString(""+id, (int)(x), (int)(y));
        g.drawOval((int)(x-12.5), (int)(y-12.5), 25, 25);
        if(armed) {
            g.setColor(Color.black);
            g.drawLine((int)(x), (int)(y), (int)((x)+(orientationX*25)), (int)((y)+(orientationY*25)));
        }
    }
    public int hashCode() {
        return id;
    }
}