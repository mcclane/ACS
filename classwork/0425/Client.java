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
        System.out.println(in.readLine());
        
        try {
            while(true) {
                //Waiting for input from the server
                if(in.ready()) {
                    String fromServer = in.readLine();
                    if(fromServer == null) {
                        break;
                    }
                    System.out.println(fromServer);
                    screen.addMessage(fromServer);
                }
                String message = screen.getInput();
                if(message != null) {
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
class Screen extends JPanel implements ActionListener {
    JTextField input;
    JTextField username;
    JTextArea chat;
    ArrayList<String> messages;
    String toBeSent = null;
    public Screen()  {
        this.setLayout(null);
        username = new JTextField();
        username.setBounds(50, 50, 200, 30);
        this.add(username);
        
        input = new JTextField();
        input.setBounds(400, 850, 400, 30);
        input.addActionListener(this);
        this.add(input);
        
        messages = new ArrayList<String>();
        
        chat = new JTextArea(200, 250);
        chat.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chat); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(200,100,800,700);
        this.add(scrollPane);
        
    }
    public String getInput() {
        String temp = toBeSent;
        toBeSent = null;
        return temp;
    }
    public void addMessage(String message) {
        messages.add(message);
        String out = "";
        for(String s : messages) {
            out += s+"\n";
        }
        chat.setText(out);
        repaint();
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1200, 900);
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, 1200, 900);
        g.setColor(Color.green);
        g.drawString("Hello, World", 50, 50);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == input) {
            toBeSent = username.getText()+": "+input.getText();
            input.setText("");
        }
    }
}