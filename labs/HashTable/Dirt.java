import java.awt.Graphics;
import java.awt.Color;

public class Dirt extends Article {
    Color brown = new Color(165, 42, 42);
    public Dirt(int row, int column, int order) {
        super(row, column, order);
        name = "dirt";
    }
    public void drawMe(Graphics g) {
        g.setColor(brown);
        g.fillRect(column*width, row*height, height, width);
        g.setColor(Color.black);
        g.drawRect(column*width, row*height, height, width);
    }
}