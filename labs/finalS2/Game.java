import java.util.HashMap;
import java.util.ArrayList;


public class Game implements Runnable {
    HashMap<Integer, Thing> state;
    ArrayList<ServerThread> threads;
    public Game() {
        state = new HashMap<Integer, Thing>();
        threads = new ArrayList<ServerThread>();
    }
    public void add(ServerThread sv) {
        threads.add(sv);
    }
    public void remove(ServerThread sv) {
        threads.remove(sv);
    }
    public void update(Event event) {
        System.out.println("Received: "+event);
        if(event.operation.equals("player_connect")) {
            state.put(event.concerns, new Player(event.concerns, Math.random()*1200, Math.random()*800));
            System.out.println(state);
        }
        else if(event.operation.equals("player_move")) {
            switch(event.direction) {
                case "up":
                    state.get(event.concerns).y -= 25;
                    break;
                case "down":
                    state.get(event.concerns).y += 25;
                    break;
                case "left":
                    state.get(event.concerns).x -= 25;
                    break;
                case "right":
                    state.get(event.concerns).x += 25;
                    break;
            }
        }
        else if(event.operation.equals("player_shoot")) {
            double x = state.get(event.concerns).x;
            double y = state.get(event.concerns).y;
            double dx = event.mouseX - x;
            double dy = event.mouseY - y;
            double magnitude = Math.sqrt(dx*dx + dy*dy);
            dx /= magnitude;
            dy /= magnitude;
            Projectile projectile = new Projectile(x, y, dx*20, dy*20);
            state.put(projectile.hashCode(), projectile);
        }
    }
    public void broadcastState() {
        //System.out.println(state);
        for(ServerThread sv : threads) {
            sv.send(state);
        }
    }
    public void run() {
        while(true) {
            // take care of running the game
            for(int key : state.keySet()) {
                state.get(key).move();
            }
            // broadcast the state
            broadcastState();

            // sleep
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }
}