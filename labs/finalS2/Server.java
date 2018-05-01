
import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        Thread gameThread = new Thread(game);
        gameThread.start();
        ServerSocket serverSocket = new ServerSocket(3000);
        while(true) {
            System.out.println("Waiting for a connection");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected");
            ServerThread sv = new ServerThread(clientSocket, game);
            game.add(sv);
            Thread svThread = new Thread(sv);
            svThread.start();
        }
    }
}