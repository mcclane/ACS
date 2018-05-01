import java.net.*;
import java.io.*;
import java.util.HashMap;

public class ServerThread implements Runnable {
    ObjectInputStream in;
    ObjectOutputStream out;
    Game game;
    public ServerThread(Socket socket, Game game) {
        this.game = game;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }
        catch(IOException e) {
            System.out.println("!!!!! IOException trying to create in and out in ServerThread");
            //e.printStackTrace();
        }
    }
    public synchronized void send(HashMap<Integer, Thing> state) {
        //System.out.println("Sending: "+state);
        try {
            out.reset();
            out.writeObject(state);
        } catch(IOException e) {
            System.out.println("!!!! IOException trying to send in ServerThread");
            //e.printStackTrace();
            game.remove(this);
        }
    }
    public void run() {
        while(true) {
            try {
                // read in events from the client
                Event event = (Event)(in.readObject());
                // send the event to the game to update the game state
                game.update(event);
            } catch(IOException e) {
                System.out.println("!!!! IOException trying to read in ServerThread");
                //e.printStackTrace();
                game.remove(this);
                break;
            } catch(ClassNotFoundException e) {
                System.out.println("!!!! ClassNotFoundException trying to read in ServerThread");
                //e.printStackTrace();
            }
        }
    }
}