import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {
		int portNumber = 1024;
		ServerSocket serverSocket = new ServerSocket(portNumber);
        Questions.load();
		//This loop will run and wait for one connection at a time.
		while(true){
			System.out.println("Waiting for a connection");

			//Wait for a connection.
			Socket clientSocket = serverSocket.accept();

			//Once a connection is made, run the socket in a ServerThread.
			Thread thread = new Thread(new ServerThread(clientSocket));
			thread.start();
		}
	}
}
