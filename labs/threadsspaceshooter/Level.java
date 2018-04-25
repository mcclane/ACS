import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ConcurrentModificationException;

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
			for(int i = 0;i < 5;i++) {
				for(int j = 0;j < 10;j++) {
					enemies.add(new Enemy(i*25, j*25+200, 1, player));
				}
			}
	    }
	    else if(level == 2) {
	    	enemies.add(new Enemy(50, 50, 100, player));
	    	for(int i = 0;i < 25;i++) {
	    		enemies.add(new Enemy(100, i*25, 1, player));
	    	}
	    }
	    else if(level > 0) {
	    	for(int i = 0;i < level;i++) {
	    		for(int j = 0;j < level; j++) {
	    			enemies.add(new Enemy(i*25, j*25, 1, player));
	    		}
	    	}
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
			e.lives = 0;
			e.visible = false;
		}
	}
	public synchronized void render(Graphics g) {
		player.render(g);
		for(Enemy e : enemies) {
			if(e.visible) {
            	e.render(g);
            }
        }
        g.setColor(Color.white);
        g.drawString("Enemies Left: "+enemies.size(), 1450, 75);
	}
	public synchronized void getRidOfInvisibleObjects() {
		try {
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
					if(enemy.dead()) {
						enemiesToBeRemoved.add(enemy);
					}
				}
				for(Enemy enemy : enemiesToBeRemoved) {
					enemies.remove(enemy);
				}
			}
		}
		catch(ConcurrentModificationException e) {}
	}
	public synchronized void checkCollisions() {
		//System.out.println("Enemy Size: "+enemies.size());
		//System.out.println("Missiles size: "+player.missiles.size());
		for(int i = 0;i < player.missiles.size();i++) {
            for(int j = 0;j < enemies.size();j++) {
                if(enemies.get(j).visible && player.missiles.get(i) != null && enemies.get(j).collision((int)player.missiles.get(i).x, (int)player.missiles.get(i).y, Missile.width, Missile.height)) {
                	enemies.get(j).hit();
                    //enemies.get(j).visible = false;
                    player.missiles.get(i).visible = false;
                    Sound.playSound("you_killed.wav");
                }
            }
        }
        for(int i = 0;i < enemies.size();i++) {
            if(enemies.get(i).visible && enemies.get(i).collision((int)player.x, (int)player.y, player.width, player.height) && !player.invincible) {
                player.lives--;
                player.makeInvincibleForALittle();
                enemies.get(i).hit();
                Sound.playSound("you_got_hit.wav");
                //enemies.get(i).visible = false;
            }
        }
	}
}
