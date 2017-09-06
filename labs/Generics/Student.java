import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import javax.swing.JButton;

public class Student {

    private String name;
    private String imageFile;

    public Student(String name, String imageFile) {
        this.name = name;
        this.imageFile = imageFile;
    }
    
    public void drawStudent(Graphics g, int x, int y) {
		//draw the picture
		try {
			BufferedImage image = ImageIO.read(new File("images/"+imageFile));
			g.drawImage(image, x,y, 100, 100, null);
            Font font = new Font("Arial", Font.PLAIN, 16);
            g.setFont(font);
            g.setColor(Color.red);
			g.drawString(toString(), x+110, y+10);


            
		} catch(IOException ex) {
			System.out.println(ex);
			System.out.println("couldn't get image");
		}
	}

    public String toString() {
        return name;
    }
}
