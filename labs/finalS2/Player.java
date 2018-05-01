import java.awt.Graphics;
import java.awt.Color;

public class Player extends Thing {
    int id;
    public Player(int id, double x, double y) {
        super("player", x, y, 0, 0);
        this.id = id;
    }
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.drawString(""+id, (int)(x), (int)(y));
        g.drawRect((int)(x), (int)(y), 25, 25);
    }
    public int hashCode() {
        return id;
    }
}