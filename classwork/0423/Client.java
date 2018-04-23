import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
public static void main(String[] args) throws IOException {
        String hostName = "10.210.22.53";
        int portNumber = 1024 ;
        Socket serverSocket = new Socket(hostName, portNumber);
        BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));    
        PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        try {
            while(true) {
                //Waiting for input from the server
                String fromServer = in.readLine();
                if(fromServer == null) {
                    break;
                }
                System.out.println("Server: " + fromServer);
                String answer = sc.next();
                out.println(answer);
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error connecting to " + hostName);
            System.exit(1);
        }
    }
}
