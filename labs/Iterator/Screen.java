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

	//Jack Black, 123 Fake Street, yahoo@gmail.com, 1234567890
	//Harvard, Massachussetts, 2022-01
	//CEO, google, mountain view, 2023-01, 2024-01
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
	private JButton deleteEducation;
	
	//job info
	private JTextField jobField;
	private JButton addJob;
	ArrayList<Job>  jobList;
	boolean jobAdded = false;
	private JButton deleteJob;
	
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
		//delete button
		deleteEducation = new JButton("Delete Job Info");
		deleteEducation.setBounds(550, 100, 200, 30); //sets the location and size
	    deleteEducation.addActionListener(this); //add the listener
		this.add(deleteEducation); //add to JPanel
		
		//job info
		jobList = new ArrayList<Job>();
		addJob = new JButton("Add job info");
		addJob.setBounds(350, 150, 200, 30); //sets the location and size
	    addJob.addActionListener(this); //add the listener
		this.add(addJob); //add to JPanel
		//TextField
		jobField = new JTextField();
		jobField.setBounds(50,150, 300, 30);
		this.add(jobField);
		//delete button
		deleteJob = new JButton("Delete Job Info");
		deleteJob.setBounds(550, 150, 200, 30); //sets the location and size
	    deleteJob.addActionListener(this); //add the listener
		this.add(deleteJob); //add to JPanel
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1500,800);
    }
    public void paintComponent(Graphics g) {
		//draw background
        g.setColor(Color.black);
        g.fillRect(0,0,1500,800);
		
		//draw the paper
		g.setColor(Color.white);
		g.fillRect(750, 20, 550, 750);
		//draw the lines
		g.setColor(Color.cyan);
		for(int i = 80;i < 770;i += 20) {
			g.fillRect(760, i, 530, 3);
		}
		g.setColor(Color.red);
		g.fillRect(810, 20, 3, 750);
        
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.white);
		
		//draw the instructions
		//contact info
		g.drawString("Please Enter FirstName LastName, address, email, and phone separated by commas", 50, 45);
		//education
		g.drawString("Please Enter Name, location, and date of graduation (YYYY-MM) separated by commas", 50, 95);
		//job
		g.drawString("Please Enter Title, company, location, start date, and end date (YYYY-MM) separated by commas", 50, 145);
		
		//display resume
		g.setColor(Color.black);
		g.drawString("Resume", 1000, 50);
		int x = 900;
		int y = 100;
		//contact info
		if(contactInfoAdded) {
			g.drawString("Contact Information", x-40, y);
			y+=20;
			ListIterator<String> contactIterator = contactInfo.listIterator();
			while(contactIterator.hasNext()) {
				g.drawString(contactIterator.next(), 900, y);
				y += 20;
			}
			y += 20;
		}
		//education
		if(educationAdded) {
			g.drawString("Education", x-40, y);
			y+=20;
			ListIterator<Education> educationIterator = educationList.listIterator();
			while(educationIterator.hasNext()) {
				g.drawString(educationIterator.next().toString(), 900, y);
				y += 20;
			}
			y+=20;
		}
		if(jobAdded) {
			g.drawString("Job Experience", x-40, y);
			y+=20;
			ListIterator<Job> jobIterator = jobList.listIterator();
			while(jobIterator.hasNext()) {
				g.drawString(jobIterator.next().toString(), 900, y);
				y += 20;
			}
			y+= 20;
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
		else if(e.getSource() == addJob) {
			String[] split = jobField.getText().split(",");
			int date = Integer.parseInt(split[3].replace("-","").trim());
			ListIterator<Job> lit = jobList.listIterator();
			boolean added = false;
			while(lit.hasNext()) {
				if(lit.next().getDate() > date) {
					lit.previous();
					lit.add(new Job(split[0].trim(), split[1].trim(), split[2].trim(), split[3].trim(), split[4].trim()));
					added = true;
					jobAdded = true;
					break;
				}
			}
			if(added == false)  {
				jobList.add(new Job(split[0].trim(), split[1].trim(), split[2].trim(), split[3].trim(), split[4].trim()));
				jobAdded = true;
			}
		}
		else if(e.getSource() == deleteJob) {
			String[] split = jobField.getText().split(",");
			String toString = split[0].trim()+", "+split[1].trim()+", "+split[2].trim()+", from "+split[3].trim()+" to "+ split[4].trim(); 
			ListIterator<Job> lit = jobList.listIterator();
			while(lit.hasNext()) {
				if(lit.next().toString() == toString) {
					lit.next();
					lit.remove();
					break;
				}
			}
		}
		else if(e.getSource() == deleteEducation) {
			
		}
        repaint();
    }
}

