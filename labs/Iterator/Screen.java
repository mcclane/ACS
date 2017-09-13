import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Arrays;

public class Screen extends JPanel implements ActionListener {

	//contact info
	private JTextField contactInfoField;
	private JButton addContactInfo;
	ArrayList<String> contactInfo;
	boolean contactInfoAdded = false;

	//education info
	private JTextField educationField;
	private JButton addEducation;
	ArrayList<Education>  educationList;
	boolean educationAdded = false;
	
	//job info
	
	
    public Screen() {
        this.setLayout(null);
		
        //Contact Info
        //JButton
		addContactInfo = new JButton("Add contact info");
		addContactInfo.setBounds(350, 50, 200, 30); //sets the location and size
	    addContactInfo.addActionListener(this); //add the listener
		this.add(addContactInfo); //add to JPanel
		//TextField
		contactInfoField = new JTextField();
		contactInfoField.setBounds(50,50, 300, 30);
		this.add(contactInfoField);
		
		//education info
		educationList = new ArrayList<Education>();
		addEducation = new JButton("Add education info");
		addEducation.setBounds(350, 100, 200, 30); //sets the location and size
	    addEducation.addActionListener(this); //add the listener
		this.add(addEducation); //add to JPanel
		//TextField
		educationField = new JTextField();
		educationField.setBounds(50,100, 300, 30);
		this.add(educationField);
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1500,800);
    }
    public void paintComponent(Graphics g) {
		//draw background
        g.setColor(Color.white);
        g.fillRect(0,0,1500,800);
        
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.black);
		
		//draw the form
		//contact info
		g.drawString("Please Enter FirstName LastName, address, email, and phone separated by commas", 50, 25);
		//education
		g.drawString("Please Enter Name, location, and date of graduation (YYYY-MM) separated by commas", 50, 100);
		
		//display resume
		g.drawString("Resume", 1000, 20);
		int x = 900;
		int y = 40;
		//contact info
		if(contactInfoAdded) {
			ListIterator<String> contactIterator = contactInfo.listIterator();
			while(contactIterator.hasNext()) {
				g.drawString(contactIterator.next(), 900, y);
				y += 25;
			}
		}
		//education
		if(educationAdded) {
			ListIterator<Education> educationIterator = educationList.listIterator();
			while(educationIterator.hasNext()) {
				g.drawString(educationIterator.next().toString(), 900, y);
				y += 25;
			}
		}
       
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addContactInfo) { 
			contactInfo = new ArrayList<String>();
			String[] split = contactInfoField.getText().split(",");
			for(String s : split) {
				contactInfo.add(s);
			}
			contactInfoAdded = true;
        }
		else if(e.getSource() == addEducation) {
			String[] split = educationField.getText().split(",");
			int date = Integer.parseInt(split[2].replace("-","").trim());
			ListIterator<Education> lit = educationList.listIterator();
			boolean added = false;
			while(lit.hasNext()) {
				if(lit.next().getDate() > date) {
					lit.previous();
					lit.add(new Education(split[0].trim(), split[1].trim(), split[2].trim()));
					added = true;
					educationAdded = true;
					break;
				}
			}
			if(added == false)  {
				educationList.add(new Education(split[0].trim(), split[1].trim(), split[2].trim()));
				educationAdded = true;
			}
		}
        repaint();
    }
}

