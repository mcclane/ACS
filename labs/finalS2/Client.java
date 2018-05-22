import java.net.*;
import java.io.*;


public class Client {
    public static void main(String[] args) throws IOException {
        ClientInterface ci = new ClientInterface("73.231.131.194", 3000);
        ci.run();
    }
}