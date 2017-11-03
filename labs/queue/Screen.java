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
    private JTextField searchPatient;
    private JTextField addPatientField;
    private JTextArea nurseTextArea;
    private JScrollPane scrollPaneNurse;
    private JButton editPatientButton;
    private JTextField editPatientName;
    private JTextField editPatientIllness;
    private JTextField editPatientPriority;
    
    private Color colorOne;
    private Color colorTwo;
    
    private int time = 0;
    
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
        addPatientButton = new JButton("Add Patient");
        addPatientButton.setBounds(50, 700, 200, 30);
        addPatientButton.addActionListener(this);
        this.add(addPatientButton);
        addPatientButton.setVisible(false);
        
        editPatientButton = new JButton("edit patient");
        editPatientButton.setBounds(250, 700, 200, 30);
        editPatientButton.addActionListener(this);
        this.add(editPatientButton);
        editPatientButton.setVisible(false);
        
        addPatientField = new JTextField(100);
        addPatientField.setBounds(50,600, 300, 30);
        addPatientField.addActionListener(this);
        this.add(addPatientField);
        addPatientField.setVisible(false);
        
        editPatientName = new JTextField(100);
        editPatientName.setBounds(50,750, 300, 30);
        editPatientName.addActionListener(this);
        this.add(editPatientName);
        editPatientName.setVisible(false);
        
        editPatientIllness = new JTextField(100);
        editPatientIllness.setBounds(50,780, 300, 30);
        editPatientIllness.addActionListener(this);
        this.add(editPatientIllness);
        editPatientIllness.setVisible(false);
 
        editPatientPriority = new JTextField(100);
        editPatientPriority.setBounds(50,810, 300, 30);
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
        
        patients = new PriorityQueue<Patient>();
        patients.add(new Patient("Jack", "Johnson", "Herpes", 0, 0));
        patients.add(new Patient("Austin", "Pan", "Living", 1, 1));
        patients.add(new Patient("Chris", "Young", "Osteoporosis", 2, 2));
        patients.add(new Patient("Brian", "Chao", "Depression", 3, 3));
        patients.add(new Patient("David", "Sillman", "Large Mouth", 0, 4));
        
        colorOne = Color.black;
        colorTwo = Color.white;
        
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
        //drawBackground(g, colorOne, colorTwo);
        
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
        g.drawString("Enter in firstname, lastname, illness, priority(2/1/0)", 50, 550);
        addPatientButton.setVisible(true);
        addPatientField.setVisible(true);
        scrollPaneNurse.setVisible(true);
        editPatientButton.setVisible(true);
        
        String text = "";
        for(Patient p : patients) {
            text += p+"\n";
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
            String[] splitted = addPatientField.getText().split(",");
            patients.add(new Patient(splitted[0].trim(), splitted[1].trim(), splitted[2].trim(), Integer.parseInt(splitted[3].trim()), time));
            time++;
            repaint();
        }
        else if(e.getSource() == editPatientButton) {
            String[] splitted = addPatientField.getText().split(",");
            Patient editedPatient = new Patient(splitted[0].trim(), splitted[1].trim(), splitted[2].trim(), Integer.parseInt(splitted[3].trim()), time);
            PriorityQueue<Patient> temp = new PriorityQueue<Patient>();
            while(!patients.isEmpty()) {
                Patient curr = patients.poll();
                if(curr.lastName.equals(editedPatient.lastName) && curr.firstName.equals(editedPatient.firstName)) {
                    temp.add(editedPatient);
                }
                else {
                    temp.add(curr);
                }
            }
            System.out.println(temp);
            patients = temp;
            repaint();
        }
        addPatientButton.setVisible(false);
        addPatientField.setVisible(false);
        scrollPaneNurse.setVisible(false);
        editPatientButton.setVisible(false);
    }
}
