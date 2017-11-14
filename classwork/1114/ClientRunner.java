import javax.swing.JFrame;
import java.io.*;

public class ClientRunner {

    public static void main(String args[]) throws IOException {

        JFrame frame = new JFrame("Client");

        ClientScreen sc = new ClientScreen();
        frame.add(sc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

		sc.poll();
    }
}
