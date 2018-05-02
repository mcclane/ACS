import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class ImageReader {
    public static HashMap<String, BufferedImage> images;
    public static void drawImage(Graphics g, String filename, int x, int y, int width, int height) {
        if(images == null) {
            images = new HashMap<String, BufferedImage>();
        }
        if(!images.containsKey(filename)) {
            try {
                images.put(filename, ImageIO.read(new File(filename)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(images.get(filename), x, y, width, height, null);
    }
}