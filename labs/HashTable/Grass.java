import java.awt.Graphics;
import java.awt.Color;

public class Grass extends Article {
    public Grass(int row, int column, int order) {
        super(row, column, order);
        name = "grass";
    }
    public void drawMe(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(column*width, row*height, height, width);
        g.setColor(Color.black);
        g.drawRect(column*width, row*height, height, width);
    }
}