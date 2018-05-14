import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ConcurrentModificationException;


public class Game implements Runnable {
    HashMap<Integer, Thing> state;
    HashSet<Thing> players;
    HashSet<Thing> obstacles;
    HashSet<Thing> projectiles;
    HashSet<Thing> weapons;
    ArrayList<ServerThread> threads;
    DeathCircle deathCircle;
    static int mapsize = 4000;
    boolean started = false;
    int level = 1;
    int killedTrees = 0;
    int killedBoxes = 0;
    Text levelText;
    int counter = 0;
    public Game() {
        state = new HashMap<Integer, Thing>();
        players = new HashSet<Thing>();
        obstacles = new HashSet<Thing>();
        projectiles = new HashSet<Thing>();
        weapons = new HashSet<Thing>();
        threads = new ArrayList<ServerThread>();
        deathCircle = new DeathCircle();
        levelText = new Text("Level 1: Find a gun and kill 1 tree", -1100, 25);
        
        state.put(levelText.hashCode(), levelText);
        state.put(deathCircle.hashCode(), deathCircle);
        //add some obstacles to the game
        for(int i = 0;i < 50;i++) {
            int treeSize = (int)(Math.random()*150);
            add(new Tree(Math.random()*mapsize, Math.random()*mapsize, treeSize, treeSize));
        }
        int obstacleSize = 100;
        for(int i = 0;i < 15;i++) {
            add(new Obstacle(Math.random()*mapsize, Math.random()*mapsize, obstacleSize, obstacleSize, 3));
        }
        for(int i = 0;i < 15;i++) {
            add(new Weapon(Math.random()*mapsize, Math.random()*mapsize));
        }
        for(int i = 0;i < 25;i++) {
            add(new Barrel(Math.random()*mapsize, Math.random()*mapsize, 70, 2));
        }
        // add some 
    }
    public void add(ServerThread sv) {
        synchronized(threads) {
            threads.add(sv);
        }
    }
    public void remove(ServerThread sv) {
        synchronized(threads) {
            threads.remove(sv);
        }
    }
    public void update(Event event) {
        //System.out.println("Received: "+event);
        synchronized(state) {
            if(event.operation.equals("player_connect")) {
                add(new Player(event.concerns, Math.random()*mapsize, Math.random()*mapsize));
                started = true;
            }
            else if(event.operation.equals("cheat")) {
                level++;
                levelText.setText("Level: "+level);
            }
            else if(event.operation.equals("player_move") && state.containsKey(event.concerns) && started) {
                double dx = 0;
                double dy = 0;
                switch(event.direction) {
                    case "up":
                        dy = -6;
                        break;
                    case "down":
                        dy = 6;
                        break;
                    case "left":
                        dx = -6;
                        break;
                    case "right":
                        dx = 6;
                        break;
                }
                synchronized(obstacles) {
                    boolean move = true;
                    for(Thing thing : obstacles) {
                        if(state.get(event.concerns).collisionIfMoved(dx, dy, thing)) {
                            move = false;
                            break;
                        }
                    }
                    if(move && inBoundsIfMoved(state.get(event.concerns), dx, dy)) {
                        state.get(event.concerns).x += dx;
                        state.get(event.concerns).y += dy;
                    }
                }   
            }
            else if(event.operation.equals("player_shoot") && state.containsKey(event.concerns) && started && state.get(event.concerns).armed) {
                // TODO: Move this to a function outside of update
                Player player = (Player)(state.get(event.concerns));
                int offset = player.height/2;
                if(Math.random() > 0.5) {
                    event.dx += Math.random()/10;
                    event.dy += Math.random()/10;
                }
                else {
                    event.dx -= Math.random()/10;
                    event.dy -= Math.random()/10;
                }
                add(new Projectile(player.x+offset+(event.dx*offset*2), player.y+offset+(event.dy*offset*2), event.dx, event.dy, 100));
                // set the orientation of the shooting player
                player.orientationX = event.dx;
                player.orientationY = event.dy;
            }
        }
    }
    public synchronized void add(Thing thing) {
        synchronized(state) {
            state.put(thing.hashCode(), thing);
        }
        if(thing.type.equals("projectile")) {
            synchronized(projectiles) {
                projectiles.add(thing);
            }
        }
        else if(thing.type.equals("obstacle") || thing.type.equals("tree") || thing.type.equals("barrel")) {
            synchronized(obstacles) {
                obstacles.add(thing);
            }
        }
        else if(thing.type.equals("weapon")) {
            synchronized(weapons) {
                weapons.add(thing);
            }
        }
        else if(thing.type.equals("player")) {
            synchronized(players) {
                players.add(thing);
            }
        }
    }
    public synchronized void remove(Thing thing) {
        if(thing == null) {
            return;
        }
        synchronized(state) {
            state.remove(thing.hashCode());
        }
        synchronized(projectiles) {
            projectiles.remove(thing);
        }
        synchronized(obstacles) {
            obstacles.remove(thing);
        }
        synchronized(weapons) {
            weapons.remove(thing);
        }
        synchronized(players) {
            players.remove(thing);
        }
    }
    public void broadcastState() {
        synchronized(state) {
            try {
                //System.out.println(state);
                synchronized(threads) {
                    for(ServerThread sv : threads) {
                        sv.send(state);
                    }
                }
            } catch(ConcurrentModificationException e) {
                System.out.println("CME trying to broadcast");
            }
        }
    }
    public void run() {
        ArrayList<Integer> toBeRemoved;
        while(true) {
            synchronized(state) {
                // broadcast the state
                broadcastState();

                toBeRemoved = new ArrayList<Integer>();
                // check collisions between missiles and everything else
                synchronized(projectiles) {
                    for(Thing projectile : projectiles) {
                        for(int key : state.keySet()) {
                            if(projectile.lives() > 0 && !state.get(key).type.equals("projectile") && projectile.collision(state.get(key))) {
                                projectile.hit();
                                state.get(key).hit();
                                if(state.get(key).type.equals("player")) {
                                    System.out.println("player hit");
                                }
                                //System.out.println("collision! with "+state.get(key).type);
                                break;
                            }
                        }
                    }
                }
                // check collisions between players and weapons
                synchronized(weapons) {
                    synchronized(players) {
                        for(Thing weapon : weapons) {
                            for(Thing player : players) {
                                weapon.collision(player);
                            }
                        }
                    }
                }
                // check collisions between the deathCircle and players every few frames
                if(counter % 300 == 0) {
                    synchronized(players) {
                        for(Thing player : players) {
                            if(!deathCircle.contains(player)) {
                                player.hit();
                                System.out.println("a player is outside the deathcircle!");
                            }
                        }
                    }
                }
                // get rid of things 
                for(int key : state.keySet()) {
                    // that have no lives left
                    if(state.get(key).lives <= 0) {
                        toBeRemoved.add(key);
                    }
                    // that have moved out of bounds
                    if(!inBounds(state.get(key)) && !state.get(key).type().equals("player")) {
                        toBeRemoved.add(key);
                    }
                }
                // get rid of everything previously determined to be removed
                for(int key : toBeRemoved) {
                    if(state.get(key) == null) {
                        continue;
                    }
                    if(state.get(key).type.equals("tree")) {
                        killedTrees++;
                    }
                    else if(state.get(key).type.equals("obstacle")) {
                        killedBoxes++;
                    }
                    else if(state.get(key).type.equals("barrel")) {
                        for(int i = -3;i < 3;i++) {
                            for(int j = -3;j < 3;j++) {
                                double magnitude = Math.sqrt(i*i + j*j);
                                add(new Projectile(state.get(key).x, state.get(key).y, i/magnitude, j/magnitude, 25));
                            }
                        }
                    }
                    remove(state.get(key));
                }
                if(level == 1 && killedTrees > 0) {
                    level++;
                    killedTrees = 0;
                    killedBoxes = 0;
                    levelText.setText("Level 2: Kill 4 Trees and 4 Boxes");
                }
                else if(level == 2 && killedBoxes > 3 && killedTrees > 3) {
                    level++;
                    levelText.setText("Level 3: Kill the other player!");
                }
                // move everything
                for(int key : state.keySet()) {
                    state.get(key).move();
                }
            }
            // sleep
            try {
                Thread.sleep(30);
            } catch(InterruptedException e) {
                System.out.println("InterruptedException");
            }
            counter++;
        }
    }
    public boolean inBounds(Thing thing) {
        if(thing.type.equals("text"))
            return true;
        return inBoundsIfMoved(thing, 0, 0);
    }
    public boolean inBoundsIfMoved(Thing thing, double dx, double dy) {
        return thing.x+dx < mapsize && thing.x+dx > 0 && thing.y+dy < mapsize && thing.y+dy > 0 && !thing.type.equals("text");
    }
}