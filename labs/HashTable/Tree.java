import java.awt.Graphics;
import java.awt.Color;

public class Tree extends Article {
    static Color color = new Color(38, 217, 73);
    static Color color2 = new Color(10, 61, 11);
    public Tree(int row, int column, int order) {
        super(row, column, order);
        name = "tree";
    }
    public void drawMe(Graphics g) {
        int x = column*width+20;
        int y = row*height+20;
        int centerX = x+width/2;
        int centerY = y+height/2;
        for(int i = 40;i > 0;i -= 10) {
            g.setColor(color);
            g.fillOval(centerX-i, centerY-i, i, i);
            g.setColor(color2);
            g.fillOval(centerX-i+5, centerY-i+5, i-5, i-5);
            
        }
        /*
        for(int i = 40;i > 0;i -= 15) {
            g.setColor(color);
            g.fillOval(centerX-i/2, centerY-i/2, i, i);
            g.setColor(color2);
            g.fillOval(centerX-i/2+4, centerY-i/2+4, i-8, i-8);
            
        }
        */
        //g.setColor(color);
        //g.fillOval(column*width+width/8, row*height+height/8, (int)(height/1.3), (int)(width/1.3));
        
    }
}