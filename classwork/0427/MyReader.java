import java.util.Scanner;

public class MyReader implements Runnable {
    String message = "";
    public void run() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            message = sc.nextLine();
        }
    }
    public String getInput() {
        String temp = message;
        message = "";
        return temp;
    }
}