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

public class Screen extends JPanel implements ActionListener {
    private JButton v1, v2, v3, v4;
    int view;

    //nurse stuff
    private JButton addPatientButton;
    private JTextField searchPatient;
    private JTextField addPatientField;
    
    private Color colorOne;
    private Color colorTwo;
    
    PriorityQueue<Patient> patients;
    public Screen() {
        this.setLayout(null);

        v1 = new JButton("Nurse");
        v1.setBounds((int)(Math.random()*1000), (int)(Math.random()*900), 200, 30);
        v1.addActionListener(this);
        this.add(v1);
        v2 = new JButton("Doctor");
        v2.setBounds((int)(Math.random()*1000), (int)(Math.random()*900), 200, 30);
        v2.addActionListener(this);
        this.add(v2);
        v3 = new JButton("Biller");
        v3.setBounds((int)(Math.random()*1000), (int)(Math.random()*900), 200, 30);
        v3.addActionListener(this);
        this.add(v3);
        v4 = new JButton("Admin");
        v4.setBounds((int)(Math.random()*1000), (int)(Math.random()*900), 200, 30);
        v4.addActionListener(this);
        this.add(v4);
        
        //nurse stuff
        addPatientButton = new JButton("Add Patient");
        addPatientButton.setBounds((int)(Math.random()*500), (int)(Math.random()*400+500), 200, 30);
        addPatientButton.addActionListener(this);
        this.add(addPatientButton);
        addPatientButton.setVisible(false);
        
        patients = new PriorityQueue<Patient>();
        patients.add(new Patient("Jack", "Johnson", "Herpes", 0, 0));
        patients.add(new Patient("Austin", "Pan", "Living", 0, 1));
        patients.add(new Patient("Chris", "Young", "Osteoporosis", 0, 2));
        patients.add(new Patient("Brian", "Chao", "Depression", 0, 3));
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
        drawBackground(g, colorOne, colorTwo);
        
        //nurse stuff
        addPatientButton.setVisible(false);
        
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
        g.drawString("Nurse View", (int)(Math.random()*400+500), (int)(Math.random()*200+300));
        addPatientButton.setVisible(true);

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
        }
        else if(e.getSource() == v2) {
            view = 2;
        }
        else if(e.getSource() == v3) {
            view = 3;
        }
        else if(e.getSource() == v4) {
            view = 4;
        }
        if(colorOne == Color.black) {
            colorOne = colorTwo;
            colorTwo = Color.black;
        }
        else if(e.getSource() == addPatientButton) {
            
        }
        else {
            colorOne = colorTwo;
            colorTwo = Color.white;
        }
        v1.setBounds((int)(Math.random()*1000), (int)(Math.random()*300), 200, 30);
        v2.setBounds((int)(Math.random()*1000), (int)(Math.random()*300), 200, 30);
        v3.setBounds((int)(Math.random()*1000), (int)(Math.random()*300), 200, 30);
        v4.setBounds((int)(Math.random()*1000), (int)(Math.random()*300), 200, 30);
        addPatientButton.setBounds((int)(Math.random()*500), (int)(Math.random()*400+500), 200, 30);
        
        repaint();
    }
}
