import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.*;

public class ClientScreen extends JPanel implements ActionListener {
    
    ObjectOutputStream outObj;
    ObjectInputStream inObj;
    Game game;
    
	public ClientScreen() throws IOException{
		
		this.setLayout(null);
		
		this.setFocusable(true);
        
        game = new Game();
        
        try {
            String hostName = "localhost";
            int portNumber = 444;
            Socket serverSocket = new Socket(hostName, portNumber);
             
            inObj = new ObjectInputStream(serverSocket.getInputStream());
            outObj = new ObjectOutputStream(serverSocket.getOutputStream());
            game = (Game)inObj.readObject();            
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not exist" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
	}


	public Dimension getPreferredSize() {
        return new Dimension(800,600);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);


	}
    public void getGame() throws IOException {
        try {      
            game = (Game) inObj.readObject();       
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not exist" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to get game");
            System.exit(1);
        }
    }
    public void sendGame() throws IOException {
         try {             
            outObj.writeObject(game);
            outObj.close();
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
    }
	public void actionPerformed(ActionEvent e) {
		
	}
}
