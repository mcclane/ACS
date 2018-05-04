import java.awt.Graphics;

public class StartScreen extends Thing {
    public StartScreen() {
        super("start_screen", 0, 0, 0, 0);
    }
    public void render(Graphics g) {
        ImageReader.drawImage(g, "images/start_screen.png", 0, 0, 1200, 800);
    }
}