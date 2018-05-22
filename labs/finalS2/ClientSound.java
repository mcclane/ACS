import java.awt.Graphics;
import java.awt.Color;

public class ClientSound extends Thing {
    String filename;
    public ClientSound(String filename) {
        super("sound", 10, 10, 0, 0);
        System.out.println("Initializing sound");
        this.filename = filename;
        this.lives = 1;
        this.height = 1;
        this.width = 1;
    }
    public void render(Graphics g) {
        Sound.playSound(filename);
    }
    public boolean collision(Thing thing) {
        return false;
    }
    public boolean collisionIfMoved(double dx, double dy, Thing thing) {
        return false;
    }
}
