import java.awt.Graphics;
import java.awt.Color;

public class Background implements Runnable {
    double[][] stars;
    public Background() {
        stars = new double[200][3];
        for(int i = 0;i < stars.length;i++) {
            stars[i][0] = Math.random() * 1600;
            stars[i][1] = Math.random() * 800;
            stars[i][2] = Math.random() * 100;
        }
    }
    public void render(Graphics g) {
        g.setColor(Color.white);
        for(int i = 0;i < stars.length;i++) {
            g.drawRect((int)stars[i][0], (int)stars[i][1], (int)stars[i][2] / 25, (int)stars[i][2] / 50);
        }
    }
    public void run() {
        while(true) {
            for(int i = 0;i < stars.length;i++) {
                stars[i][0] -= stars[i][2] / 25;
                if(stars[i][0] <= 0) {
                    stars[i][0] = 1600;
                    stars[i][1] = Math.random()*800;
                }
            }
            try {
                Thread.sleep(50);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}