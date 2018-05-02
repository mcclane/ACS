import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;


public class Game implements Runnable {
    HashMap<Integer, Thing> state;
    HashSet<Thing> players;
    HashSet<Thing> obstacles;
    HashSet<Thing> projectiles;
    ArrayList<ServerThread> threads;
    public Game() {
        state = new HashMap<Integer, Thing>();
        players = new HashSet<Thing>();
        obstacles = new HashSet<Thing>();
        projectiles = new HashSet<Thing>();
        threads = new ArrayList<ServerThread>();
        
        //add some objects to create the level
        for(int i = 0;i < 5;i++) {
            for(int j = 0;j < 5;j++) {
                Obstacle o = new Obstacle(i*100, j*100, 50, 50);
                state.put(o.hashCode(), o);
                obstacles.add(o);
            }
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
        System.out.println("Received: "+event);
        synchronized(state) {
            if(event.operation.equals("player_connect")) {
                state.put(event.concerns, new Player(event.concerns, Math.random()*1200, Math.random()*800));
            }
            else if(event.operation.equals("player_move")) {
                double dx = 0;
                double dy = 0;
                switch(event.direction) {
                    case "up":
                        dy = -2;
                        break;
                    case "down":
                        dy = 2;
                        break;
                    case "left":
                        dx = -2;
                        break;
                    case "right":
                        dx = 2;
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
                    if(move) {
                        state.get(event.concerns).x += dx;
                        state.get(event.concerns).y += dy;
                    }
                }   
            }
            else if(event.operation.equals("player_shoot")) {
                // TODO: Move this to a function outside of update
                double x = state.get(event.concerns).x;
                double y = state.get(event.concerns).y;
                double dx = event.mouseX - x;
                double dy = event.mouseY - y;
                double magnitude = Math.sqrt(dx*dx + dy*dy);
                dx /= magnitude;
                dy /= magnitude;
                Projectile projectile = new Projectile(x, y, dx, dy);
                state.put(projectile.hashCode(), projectile);
                // set the orientation of the shooting player
                Player player = (Player)(state.get(event.concerns));
                player.orientationX = dx;
                player.orientationY = dy;
            }
        }
    }
    public void broadcastState() {
        synchronized(state) {
            //System.out.println(state);
            synchronized(threads) {
                for(ServerThread sv : threads) {
                    sv.send(state);
                }
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
                            if(key != projectile.hashCode() && projectile.collision(state.get(key))) {
                                toBeRemoved.add(key);
                                System.out.println("collision!");
                            }
                        }
                    }
                }
                // take care of running the game
                for(int key : state.keySet()) {
                    state.get(key).move();
                    if(!inBounds(state.get(key)) && !state.get(key).type().equals("player")) {
                        toBeRemoved.add(key);
                    }
                }
                // get rid of things that have moved out of bounds
                for(int key : toBeRemoved) {
                    if(state.containsKey(key)) {
                        state.remove(key);
                    }
                }
                
            }
            // sleep
            try {
                Thread.sleep(17);
            } catch(InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }
    public boolean inBounds(Thing thing) {
        return thing.x < 1200 && thing.x > 0 && thing.y < 800 && thing.y > 0;
    }
}