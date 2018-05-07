import java.io.Serializable;
import java.awt.Graphics;
import java.awt.Color;

public abstract class Thing implements Serializable, Comparable<Thing> {
    public static final long serialVersionUID = 2L;
    String type;
    double x, y, dx, dy;
    double orientationX = 0;
    double orientationY = 0;
    int id;
    int width = 25;
    int height = 25;
    boolean armed = false;
    int lives = 1;
    public Thing(String type, double x, double y, double dx, double dy) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        id = (int)(Math.random()*10000000);
    }
    public String type() {
        return type;
    }
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.drawRect((int)(x), (int)(y), 25, 25);
    }
    public void move() {
        x += dx;
        y += dy;
    }
    public String toString() {
        return type;
    }
    @Override
    public boolean equals(Object o) {
        Thing temp = (Thing)(o);
        return temp.id == id;
    }
    @Override
    public int hashCode() {
        return type.hashCode()+id;
    }
    public void rotate(int mx, int my) {
        double dx = mx - (x+width/2);
        double dy = my - (y+height/2);
        double magnitude = Math.sqrt(dx*dx + dy*dy);
        this.orientationX = dx/magnitude;
        this.orientationY = dy/magnitude;
        /*if(dx != 0) {
            angle = Math.atan(dy/dx);
            if(dx < 0) {
                angle += Math.PI;
            }
        }*/
    }
    public boolean collision(Thing thing) {
        return collisionIfMoved(0, 0, thing);
    }
    public boolean collisionIfMoved(double dx, double dy, Thing thing) {
        int nx = (int)(x + dx);
        int ny = (int)(y  + dy);
        return thing.width + (int)thing.x > nx && nx + width > (int)(thing.x) && thing.height + (int)thing.y > ny && ny + height > (int)(thing.y);
    }
    public int lives() {
        return lives;
    }
    public void hit() {
        lives--;
    }
    public int compareTo(Thing thing) {
        if(thing.id == this.id) {
            return 0;
        }
        return Screen.drawOrder.get(this.type) - Screen.drawOrder.get(thing.type);
    }
    public boolean inView(Thing player) {
        return 1200 > Math.sqrt(Math.pow(player.x - x, 2) + Math.pow(player.y - y, 2));
    }
}