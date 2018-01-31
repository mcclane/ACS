import java.awt.Graphics;
import java.awt.Color;

public class Water extends Article {
    public Water(int row, int column, int order) {
        super(row, column, order);
        name = "water";
    }
    public void drawMe(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(column*width, row*height, height, width);
        g.setColor(Color.black);
        g.drawRect(column*width, row*height, height, width);
    }
}