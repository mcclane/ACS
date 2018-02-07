import java.awt.Graphics;
import java.awt.Color;

public abstract class Article implements Comparable<Article> {
    int row, column, order;
    String name = "";
    int height = 50;
    int width = 50;
    public Article(int row, int column, int order)  {
        this.order = order;
        this.row = row;
        this.column = column;
    }
    public void drawMe(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(row*height, column*width, height, width);
        g.setColor(Color.black);
        g.drawRect(column*width, row*height, height, width);
    }
    public int hashCode() {
        return row*20+column;
    }
    public int compareTo(Article a) {
        return a.order - order;
    }
    public String name() {
        return name;
    }
}