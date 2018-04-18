import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Level {
	Player player;
	int level;
	ArrayList<Enemy> enemies;
	public Level(Player player, int level) {
		this.player = player;
		this.level = level;
		enemies = new ArrayList<Enemy>();
	}
	public void render(Graphics g) {
		
	}
}