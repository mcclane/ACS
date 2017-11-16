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
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.io.*;
import java.net.*;

public class ServerScreen extends JPanel implements ActionListener, MouseListener {
    
    ObjectOutputStream outObj;
    Game game;
    private int gridX = 50;
    private int gridY = 50;
    private int squareSize = 50;
    private boolean sent = false;
    
	public ServerScreen() throws IOException{
		
		this.setLayout(null);
		this.setFocusable(true);
        addMouseListener(this);
        
        game = new Game();
        
	}
	public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.drawMe(g, gridX, gridY, squareSize);
	}
	public void actionPerformed(ActionEvent e) {}
    public void mousePressed(MouseEvent e) {
        int row = (e.getY() - gridY)/squareSize;
        int column = (e.getX() - gridX)/squareSize;
        if(game.move(row, column, "X")) {
            try {
                outObj.writeObject(game);
            } catch (IOException err) {
                System.out.println(err);
            }
            repaint();
        }
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void poll() {

        String hostName = "localhost";
        int portNumber = 3000;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
             
            outObj = new ObjectOutputStream(clientSocket.getOutputStream());
            outObj.writeObject("Connection Successful!");
             
            ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());

            while(true) {
                game = (Game) inObj.readObject();
                repaint();
            }
 
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
    }
}
