import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

public class Game {
	Background background;
	Level currentLevel;
	Player player;
	double progression = 0.0;
	public Game() {
		// display the stars background
		background = new Background();
		background = new Background();
        Thread bThread = new Thread(background);
        bThread.start();
		
		// initialize some stuff
		player = new Player(1400, 400);
		currentLevel = new Level(player, 0);
		
	}
	public void start() {
		/*Thread pThread = new Thread(player);
        pThread.start();
        for(Enemy e : enemies) {
            Thread th = new Thread(e);
            th.start();
        }*/
	}
	public void stop() {
		
	}
	public void next() {
		progression++;
	}
	public void showCutScene(Graphics g, String text) {
		g.setFont(new Font("DIALOG", 0, 30));
		g.drawString(text, 650, 400);
	}
	public void render(Graphics g) {
		// start
		if(progression == 0) {
			showCutScene(g, "Press Space to Begin");
		}
		// a cutscene
		else if(progression % 0.5 == 0) {
			
		}
		// we're in a level!
		else {
			currentLevel.render(g);
		}
	}
	public void renderEnemies(Graphics g) {
		
	}
	public void renderPlayer(Graphics g) {
		
	}
}