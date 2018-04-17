import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class LoadingScreen extends JPanel {
    static boolean loading = true;
    public LoadingScreen() {
        this.setLayout(null);
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1600,800);
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        //g.setFont(new Font("DIALOG", 0, 30));
        g.fillRect(0, 0, 1600, 800);
        g.setColor(Color.white);
        g.drawRect(400, 200, 650, 400);
        //g.drawString("This is a loading screen", 700, 480);        
    }
}