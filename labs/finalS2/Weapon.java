import java.awt.Graphics;
import java.awt.Color;


public class Weapon extends Thing {
    public Weapon(double x, double y) {
        super("weapon", x, y, 0, 0);
    }
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int)x, (int)y, 30, 10);
        g.fillRect((int)x, (int)y, 10, 20);
    }
    public boolean collision(Thing thing) {
        if(thing.type.equals("player") && super.collisionIfMoved(0, 0, thing) && !thing.armed) {
            lives = 0;
            thing.armed = true;
            Sound.playSound("sound/gun_pickup_01.mp3");
            return true;
        }
        return super.collisionIfMoved(0, 0, thing);
    }
}