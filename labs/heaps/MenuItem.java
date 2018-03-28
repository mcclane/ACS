import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuItem {
    public String name;
    public double price;
    public String filename;
    public MenuItem(String name, double price, String filename) {
        this.name = name;
        this.price = price;
        this.filename = filename;
        
        try {
            Screen.images.put(name, ImageIO.read(new File(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public MenuItem copy() {
        return new MenuItem(this.name, this.price, this.filename);
    }
    public String toString() {
        return name+" - $"+price;
    }
    public void drawMe(Graphics g, int x, int y) {
        g.setColor(Color.white);
        drawMyImage(g, x, y, 100, 80);
        g.drawString(toString(), x, y-5);
    }
    public void drawMyImage(Graphics g, int x, int y, int w, int h) {
        g.drawImage(Screen.images.get(this.name), x, y, w, h, null);
    }
}