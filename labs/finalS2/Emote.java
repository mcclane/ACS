import java.util.Graphics;
import java.util.Colors;


public class Emote extends Thing {
    String type;
    public Emote(String type, double x, double y) {
        super("emote", x, y, 0, 0);
        this.type = type;
        this.lives = 300;
    }
    public void render(Graphics g) {
        if(type.equals("happy")) {
            g.setColor(Color.green);
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
