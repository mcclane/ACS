import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Serializable;

public class MyImage implements Serializable {
  
    private static final long serialVersionUID = 6L;


	private String description, date;
    private URL url;
    
	public MyImage(String url, String description, String date) {
        try {
            this.url = new URL(url);
            ImageLoader.add(this.url);
        } catch(MalformedURLException e) {
            System.out.println(e);
        }
		this.description = description;
		this.date = date;
	}
    public void drawMe(Graphics g, int x, int y) {
        ImageLoader.drawImage(url, g, x, y);
        g.drawString("Description: " + description, x, y+415);
        g.drawString("Date:" + date, x, y+435);
    }
}
