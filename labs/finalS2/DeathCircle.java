import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class DeathCircle extends Thing {
    public DeathCircle() {
        super("death_circle", 0, 0, 0, 0);
        x = (int)(Math.random()*Game.mapsize);
        y = (int)(Math.random()*Game.mapsize);
        //x = Game.mapsize/2;
        //y = Game.mapsize/2;
        width = (int)(Game.mapsize*4);
        height = width;
    }
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Rectangle2D outer = new Rectangle2D.Double(-800, -800, Game.mapsize+1600, Game.mapsize+1600);
        Ellipse2D inner = new Ellipse2D.Double(x-width/2, y-width/2, width, width);
        Area area = new Area(outer);
        area.subtract(new Area(inner));
        g2.setColor(Colors.DEATH);
        g2.fill(area);
    }
    public void contract() {
        if(width > Game.mapsize) {
            width -= width/500;
        }
        else {
            width--;
        }
        height = width;
    }
    public void reset() {
        x = (int)(Math.random()*Game.mapsize);
        y = (int)(Math.random()*Game.mapsize);
        //x = Game.mapsize/2;
        //y = Game.mapsize/2;
        width = (int)(Game.mapsize*4);
        height = width;
    }
    public boolean contains(Thing thing) {
        double distance = Math.sqrt(Math.pow(thing.x - x, 2) + Math.pow(thing.y - y, 2));
        return distance < width/2;
    }
    public boolean contains(double x, double y) {
        double distance = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        return distance < width/2;
    }
    public boolean collision(Thing thing) {
        return false;
    }
}