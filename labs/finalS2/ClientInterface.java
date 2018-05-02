import java.net.*;
import java.io.*;
import java.util.HashMap;
import javax.swing.JFrame;


public class ClientInterface {
    ObjectInputStream in;
    ObjectOutputStream out;
    Screen screen;
    public ClientInterface(String hostname, int port) {
        // set up the networking
        try {
            Socket socket = new Socket(hostname, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch(UnknownHostException e) {
            System.out.println("Unknown Host");
        } catch(IOException e) {
            System.out.println("IOException creating in and out");
        }
        // set up the screen
        screen = new Screen(this);
        JFrame frame = new JFrame("Screen");
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public synchronized void send(Event event) {
        try {
            out.writeObject(event);
        } catch(IOException e) {
            System.out.println("IOException trying to send: ");
            //e.printStackTrace();
        }
    }
    public void run() {
        while(true) {
            try {
                screen.update((HashMap<Integer, Thing>)(in.readObject()));
            } catch(IOException e) {
                System.out.println("IOException reading in run");
                //break;
            } catch(ClassNotFoundException e) {
                System.out.println("ClassNotFoundException reading in run");
                //e.printStackTrace();
            }
        }
    }
}