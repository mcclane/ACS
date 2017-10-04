import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Item implements Comparable {
    String name;
    int price;
    JButton addButton;
    String filename;
    private BufferedImage img;
    
    public Item(String name, int price, String filename) {
        this.name = name;
        this.price = price;
        this.filename = filename;
        addButton = new JButton("Add");
        try {
            img = ImageIO.read(new File("images\\"+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawMe(Graphics g, int quantity, int x, int y) {
        g.drawString(name+" $"+price+" Quantity: "+quantity, x, y);
        g.drawImage(img, x+200, y, 50,50, null);
        addButton.setBounds(x, y, 200, 30);
        addButton.setVisible(true);
    }
   public boolean equals(Object o) {
        Item i = (Item)(o);
        if(i.name.equals(name) && i.price == price) {
            return true;
        }
        return false;
    }
    public int compareTo(Object o) {
        Item i = (Item)(o);
        return name.compareTo(i.name)*100 + (price - i.price);
    }
    public int hashCode() {
        return 31*(price + name.hashCode());
    }
}