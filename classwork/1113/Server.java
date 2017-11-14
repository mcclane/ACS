import java.net.*;
import java.io.*;
 
public class Server {
    public static void main(String[] args) {
         
        int portNumber = 444;
 
        try {
             
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
             
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             
            //First Message Sent
            out.println("Connection Successful!");   
             
             
            //Second Message Sent
            out.println("What is the capital of California?");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             
            //First Message received
            String serverMessage = in.readLine();
            if(serverMessage.trim().equals("Sacramento")) {
                out.println("Correct!");
            }
            else {
                out.println("Wrong!");
            }
            out.close();
             
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}