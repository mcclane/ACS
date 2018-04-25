import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
	HashMap<Double, String> cutscenes;
	Background background;
	Level currentLevel;
	Player player;
	double progress = 0.0;
	int ticker = 0;
	boolean running = false;
	public Game() {
		// create all the cutscenes
		cutscenes = new HashMap<Double, String>();
		cutscenes.put(0.5, "Welcome");
		cutscenes.put(1.5, "You made it past the first level! Now fight the boss.");
		cutscenes.put(2.5, "You won.");
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
		currentLevel.getRidOfInvisibleObjects();
	}
	public void next() {
		progress += 0.5;
		if(progress == 0) {

		}
		if(progress > 0 && progress % 1 == 0) {
			background.slow();
			currentLevel = new Level(player, (int)(progress));
			start();
		}
		else {
			// means we're in a cutscene, so speed up the background
			background.fast();
			if(progress == -0.5) {
				Sound.playSound("death.wav");
			}
			if(progress == 2.5) {
				Sound.playSound("you_won.wav");
			}
			else if(progress == 1.5) {
				Sound.playSound("levelUp.wav");
			}
		}
	}
	public void showCutScene(Graphics g, String text) {
		g.setFont(new Font("DIALOG", 0, 30));
		g.drawString(text, 650, 400);
	}
	public void render(Graphics g) {
		background.render(g);
		g.drawString("Level: "+progress, 1450, 100);
		// game over
		if(progress == -0.5) {
			showCutScene(g, "You dead. Press Space to Go Again");
		}
		// start
		else if(progress == 0) {
			showCutScene(g, "Press Space to Begin");
		}
		// a cutscene
		else if(progress % 1 != 0) {
			if(cutscenes.containsKey(progress)) {
				showCutScene(g, cutscenes.get(progress));
			}
			else {
				showCutScene(g, "This is a cutscene!");
			}
		}
		// we're in a level!
		else {
			currentLevel.render(g);
		}
	}
	public boolean levelFinished() {
		if(player.dead()) {
			return true;
		}
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
		if(player.dead()) {
			player = new Player(1400, 400);
			running = false;
			progress = -.5;
			currentLevel.stop();
			background.fast();
			currentLevel = new Level(player, 0);
		}
		if(running) {
			player.move();
			currentLevel.checkCollisions();
			currentLevel.getRidOfInvisibleObjects();
		}
	}
}