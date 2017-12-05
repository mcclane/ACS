import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Stack;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemy {
    TreeMap<Tile, Integer> inventory;
    Font font = new Font("Arial", Font.PLAIN, 25);
    BufferedImage display;
    public Enemy() {
        try {
            display = ImageIO.read(new File("characterUp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawMe(Graphics g, int x, int y) {
        g.drawImage(display, x, y, null);
    }
}
