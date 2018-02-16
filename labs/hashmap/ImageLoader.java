import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.MalformedURLException;

public class ImageLoader {
    public static HashMap<URL, Image> images = new HashMap<URL, Image>();
    public static void add(URL url) {
        if(!images.containsKey(url)) {
            try {
                images.put(url, ImageIO.read(url));
            } catch(IOException e) {
                System.out.println(e);
            }
        }
    }
    public static void drawImage(URL url, Graphics g, int x, int y) {
        if(!images.containsKey(url)) {
            add(url);
        }
        g.drawImage(images.get(url), x, y, 400, 400, null);
    }
}