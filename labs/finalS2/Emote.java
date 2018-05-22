import java.awt.Graphics;
import java.awt.Color;


public class Emote extends Thing {
    String type;
    public Emote(String type, double x, double y) {
        super("emote", x, y, 0, 0);
        this.type = type;
        this.lives = 50;
    }
    public void render(Graphics g) {
        if(type.equals("happy")) {
            g.setColor(Color.green);
            g.fillOval((int)(x), (int)(y), 25, 25);
        }
        else if(type.equals("sad")) {
            g.setColor(Color.red);
            g.fillOval((int)(x), (int)(y), 25, 25);
        }
    }
    public boolean collision(Thing thing) {
        return false;
    }
    public boolean collisionIfMoved(double dx, double dy, Thing thing) {
        return false;
    }
}
