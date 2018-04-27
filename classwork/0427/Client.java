import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Client {
public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Screen");
        Screen screen = new Screen();
       
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        MyReader reader = new MyReader();
        Thread readerThread = new Thread(reader);
        readerThread.start();
        String hostName = "localhost";
        int portNumber = 1024 ;
        Socket serverSocket = new Socket(hostName, portNumber);
        BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));    
        PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
        System.out.println("Connected?");
        String previousMessage = "";
        try {
            while(true) {
                //Waiting for input from the server
                if(in.ready()) {
                    String fromServer = in.readLine();
                    if(fromServer == null) {
                        break;
                    }
                    //System.out.println(fromServer);
                    System.out.println("From Server: "+fromServer);
                    screen.addPlayer(fromServer);
                }
                
                String message = screen.getInput();
                if(message != null && !message.equals(previousMessage)) {
                    System.out.println(message);
                    previousMessage = message;
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
class Screen extends JPanel implements KeyListener {
    String toBeSent = null;
    HashMap<String, String> players;
    int x = (int)(Math.random()*700);
    int y = (int)(Math.random()*700);
    int color = 10000;
    JTextField username;
    public Screen()  {
        
        this.setLayout(null);
        username = new JTextField();
        username.setBounds(50, 50, 200, 30);
        username.setText(""+(int)(Math.random()*10000));
        this.add(username);
        addKeyListener(this);
        //this.setFocusable(true);
        username.setFocusable(false);
        
        players = new HashMap<String, String>();
        
    }
    public void addPlayer(String info) {
        // username color x,y
        try {
            String[] splitted = info.split("\\s+");
            String name = splitted[0];
            players.put(name, splitted[1]+" "+splitted[2]);
            repaint();
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("OutOfBounds: "+info);
        }
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(700, 700);
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 700, 700);
        g.setColor(new Color(color));
        g.fillRect(x, y, 25, 25);
        g.setColor(Color.black);
        g.drawString(username.getText(), x, y);
        for(String player : players.keySet()) {
            String[] splitted = players.get(player).split("\\s+");
            int x = Integer.parseInt(splitted[1].split(",")[0]);
            int y = Integer.parseInt(splitted[1].split(",")[1]);
            g.setColor(new Color(Integer.parseInt(splitted[0])));
            g.fillRect(x, y, 25, 25);
            g.setColor(Color.black);
            g.drawString(player, x, y);
        }
    }
    public String getInput() {
        return username.getText()+" "+color+" "+x+","+y;
    }
    public void keyPressed(KeyEvent e) {
        int keyDown = e.getKeyCode();
        // w - up
        if(keyDown == 87) {
            y -= 25;
        }
        // s - down
        else if(keyDown == 83) {
            y += 25;
        }
        // d - right
        else if(keyDown == 68) {
            x += 25;
        }
        // a - left
        else if(keyDown == 65) {
            x -= 25;
        }
        repaint();
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}