import java.awt.Graphics;
import java.awt.Color;

public class Bush extends Article {
    static Color color = new Color(38, 217, 73);
    static Color color2 = new Color(10, 61, 11);
    public Bush(int row, int column, int order) {
        super(row, column, order);
        name = "bush";
    }
    public void drawMe(Graphics g) {
        int x = column*width;
        int y = row*height;
        int centerX = x+width/2;
        int centerY = y+height/2;
        for(int i = 40;i > 0;i -= 15) {
            g.setColor(color);
            g.fillOval(centerX-i/2, centerY-i/2, i, i);
            g.setColor(color2);
            g.fillOval(centerX-i/2+4, centerY-i/2+4, i-8, i-8);
            
        }
    }
}