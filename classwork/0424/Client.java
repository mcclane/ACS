import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
public static void main(String[] args) throws IOException {
        MyReader reader = new MyReader();
        Thread readerThread = new Thread(reader);
        readerThread.start();
        String hostName = "localhost";
        int portNumber = 1024 ;
        Socket serverSocket = new Socket(hostName, portNumber);
        BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));    
        PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
        System.out.println(in.readLine());
        Scanner sc = new Scanner(System.in);
        out.println(sc.next());
        try {
            while(true) {
                //Waiting for input from the server
                if(in.ready()) {
                    String fromServer = in.readLine();
                    if(fromServer == null) {
                        break;
                    }
                    System.out.println(fromServer);
                }
                String message = reader.getInput();
                if(!message.equals("")) {
                    out.println(message);
                }
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
