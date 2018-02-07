import java.awt.Graphics;
import java.awt.Color;

public class Water extends Article {
    static Color color = new Color(200, 255, 222);
    public Water(int row, int column, int order) {
        super(row, column, order);
        name = "water";
    }
    public void drawMe(Graphics g) {
        g.setColor(color);
        g.fillRect(column*width, row*height, height, width);
        g.setColor(Color.black);
        g.drawRect(column*width, row*height, height, width);
        g.setColor(Color.blue);
        g.drawRect(column*width+width/4, row*height+height/4, height, width);
        g.drawRect(column*width-width/4, row*height-height/4, height, width);
        g.drawRect(column*width+width/2, row*height+height/2, height, width);
        g.drawRect(column*width-width/2, row*height-height/2, height, width);
    }
}