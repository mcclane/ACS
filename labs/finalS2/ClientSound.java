import java.awt.Graphics;
import java.awt.Color;

public class ClientSound extends Thing {
    String filename;
    public ClientSound(String filename) {
        super("sound", 10, 10, 0, 0);
        this.filename = filename;
        this.lives = 3;
        this.height = 1;
        this.width = 1;
    }
    public void render(Graphics g) {
        System.out.println("Playing: "+filename);
        Sound.playSound(filename);
    }
    public boolean collision(Thing thing) {
        return false;
    }
}