import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.MouseInfo;
import java.util.PriorityQueue;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Screen extends JPanel implements ActionListener {
    private JButton v1, v2, v3, v4;
    int view;

    //nurse stuff
    private JButton addPatientButton;
    private JTextArea nurseTextArea;
    private JScrollPane scrollPaneNurse;
    private JButton editPatientButton;
	private JButton searchPatientButton;
    private JTextField editPatientFirstName;
    private JTextField editPatientLastName;
    private JTextField editPatientIllness;
    private JTextField editPatientPriority;

	//doctor stuff
	private JTextField doctorNoteField;
	private JButton dischargePatientButton;
    
    private int time = 6;
	
	private Patient editingPatient;
    
    PriorityQueue<Patient> patients;
    public Screen() {
        this.setLayout(null);

        v1 = new JButton("Nurse");
        v1.setBounds(100, 100, 200, 30);
        v1.addActionListener(this);
        this.add(v1);
        v2 = new JButton("Doctor");
        v2.setBounds(300, 100, 200, 30);
        v2.addActionListener(this);
        this.add(v2);
        v3 = new JButton("Biller");
        v3.setBounds(500, 100, 200, 30);
        v3.addActionListener(this);
        this.add(v3);
        v4 = new JButton("Admin");
        v4.setBounds(700, 100, 200, 30);
        v4.addActionListener(this);
        this.add(v4);
        
        //nurse stuff
        addPatientButton = new JButton("Update Patient");
        addPatientButton.setBounds(50, 700, 200, 30);
        addPatientButton.addActionListener(this);
        this.add(addPatientButton);
        addPatientButton.setVisible(false);

        searchPatientButton = new JButton("Search Patient");
        searchPatientButton.setBounds(250, 700, 200, 30);
        searchPatientButton.addActionListener(this);
        this.add(searchPatientButton);
        searchPatientButton.setVisible(false);
        
        editPatientButton = new JButton("edit patient");
        editPatientButton.setBounds(450, 700, 200, 30);
        editPatientButton.addActionListener(this);
        this.add(editPatientButton);
        editPatientButton.setVisible(false);
        
        editPatientFirstName = new JTextField(100);
        editPatientFirstName.setBounds(200,750, 300, 30);
        editPatientFirstName.addActionListener(this);
        this.add(editPatientFirstName);
        editPatientFirstName.setVisible(false);

        editPatientLastName = new JTextField(100);
        editPatientLastName.setBounds(200,780, 300, 30);
        editPatientLastName.addActionListener(this);
        this.add(editPatientLastName);
        editPatientLastName.setVisible(false);
        
        editPatientIllness = new JTextField(100);
        editPatientIllness.setBounds(200,810, 300, 30);
        editPatientIllness.addActionListener(this);
        this.add(editPatientIllness);
        editPatientIllness.setVisible(false);
 
        editPatientPriority = new JTextField(100);
        editPatientPriority.setBounds(200,840, 300, 30);
        editPatientPriority.addActionListener(this);
        this.add(editPatientPriority);
        editPatientPriority.setVisible(false);
        
        nurseTextArea = new JTextArea(200, 250);
        scrollPaneNurse = new JScrollPane(nurseTextArea); 
        scrollPaneNurse.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneNurse.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneNurse.setBounds(800,400,300,500);
        this.add(scrollPaneNurse);
        scrollPaneNurse.setVisible(false);

		//doctor stuff
		
        
        patients = new PriorityQueue<Patient>();
        patients.add(new Patient("Jack", "Johnson", "Herpes", 0, 0));
        patients.add(new Patient("Austin", "Pan", "Living", 1, 1));
        patients.add(new Patient("Chris", "Young", "Osteoporosis", 2, 2));
        patients.add(new Patient("Brian", "Chao", "Depression", 3, 3));
        patients.add(new Patient("David", "Sillman", "Large Mouth", 0, 4));
        
        view = 0;
        
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1200,1000);
    }
    public void paintComponent(Graphics g) {
		// draw background
        g.setColor(Color.black);
        g.fillRect(0,0,1200,1000);
        //set font
        Font font = new Font("Arial", Font.PLAIN, 32);
        g.setFont(font);
        //set color
        
        //nurse stuff
        
        
        switch(view) {
            case 1:
                drawNurseView(g);
                break;
            case 2:
                drawDoctorView(g);
                break;
            case 3:
                drawBillerView(g);
                break;
            case 4:
                drawAdminView(g);
                break;
            default:
                g.setColor(Color.red);
                g.drawString("Please make a selection", 600, 500);
        }
    }
    public void drawBackground(Graphics g, Color first, Color second) {
        Color color = first;
        for(int i = 0;i < 1150;i+=50) {
            for(int j = 0;j < 950;j+=50) {
                if(color == first)
                    color = second;
                else
                    color = first;
                g.setColor(color);
                g.fillRect(i, j, 50, 50);
            }
        }
    }
    public void drawNurseView(Graphics g) {
        g.setColor(Color.red);
        g.drawString("Nurse View", 500, 400);
		g.drawString("First Name", 30, 770);
		g.drawString("Last Name", 30, 800);
		g.drawString("Illness", 30, 830);
		g.drawString("Priority", 30, 870);

        addPatientButton.setVisible(true);
        scrollPaneNurse.setVisible(true);
        editPatientButton.setVisible(true);
		editPatientFirstName.setVisible(true);
		editPatientLastName.setVisible(true);
		editPatientIllness.setVisible(true);
		editPatientPriority.setVisible(true);
        searchPatientButton.setVisible(true);
        
        String text = "";
		PriorityQueue<Patient> copy = new PriorityQueue<Patient>(patients);
		while(!copy.isEmpty()) {
			text += copy.poll()+"\n";
		}
        nurseTextArea.setText(text);
    }
    public void drawDoctorView(Graphics g) {
    	    
    }
    public void drawBillerView(Graphics g) {
        
    }
    public void drawAdminView(Graphics g) {
        
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == v1) {
            view = 1;  
            repaint();
        }
        else if(e.getSource() == v2) {
            view = 2;
            repaint();
        }
        else if(e.getSource() == v3) {
            view = 3;
            repaint();
        }
        else if(e.getSource() == v4) {
            view = 4;
            repaint();
        }
        else if(e.getSource() == addPatientButton) {
            patients.add(new Patient(editPatientFirstName.getText().trim(), editPatientLastName.getText().trim(), editPatientIllness.getText().trim(), Integer.parseInt(editPatientPriority.getText().trim()), time));
            time++;
            repaint();
        }
		else if(e.getSource() == searchPatientButton) {
			boolean found = false;
			String firstName = editPatientFirstName.getText().trim();
			String lastName = editPatientLastName.getText().trim();
			for(Patient p : patients) {
				if(p.lastName.equals(lastName) && p.firstName.equals(firstName)) {
					editPatientIllness.setText(p.illness);
					editPatientPriority.setText(""+p.priority);
					editingPatient = p;
					found = true;
					break;
				}	
			}
			if(!found) {
				editPatientIllness.setText("");
				editPatientPriority.setText("");
			}
		}
        else if(e.getSource() == editPatientButton) {
			String firstName = editPatientFirstName.getText().trim();
			String lastName = editPatientLastName.getText().trim();
			String illness = editPatientIllness.getText().trim();
			int priority = Integer.parseInt(editPatientPriority.getText().trim());
			if(patients.remove(editingPatient)) {
				patients.add(new Patient(firstName, lastName, illness, priority, editingPatient.time));
			}
					
            /*PriorityQueue<Patient> temp = new PriorityQueue<Patient>();
            while(!patients.isEmpty()) {
                Patient curr = patients.poll();
                if(curr.lastName.equals(editingPatient.lastName) && curr.firstName.equals(editingPatient.firstName)) {
					editingPatient = new Patient(firstName, lastName, illness, priority, editingPatient.time);
                    temp.add(editingPatient);
                }
                else {
                    temp.add(curr);
                }
            }
            patients = temp;*/
            repaint();
        }
        addPatientButton.setVisible(false);
        scrollPaneNurse.setVisible(false);
        editPatientButton.setVisible(false);
        searchPatientButton.setVisible(false);
		editPatientFirstName.setVisible(true);
		editPatientLastName.setVisible(true);
		editPatientIllness.setVisible(true);
		editPatientPriority.setVisible(true);

    }
}
