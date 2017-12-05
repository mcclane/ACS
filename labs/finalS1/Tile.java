import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class Tile implements Comparable<Tile>{
    int x, y;
    int width = 50;
    int height = 50;
    String type;
    String itemType;
    static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
    public Tile(String type, String filename) {
        this.type = type;
        try {
            images.put(type, ImageIO.read(new File(filename)));
            //strawberryImg = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Tile(String type, String itemType, String filename) {
        this.type = type;
        this.itemType = itemType;
         try {
            images.put(itemType, ImageIO.read(new File(filename)));
            //strawberryImg = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawMe(Graphics g, int x, int y) {
        if(itemType != null)
            g.drawImage(images.get(itemType), x, y, height, width, null);
        else
            g.drawImage(images.get(type), x, y, height, width, null);

    }
    public int compareTo(Tile t) {
        if(itemType != null && t.itemType != null)
            return itemType.compareTo(t.itemType);
        else
            return type.compareTo(t.type);
    }
}