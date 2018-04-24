import java.net.*;
import java.io.*;
public class ServerThread implements Runnable{	
	private Socket clientSocket;
    private Manager manager;
	public ServerThread(Socket clientSocket, Manager manager){
		this.clientSocket = clientSocket;
        this.manager = manager;
	}
    public void send(String message) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
        }
        catch(IOException e) {
            System.out.println("IO Exception");
        }
    }
	public void run(){
		System.out.println(Thread.currentThread().getName() + ": connection opened.");
		try{
            //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Enter Username: ");
            String username = in.readLine();
            System.out.println("Username is: "+username);
            while(true) {
                //Sends a message
                //out.println(question);
                // wait for the answer
                System.out.println("waiting for messages");
                String message = in.readLine();
                System.out.println("Message: "+message);
                manager.broadcast(username+": "+message);
                //Clears and close the output stream.
                //out.flush();
                //out.close();
                //System.out.println(Thread.currentThread().getName() + ": connection closed.");
            }
		} catch (IOException ex){
			System.out.println("Error listening for a connection");
			System.out.println(ex.getMessage());
            manager.remove(this);
		}
	}
}
