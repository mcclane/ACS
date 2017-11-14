import java.net.*;
import java.io.*;
import java.util.HashMap;

public class Chatbot {
    public static void main(String[] args) {
         
        int portNumber = 444;
        
        HashMap<String, String> responses = new HashMap<String, String>();
        responses.put("joke", "Paran is a joke");
        responses.put("problem", "Please elaborate on your problem");
        responses.put("Hello", "How are you?");
        responses.put("Hi", "How are you?");
        responses.put("bye", "Adios");
        responses.put("who", "I am chatbot");
 
        try {
             
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
             
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             
            //First Message Sent
            out.println("Connection Successful!");   
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(true) {
                String message = in.readLine().trim();
                boolean responded = false;
                for(String k : responses.keySet()) {
                    if(message.contains(k)) {
                        out.println(responses.get(k));
                        responded = true;
                    }
                }
                if(responded == false) {
                    out.println("Please say something else");
                }
                if(message.equals("bye")) {
                    break;
                }
            }            
            
            out.close();
             
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}