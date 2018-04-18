import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.util.stream.Collectors;

public class Level {
	boolean running = false;
	Player player;
	int level;
	ArrayList<Enemy> enemies;
	public Level(Player player, int level) {
		this.player = player;
		this.level = level;
		enemies = new ArrayList<Enemy>();
		if(level == 1) {
			enemies.add(new Enemy(100, 200, player));
	        enemies.add(new Enemy(200, 200, player));
	        enemies.add(new Enemy(300, 200, player));
	        enemies.add(new Enemy(400, 200, player));
	        enemies.add(new Enemy(500, 200, player));
	        enemies.add(new Enemy(100, 300, player));
	        enemies.add(new Enemy(200, 300, player));
	        enemies.add(new Enemy(300, 300, player));
	        enemies.add(new Enemy(400, 300, player));
	        enemies.add(new Enemy(500, 300, player));
	        enemies.add(new Enemy(100, 400, player));
	        enemies.add(new Enemy(200, 400, player));
	        enemies.add(new Enemy(300, 400, player));
	        enemies.add(new Enemy(400, 400, player));
	        enemies.add(new Enemy(500, 400, player));
	        enemies.add(new Enemy(100, 500, player));
	        enemies.add(new Enemy(200, 500, player));
	        enemies.add(new Enemy(300, 500, player));
	        enemies.add(new Enemy(400, 500, player));
	        enemies.add(new Enemy(500, 500, player));
	    }
	}
	public boolean isFinished() {
		return enemies.size() == 0;
	}
	public void start() {
		for(Enemy e : enemies) {
			Thread temp = new Thread(e);
			temp.start();
		}
	}
	public void stop() {
		for(Enemy e : enemies) {
			e.visible = false;
		}
	}
	public void render(Graphics g) {
		player.render(g);
		for(Enemy e : enemies) {
			if(e.visible) {
            	e.render(g);
            }
        }
        g.setColor(Color.white);
        g.drawString("Enemies Left: "+enemies.size(), 1450, 75);
	}
	public void getRidOfInvisibleObjects() {
		synchronized(player.missiles) {
			ArrayList<Missile> missilesToBeRemoved = new ArrayList<Missile>();
			for(Missile missile : player.missiles) {
				if(!missile.visible) {
					missilesToBeRemoved.add(missile);
				}
			}
			for(Missile missile : missilesToBeRemoved) {
				player.missiles.remove(missile);
			}
		}
		synchronized(enemies) {
			ArrayList<Enemy> enemiesToBeRemoved = new ArrayList<Enemy>();
			for(Enemy enemy : enemies) {
				if(!enemy.visible) {
					enemiesToBeRemoved.add(enemy);
				}
			}
			for(Enemy enemy : enemiesToBeRemoved) {
				enemies.remove(enemy);
			}
		}
	}
	public synchronized void checkCollisions() {
		//System.out.println("Enemy Size: "+enemies.size());
		//System.out.println("Missiles size: "+player.missiles.size());
		for(int i = 0;i < player.missiles.size();i++) {
            for(int j = 0;j < enemies.size();j++) {
                if(enemies.get(j).visible && player.missiles.get(i) != null && enemies.get(j).collision((int)player.missiles.get(i).x, (int)player.missiles.get(i).y, Missile.width, Missile.height)) {
                    enemies.get(j).visible = false;
                    player.missiles.get(i).visible = false;
                }
            }
        }
        for(int i = 0;i < enemies.size();i++) {
            if(enemies.get(i).visible && enemies.get(i).collision((int)player.x, (int)player.y, player.width, player.height)) {
                player.lives--;
                enemies.get(i).visible = false;
            }
        }
	}
}