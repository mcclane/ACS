import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class Client {
    public static void main(String[] args)  {
         
        String hostName = "localhost";
        int portNumber = 444;
         
        try {
             
            Socket serverSocket = new Socket(hostName, portNumber);
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            
            //First Message received
            String serverMessage = in.readLine();
            System.out.println(serverMessage);
            
            //Second Message received
            serverMessage = in.readLine();
            System.out.println(serverMessage);
             
            Scanner sc = new Scanner(System.in);
            String capital = sc.next();
            
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            out.println(capital);
            System.out.println(in.readLine());
            
            in.close();
            out.close();

  
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}