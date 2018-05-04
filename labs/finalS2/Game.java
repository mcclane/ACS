import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ConcurrentModificationException;


public class Game implements Runnable {
    HashMap<Integer, Thing> state;
    HashSet<Thing> players;
    HashSet<Thing> obstacles;
    HashSet<Thing> projectiles;
    ArrayList<ServerThread> threads;
    int mapsize = 4000;
    boolean started = false;
    public Game() {
        state = new HashMap<Integer, Thing>();
        players = new HashSet<Thing>();
        obstacles = new HashSet<Thing>();
        projectiles = new HashSet<Thing>();
        threads = new ArrayList<ServerThread>();
        
        //add some obstacles to the game
        for(int i = 0;i < 50;i++) {
            int treeSize = (int)(Math.random()*150);
            Tree t = new Tree(Math.random()*mapsize, Math.random()*mapsize, treeSize, treeSize);
            state.put(t.hashCode(), t);
            obstacles.add(t);
        }
        int obstacleSize = 100;
        for(int i = 0;i < 15;i++) {
            Obstacle o = new Obstacle(Math.random()*mapsize, Math.random()*mapsize, obstacleSize, obstacleSize, 3);
            state.put(o.hashCode(), o);
            obstacles.add(o);
        }
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
                state.put(event.concerns, new Player(event.concerns, Math.random()*mapsize, Math.random()*mapsize));
                started = true;
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
            else if(event.operation.equals("player_shoot") && state.containsKey(event.concerns) && started) {
                // TODO: Move this to a function outside of update
                Player player = (Player)(state.get(event.concerns));
                int offset = player.height/2;
                /*double x = state.get(event.concerns).x;
                double y = state.get(event.concerns).y;
                double dx = event.mouseX - x;
                double dy = event.mouseY - y;
                double magnitude = Math.sqrt(dx*dx + dy*dy);
                dx /= magnitude;
                dy /= magnitude;
                */
                if(Math.random() > 0.5) {
                    event.dx += Math.random()/10;
                    event.dy += Math.random()/10;
                }
                else {
                    event.dx -= Math.random()/10;
                    event.dy -= Math.random()/10;
                }
                Projectile projectile = new Projectile(player.x+offset+(event.dx*offset*2), player.y+offset+(event.dy*offset*2), event.dx, event.dy);
                state.put(projectile.hashCode(), projectile);
                synchronized(projectiles) {
                    projectiles.add(projectile);
                }
                // set the orientation of the shooting player
                
                player.orientationX = event.dx;
                player.orientationY = event.dy;
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
                    remove(state.get(key));
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
        }
    }
    public boolean inBounds(Thing thing) {
        return inBoundsIfMoved(thing, 0, 0);
    }
    public boolean inBoundsIfMoved(Thing thing, double dx, double dy) {
        return thing.x+dx < mapsize && thing.x+dx > 0 && thing.y+dy < mapsize && thing.y+dy > 0;
    }
}