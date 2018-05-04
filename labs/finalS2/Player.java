import java.awt.Graphics;
import java.awt.Color;

public class Player extends Thing {
    int id;
    double orientationX = 1;
    double orientationY = 1;
    public Player(int id, double x, double y) {
        super("player", x, y, 0, 0);
        this.id = id;
        this.lives = 10;
        this.height = 40;
        this.width = 40;
    }
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.drawString(""+id, (int)(x), (int)(y));
        g.setColor(Colors.PLAYER);
        g.fillOval((int)(x), (int)(y), height, width);
        g.setColor(Color.black);
        g.drawOval((int)x, (int)y, height, width);
        if(armed) {
            g.setColor(Color.black);
            g.drawLine((int)(x+height/2), (int)(y+height/2), (int)((x+height/2)+(orientationX*25)), (int)((y+height/2)+(orientationY*25)));
            g.drawLine((int)(x+height/2+1), (int)(y+height/2+1), (int)((x+12.5+1)+(orientationX*25)), (int)((y+height/2+1)+(orientationY*25)));
            g.drawLine((int)(x+height/2+1), (int)(y+height/2-1), (int)((x+height/2+1)+(orientationX*25)), (int)((y+height/2-1)+(orientationY*25)));
        }
    }
    public int hashCode() {
        return id;
    }
}