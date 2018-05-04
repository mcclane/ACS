import java.awt.Graphics;
import java.util.ArrayList;


public class Enemy extends Thing {
    public Enemy(double x, double y) {
        super("enemy", x, y, 0, 0);
    }
    public void setTarget(Thing thing) {
        double tdx = thing.x - x;
        double tdy = thing.y - y;
        double magnitude = Math.sqrt(tdx*tdx + tdy*tdy);
        tdx /= magnitude;
        tdy /= magnitude;
        this.dx = tdx;
        this.dy = tdy;
    }
}