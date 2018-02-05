import java.awt.Graphics;
import java.awt.Color;

public class Grass extends Article {
    static Color color = new Color(136, 255, 183);
    public Grass(int row, int column, int order) {
        super(row, column, order);
        name = "grass";
    }
    public void drawMe(Graphics g) {
        g.setColor(color);
        g.fillRect(column*width, row*height, height, width);
        g.setColor(Color.black);
        g.drawRect(column*width, row*height, height, width);
    }
}