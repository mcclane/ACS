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

public class ServerScreen extends JPanel implements ActionListener {

    private JTextArea chatMessages;
    private JScrollPane messagePane;
    private JTextField textInput;
	private JButton sendButton;
    
    private String messageText = "";

	private PrintWriter out;
	
	public ServerScreen(){
		
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


	public Dimension getPreferredSize() {

        return new Dimension(800,600);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);


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

	public void poll() throws IOException {
		int portNumber = 444;
		
		ServerSocket serverSocket = new ServerSocket(portNumber);
		Socket clientSocket = serverSocket.accept();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		
		out.println("You are connected to the server!");
        
        try {

            while (true) {
                String current = in.readLine();
                messageText = messageText+"Them: "+current+"\n";
                chatMessages.setText(messageText);
				repaint();
            }
        } catch (IOException e) {
            
            System.exit(1);
        }
	
	}



}
