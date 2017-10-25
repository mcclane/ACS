import java.awt.Graphics;
import java.awt.Color;

public class Square {
    private Color color;
    public Square() {
        color = new Color(255, 255, 255);
    }
    public void drawMe(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
        g.setColor(Color.black);
        g.drawRect(x, y, size, size);
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Square copy() {
        Square tempSquare = new Square();
        Color tempSquareColor = new Color(color.getRed(), color.getGreen(), color.getBlue());
        tempSquare.setColor(tempSquareColor);
        return tempSquare;
    }
}