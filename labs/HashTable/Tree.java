import java.awt.Graphics;
import java.awt.Color;

public class Tree extends Article {
    public Tree(int row, int column, int order) {
        super(row, column, order);
        name = "tree";
    }
    public void drawMe(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(column*width+width/2, row*height+height/2, height/2, width/2);
        g.setColor(Color.black);
        g.drawRect(column*width, row*height, height, width);
    }
}