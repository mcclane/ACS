import java.awt.Graphics;
import java.awt.Color;


public class Text extends Thing {
    String text;
    public Text(String text, double x, double y) {
        super("text", x, y, 0, 0);
        this.text = text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawString(text, (int)x, (int)y);
    }
}