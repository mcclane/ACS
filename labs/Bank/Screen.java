import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

 
 
public class Screen extends JPanel implements ActionListener {
    private JButton b1;
	private JButton logoutButton;
	private JButton submit;
    private JTextField usernameField;
	private JTextField pinField;
    private JTextField amount;
    private String name = "";
	private int pin;
	private Account acc;
	private boolean access = false;
	
	private Account accounts[] = {	new Account("Jennifer", 999.99, 1234), 
									new Account("Jose", 500.01, 4321),
									new Account("McClane", 1000000, 5432),
									new Account("Nick", 1, 6543),
									new Account("Austin", 10, 7654)
								};
    public Screen() {
        this.setLayout(null);
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(100,170, 200, 30); //sets the location and size
		logoutButton.addActionListener(this); //add the listener
		logoutButton.setVisible(false);
		this.add(logoutButton); //add to JPanel
		//Button
		b1 = new JButton("Login");
		b1.setBounds(100,120, 200, 30); //sets the location and size
		b1.addActionListener(this); //add the listener
		b1.setVisible(false);
		this.add(b1); //add to JPanel
		
		//Button
		submit = new JButton("Withdraw/Deposit");
		submit.setBounds(100,140, 200, 30); //sets the location and size
		submit.addActionListener(this); //add the listener
		submit.setVisible(false);
		this.add(submit); //add to JPanel
	 
		//TextField
		usernameField = new JTextField(20);
		usernameField.setBounds(100,50, 80, 30);
		usernameField.setVisible(false);
		this.add(usernameField);
			
		//TextFiel10d
		pinField = new JTextField(20);
		pinField.setBounds(100,80, 80, 30);
		this.add(pinField);
		pinField.setVisible(false);
        this.setFocusable(true);
		
		//TextField
		amount = new JTextField(20);
		amount.setBounds(100,100, 80, 30);
		amount.setVisible(false);
		this.add(amount);
    }
 
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,400);
    }
 
    public void paintComponent(Graphics g) {
		//draw background
        g.setColor(Color.white);
        g.fillRect(0,0,800,400);
        
		//draw instructions
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.red);
		g.drawString("Welcome "+name, 100, 20);

		if(access) {
			//draw the picture
			try {
				BufferedImage image = ImageIO.read(new File("images\\"+name+".jpg"));
				g.drawImage(image, 0,0, 800, 400, this);
			} catch(IOException ex) {
				System.out.println(ex);
				System.out.println("couldn't get image");
			}
			g.drawString("Welcome "+name, 100, 20);
			b1.setVisible(false);
			usernameField.setVisible(false);
			pinField.setVisible(false);
			logoutButton.setVisible(true);
			amount.setVisible(true);
            submit.setVisible(true);
			g.drawString("Balance "+acc.getBalance(), 10, 75);
			
		}
		else {
			b1.setVisible(true);
			usernameField.setVisible(true);
			pinField.setVisible(true);
			logoutButton.setVisible(false);
			amount.setVisible(false);
            submit.setVisible(false);
			//draw instructions
			g.setColor(Color.red);
			g.drawString("name", 10, 75);        
			
			//draw instructions
			g.setColor(Color.red);
			g.drawString("pin", 10, 95);
		}
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { 
            name = usernameField.getText();
			pin = Integer.parseInt(pinField.getText());
			//get the account matched to the name
			for(int i = 0;i < accounts.length;i++) {
				if(accounts[i].getName().equals(name)) {
					acc = accounts[i];
				}
			}
			if(acc != null) {
				//try to login to the account
				acc.setAccess(pin);
				if(acc.getAccess()) {
					access = true;
					usernameField.setText("");
					pinField.setText("");
				}
				else {
					access = false;
				}
			}
			//b1.getParent().repaint();
            repaint();
        }
        else if(e.getSource() == submit) {
            double amt = Double.parseDouble(amount.getText());
            if(amt < 0 && Math.abs(amt) <= acc.getBalance()) {
                acc.withdraw(Math.abs(amt));
            }
            else if(amt > 0) {
                acc.deposit(amt);
            }
            repaint();
        }
		else if(e.getSource() == logoutButton) {
			access = false;
			name = "";
			repaint();
		}
    }
 
 
 
}
