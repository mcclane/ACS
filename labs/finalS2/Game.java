import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;


public class Game implements Runnable {
    HashMap<Integer, Thing> state;
    ArrayList<ServerThread> threads;
    public Game() {
        state = new HashMap<Integer, Thing>();
        threads = new ArrayList<ServerThread>();
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
                switch(event.direction) {
                    case "up":
                        state.get(event.concerns).y -= 2;
                        break;
                    case "down":
                        state.get(event.concerns).y += 2;
                        break;
                    case "left":
                        state.get(event.concerns).x -= 2;
                        break;
                    case "right":
                        state.get(event.concerns).x += 2;
                        break;
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
                Projectile projectile = new Projectile(x, y, dx*20, dy*20);
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
                // take care of running the game
                for(int key : state.keySet()) {
                    state.get(key).move();
                    if(!inBounds(state.get(key)) && !state.get(key).type().equals("player")) {
                        toBeRemoved.add(key);
                    }
                }
                // get rid of things that have moved out of bounds
                for(int key : toBeRemoved) {
                    state.remove(key);
                }
                // check collisions between players and objects
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