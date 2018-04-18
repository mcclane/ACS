import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

public class Game {
	Background background;
	Level currentLevel;
	Player player;
	double progress = 0.0;
	int ticker = 0;
	boolean running = false;
	public Game() {
		// display the stars background
		background = new Background();
		background = new Background();
        Thread bThread = new Thread(background);
        bThread.start();
		
		// initialize some stuff
		player = new Player(1400, 400);
		currentLevel = new Level(player, (int)(progress));
	}
	public void start() {
		currentLevel.start();
		running = true;
	}
	public void stop() {
		running = false;
		currentLevel.stop();
	}
	public void next() {
		progress += 0.5;
		if(progress % 1 == 0) {
			currentLevel = new Level(player, (int)(progress));
			start();
		}
	}
	public void showCutScene(Graphics g, String text) {
		g.setFont(new Font("DIALOG", 0, 30));
		g.drawString(text, 650, 400);
	}
	public void render(Graphics g) {
		background.render(g);
		g.drawString("Level: "+progress, 1450, 100);
		// start
		if(progress == 0) {
			showCutScene(g, "Press Space to Begin");
		}
		// a cutscene
		else if(progress % 1 != 0) {
			showCutScene(g, "This is a cutscene!");
		}
		// we're in a level!
		else {
			currentLevel.render(g);
		}
	}
	public boolean levelFinished() {
		return currentLevel.isFinished();
	}
	public void rotatePlayer(int x, int y) {
		if(running && Math.sqrt(Math.pow(player.x+player.width/2 - x, 2) + Math.pow(player.y+player.height/2 - x, 2)) > player.height) {
            player.rotate(x, y);
        }
	}
	public void startShooting() {
		if(running) {
			player.shoot();
			player.shooting = true;
		}
	}
	public void stopShooting() {
		player.shooting = false;
	}
	public synchronized void gameLoop() {
		if(running) {
			player.move();
			currentLevel.checkCollisions();
			currentLevel.getRidOfInvisibleObjects();
		}
	}
}