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

	private PrintWriter out;
    
    private JTextArea chatMessages;
    private JScrollPane messagePane;
    private JTextField textInput;
	private JButton sendButton;
    
    private String messageText = "";
    
    public ClientScreen(){
		
		this.setLayout(null);
		
		textInput = new JTextField(); 
		textInput.setBounds(50,500, 200, 30); 
        textInput.addActionListener(this);
		this.add(textInput); 
	
		sendButton = new JButton("Send"); 
		sendButton.setBounds(50,550, 200, 30);  
		this.add(sendButton); 
		sendButton.addActionListener(this);
        
        chatMessages = new JTextArea(200, 250);
        messagePane = new JScrollPane(chatMessages); 
        messagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messagePane.setBounds(0,00,300,500);
        this.add(messagePane);
		
		this.setFocusable(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.drawString( chatMessage, 100, 50 );
	}

	public Dimension getPreferredSize() {
        return new Dimension(800,600);
	}
	
    public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == sendButton) {
			String sendText = textInput.getText();
            textInput.setText("");
			if( out != null ){
				out.println(sendText);
                messageText = messageText+"You: "+sendText+"\n";
                chatMessages.setText(messageText);
                repaint();
			}
		}
        else if(e.getSource() == textInput) {
            String sendText = textInput.getText();
            textInput.setText("");
			if( out != null ){
				out.println(sendText);
                messageText = messageText+"You: "+sendText+"\n";
                chatMessages.setText(messageText);
                repaint();
			}
        }
		
	}
    
	public void poll() throws IOException{
		
		String hostName = "localhost"; 
        int portNumber = 3000;
		Socket serverSocket = new Socket(hostName, portNumber);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        out = new PrintWriter(serverSocket.getOutputStream(), true);
        
		chatMessages.setText("Them: "+in.readLine() + "\n");
		repaint();
		
		//listens for inputs
		try {

            while (true) {
				String current = in.readLine();
                messageText = messageText+"Them: "+current+"\n";
                chatMessages.setText(messageText);
				repaint();
            }
        } catch (UnknownHostException e) {
            System.err.println("Host unkown: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
	}
}
