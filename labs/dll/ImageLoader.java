import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.Graphics2D;
import java.awt.Image;

public class ImageLoader {
	public static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	public static final String[] imagenames = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	public static final String[] names = {"ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};
	public static final String[] imagesuits = {"C", "S", "H", "D"};
	public static final String[] suits = {"clubs", "spades", "hearts", "diamonds"};
	public static void load() {
		try {
			for (int i = 0; i<imagenames.length; i++) {
				for (int j = 0; j<imagesuits.length; j++) {
					images.put(names[i] + " of " + suits[j], resize(ImageIO.read(new File("PNG/" + imagenames[i] + imagesuits[j] + ".png")), 1056/5, 691/5));
				}
			}
			images.put("back", resize(ImageIO.read(new File("PNG/blue_back.png")), 1056/5, 691/5));
		} catch (IOException e) {
			
		}
	}
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
