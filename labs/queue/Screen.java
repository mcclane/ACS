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
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

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

    //billing stuff
    private JTextField billingAmountField;
    private JButton enterBillButton;

    //admin stuff
    private JTextArea adminTextArea;
    private JScrollPane adminScrollPane;
    
    private int time = 6;
	
	private Patient editingPatient;
    
    PriorityQueue<Patient> patients;
    Queue<Patient> billing;
    TreeSet<Patient> records;

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
        addPatientButton = new JButton("Add Patient");
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
        nurseTextArea.setEditable(false);
        scrollPaneNurse = new JScrollPane(nurseTextArea); 
        scrollPaneNurse.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneNurse.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneNurse.setBounds(800,400,300,500);
        this.add(scrollPaneNurse);
        scrollPaneNurse.setVisible(false);

		//doctor stuff
		dischargePatientButton = new JButton("Discharge Patient");
        dischargePatientButton.setBounds(500, 700, 200, 30);
        dischargePatientButton.addActionListener(this);
        this.add(dischargePatientButton);
        dischargePatientButton.setVisible(false);

        doctorNoteField = new JTextField(100);
        doctorNoteField.setBounds(50, 700, 450, 30);
        doctorNoteField.addActionListener(this);
        this.add(doctorNoteField);
        doctorNoteField.setVisible(false);

        //billing stuff
        billingAmountField = new JTextField(100);
        billingAmountField.setBounds(50, 700, 450, 30);
        this.add(billingAmountField);
        billingAmountField.setVisible(false);

        enterBillButton = new JButton("Enter");
        enterBillButton.setBounds(500, 700, 200, 30);
        enterBillButton.addActionListener(this);
        this.add(enterBillButton);
        enterBillButton.setVisible(false);

        //admin stuff
        adminTextArea = new JTextArea(200, 250);
        adminTextArea.setEditable(false);
        adminScrollPane = new JScrollPane(adminTextArea);
        adminScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        adminScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        adminScrollPane.setBounds(50,400,1100,500);
        this.add(adminScrollPane);
        adminScrollPane.setVisible(false);

        //queue stuff
        patients = new PriorityQueue<Patient>();
        patients.add(new Patient("Jack", "Johnson", "Herpes", 0, 0));
        patients.add(new Patient("Austin", "Pan", "Living", 1, 1));
        patients.add(new Patient("Chris", "Young", "Osteoporosis", 2, 2));
        patients.add(new Patient("Brian", "Chao", "Depression", 3, 3));
        patients.add(new Patient("David", "Sillman", "Large Mouth", 0, 4));

        billing = new LinkedList<Patient>();

        records = new TreeSet<Patient>();
        
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
        g.setColor(Color.red);
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
                g.drawString("Please make a selection", 600, 500);
                break;
        }
    }
    public void drawNurseView(Graphics g) {
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
        g.drawString("Patient: "+patients.peek().toString(), 100, 500);
        g.drawString("Enter in a doctor's note to discharge Patient", 50, 600);

        dischargePatientButton.setVisible(true);
        doctorNoteField.setVisible(true);
    }
    public void drawBillerView(Graphics g) {
        if(!billing.isEmpty()) {
            g.setColor(Color.red);
            g.drawString("Discharged Patient: "+billing.peek().toString(), 100, 500);
            g.drawString("Doctors Note: "+billing.peek().note, 100, 550);
            g.drawString("Enter billing amount", 50, 600);
            billingAmountField.setVisible(true);
            enterBillButton.setVisible(true);
        }
        else {
            g.drawString("No people to bill", 100, 500);
        }
    }
    public void drawAdminView(Graphics g) {
        adminScrollPane.setVisible(true);
        String text = "";
        Iterator<Patient> it = records.iterator();
        while(it.hasNext()) {
            text += it.next().toString()+"\n";
        }
        adminTextArea.setText(text);
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
            repaint();
        }
        else if(e.getSource() == dischargePatientButton) {
            if(doctorNoteField.getText() != "") {
                Patient inLimbo = patients.poll();
                inLimbo.note = doctorNoteField.getText();
                billing.add(inLimbo);
                doctorNoteField.setText("");
                repaint();
            }
        }
        else if(e.getSource() == enterBillButton) {
            if(billingAmountField.getText() != "") {
                Patient inFirstCircle = billing.poll();
                inFirstCircle.price = Double.parseDouble(billingAmountField.getText().trim());
                records.add(inFirstCircle);
                repaint();
            }
        }
        //nurse stuff
        addPatientButton.setVisible(false);
        scrollPaneNurse.setVisible(false);
        editPatientButton.setVisible(false);
        searchPatientButton.setVisible(false);
		editPatientFirstName.setVisible(false);
		editPatientLastName.setVisible(false);
		editPatientIllness.setVisible(false);
		editPatientPriority.setVisible(false);
        //doctor stuff
        doctorNoteField.setVisible(false);
        dischargePatientButton.setVisible(false);
        //billing stuff
        enterBillButton.setVisible(false);
        billingAmountField.setVisible(false);
        //admin stuff
        adminScrollPane.setVisible(false);

    }
}
