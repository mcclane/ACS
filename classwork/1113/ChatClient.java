import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class ChatClient {
    public static void main(String[] args)  {
         
        String hostName = "172.20.122.30";
        int portNumber = 444;
         
        try {
             
            Socket serverSocket = new Socket(hostName, portNumber);
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            
            Scanner sc = new Scanner(System.in);
            
            while(true) {
                String message = in.readLine();
                System.out.println(message);
                System.out.println("enter in your message: ");
                String tobesent = sc.nextLine();
                boolean done = tobesent.equals("bye");
                out.println(tobesent.trim());
                if(done)
                    break;
            }
            
            in.close();
            out.close();

  
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}