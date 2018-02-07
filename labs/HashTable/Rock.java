import java.awt.Graphics;
import java.awt.Color;

public class Rock extends Article {
    static Color color = new Color(136, 173, 143);
    static int xpoints[] = {0, -10, -20, -10, 0, 10, 20, 30, 20, 10, 0};
    static int ypoints[] = {-40, -10, -10, 0, 10, 20, 30, 20, 10, 0, -30};

    public Rock(int row, int column, int order) {
        super(row, column, order);
        name = "rock";
    }
    public int[] translateX(int x) {
        int[] xnpoints = new int[xpoints.length];
        for(int i = 0;i < xpoints.length;i++) {
            xnpoints[i] = x+xpoints[i];
        }
        return xnpoints;
    }
    public int[] translateY(int y) {
        int[] ynpoints = new int[ypoints.length];
        for(int i = 0;i < ypoints.length;i++) {
            ynpoints[i] = y+ypoints[i];
        }
        return ynpoints;
    }
    public void drawMe(Graphics g) {
        g.setColor(color);
        g.fillPolygon(translateX(column*width+width/2), translateY(row*height+height/2), xpoints.length);
    }
}