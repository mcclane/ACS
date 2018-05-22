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
        add(deathCircle);
        add(levelText);
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
                double[] spawnPoint = chooseSpawnLocation();
                if(spawnPoint != null) {
                    add(new Player(event.concerns, spawnPoint[0], spawnPoint[1]));
                }
                else {
                    add(new Player(event.concerns, Math.random()*mapsize, Math.random()*mapsize));
                }
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
                add(new ClientSound("sound/mac10_01.wav"));
                // set the orientation of the shooting player
                player.orientationX = event.dx;
                player.orientationY = event.dy;
            }
            else if(event.operation.equals("emote") && state.containsKey(event.concerns)) {
                add(new Emote("happy", state.get(event.concerns).x, state.get(event.concerns).y - 30));
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
        else if(thing.type.equals("obstacle") || thing.type.equals("tree") || thing.type.equals("barrel")  || thing.type.equals("sound") || thing.type.equals("emote")) {
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
                                    add(new ClientSound("sound/player_bullet_hit_01.wav"));
                                }
                                else if(state.get(key).type.equals("tree")) {
                                    add(new ClientSound("sound/wood_bullet_hit_03.wav"));
                                    if(state.get(key).lives <= 0) {
                                        add(new ClientSound("sound/tree_break_01.wav"));
                                    }
                                }
                                else if(state.get(key).type.equals("barrel")) {
                                    add(new ClientSound("sound/metal_bullet_hit_03.wav"));
                                    if(state.get(key).lives <= 0) {
                                        add(new ClientSound("sound/explosion_02.wav"));
                                    }
                                }
                                else if(state.get(key).type.equals("obstacle")) {
                                    add(new ClientSound("sound/wood_bullet_hit_01.wav"));
                                    if(state.get(key).lives <= 0) {
                                        add(new ClientSound("sound/crate_break_01.wav"));
                                    }
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
                                if(weapon.collision(player)) {
                                    add(new ClientSound("sound/gun_pickup_01.wav"));
                                }
                                
                            }
                        }
                    }
                }
                // check collisions between the deathCircle and players every few frames
                if(counter % 10 == 0) {
                    synchronized(players) {
                        for(Thing player : players) {
                            if(!deathCircle.contains(player)) {
                                player.hit();
                                add(new ClientSound("sound/player_bullet_hit_01.wav"));
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
                    if(!inBounds(state.get(key)) && !state.get(key).type().equals("player") && !state.get(key).type.equals("death_circle") && !state.get(key).type.equals("sound")) {
                        toBeRemoved.add(key);
                    }
                    if(state.get(key).type.equals("sound") || state.get(key).type.equals("emote")) {
                        state.get(key).hit();
                    }
                }
                // get rid of everything previously determined to be removed
                for(int key : toBeRemoved) {
                    if(state.get(key) == null) {
                        continue;
                    }
                    if(state.get(key).type.equals("death_circle")) {
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
                if(started) {
                    deathCircle.contract();
                }
            }
            // check if we should reset the circle size and the game
            if(deathCircle.width <= 0 && players.size() == 0) {
                reset();
                deathCircle.reset();
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
        return thing.x+dx < mapsize && thing.x+dx > 0 && thing.y+dy < mapsize && thing.y+dy > 0 && !thing.type.equals("text") && !thing.type.equals("sound");
    }
    public void init() {

    }
    public void reset() {
        synchronized(state) {
            boolean started = false;
            int level = 1;
            int killedTrees = 0;
            int killedBoxes = 0;
            state = new HashMap<Integer, Thing>();
            players = new HashSet<Thing>();
            obstacles = new HashSet<Thing>();
            projectiles = new HashSet<Thing>();
            weapons = new HashSet<Thing>();
            deathCircle = new DeathCircle();
            levelText = new Text("Level 1: Find a gun and kill 1 tree", -1100, 25);
            
            add(deathCircle);
            add(levelText);
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
        }
    }
    public double[] chooseSpawnLocation() {
        for(int i = 0;i < 1000;i++) {
            double x = Math.random()*mapsize;
            double y = Math.random()*mapsize;
            Thing possibleThing = new Player(123, x, y);
            boolean valid = false;
            if(deathCircle.contains(x, y)) {
                valid = true;
                synchronized(state) {
                    for(Thing thing : state.values()) {
                        if(thing.collision(possibleThing)) {
                            valid = false;
                            break;
                        }
                    }
                }
            }
            if(valid) {
                double[] spawnPoint = new double[2];
                spawnPoint[0] = x;
                spawnPoint[1] = y;
                return spawnPoint;
            }
        }
        System.out.println("Can't find a good one!");
        return null;
    }
}
