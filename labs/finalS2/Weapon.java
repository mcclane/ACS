import java.awt.Graphics;
import java.awt.Color;


public class Weapon extends Thing {
    public Weapon(double x, double y) {
        super("weapon", x, y, 0, 0);
    }
    public boolean collision(Thing thing) {
        if(thing.type.equals("player") && super.collisionIfMoved(0, 0, thing) && !thing.armed) {
            lives = 0;
            thing.armed = true;
            return true;
        }
        return super.collisionIfMoved(0, 0, thing);
    }
}