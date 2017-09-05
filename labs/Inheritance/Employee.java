import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;

public abstract class Employee {
	private String name;
	private String photoFile;
	private String jobTitle;
	public Employee(String name, String photoFile, String jobTitle) {
		this.name = name;
		this.photoFile = photoFile;
		this.jobTitle = jobTitle;
	}
	public abstract double getSalary();
	public void drawPhoto(Graphics g, int x, int y) {
		//draw the picture
		try {
			BufferedImage image = ImageIO.read(new File("images\\"+photoFile));
			g.drawImage(image, x,y, 50, 50, null);
		} catch(IOException ex) {
			System.out.println(ex);
			System.out.println("couldn't get image");
		}
	}
	public String toString() {
		return name+"  "+jobTitle+" "+getSalary();
	}
	public String getJobTitle() {
		return jobTitle;
	}
}