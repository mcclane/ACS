import java.util.ArrayList;

public class Manager {
    ArrayList<ServerThread> threads;
    public Manager() {
        threads = new ArrayList<ServerThread>();
    }
    public synchronized void add(ServerThread thread) {
        threads.add(thread);
    }
    public synchronized void remove(ServerThread thread) {
        threads.remove(thread);
    }
    public synchronized void broadcast(String message) {
        for(ServerThread thread : threads) {
            thread.send(message);
        }
    }
}