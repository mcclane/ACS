import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.MalformedURLException;

public class MyImage {

	private String description, date;
    private URL url;
    
    public static HashMap<URL, Image> images = new HashMap<URL, Image>();

	public MyImage(String url, String description, String date) {
        try {
            this.url = new URL(url);
            if(!images.containsKey(this.url)) {
                try {
                    images.put(this.url, ImageIO.read(this.url));
                } catch(IOException e) {
                    System.out.println(e);
                }
            }
        } catch(MalformedURLException e) {
            System.out.println(e);
        }
		this.description = description;
		this.date = date;
	}
    public void drawMe(Graphics g, int x, int y) {
        g.drawImage(images.get(this.url), x, y, 400, 400, null);
    }
}
