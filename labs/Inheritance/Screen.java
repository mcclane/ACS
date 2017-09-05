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

public class Screen extends JPanel implements ActionListener {
	private JButton pButton;
	private JButton tButton;
	private JButton eButton;
	private JButton bButton;
	private JButton sButton;
	private JButton cButton;

    private JTextField search;

	private ArrayList<Employee> emps;
    private ArrayList<Employee> displayList;

    public Screen() {
		emps = new ArrayList<>();
		emps.add(new Government("Austin", "Austin.jpg", "Police Officer", "San Jose"));
		emps.add(new Government("Jennifer", "Jennifer.jpg", "Police Officer", "Oakland"));
		emps.add(new Government("Jose", "Jose.jpg", "Teacher", "Ferguson"));
		emps.add(new Government("Lashonda", "Lashonda.jpg", "Teacher", "New Orleans"));
		emps.add(new Company("McClane", "McClane.jpg", "Banker", "Deutsche Bank"));
		emps.add(new Company("Nick", "Nick.jpg", "Banker", "Wells Fargo"));
		emps.add(new Company("Sharkeisha", "Sharkeisha.jpg", "Engineer", "Uber"));
		emps.add(new Government("Wong", "Wong.jpg", "Engineer", "Tesla"));
        displayList = new ArrayList<Employee>();
        for(Employee e : emps) {
            displayList.add(e);
        }
		int x = 100;
		int y = 100;
		for(Employee emp : displayList) {
			JButton temp = new JButton("Delete");
			this.add(temp);
            temp.setVisible(false);
			temp.addActionListener(this);
            emp.setDeleteButton(temp);
		}
        this.setLayout(null);
        
        sButton = new JButton("Search");
		sButton.setBounds(150,50, 100, 30); //sets the location and size
		sButton.addActionListener(this); //add the listener
		this.add(sButton); //add to JPanel

		pButton = new JButton("Police Officers");
		pButton.setBounds(250,50, 100, 30); //sets the location and size
		pButton.addActionListener(this); //add the listener
		this.add(pButton); //add to JPanel

		tButton = new JButton("Teachers");
		tButton.setBounds(350,50, 100, 30); //sets the location and size
		tButton.addActionListener(this); //add the listener
		this.add(tButton); //add to JPanel

		bButton = new JButton("Bankers");
		bButton.setBounds(450,50, 100, 30); //sets the location and size
		bButton.addActionListener(this); //add the listener
		this.add(bButton); //add to JPanel

		eButton = new JButton("Engineers");
		eButton.setBounds(550,50, 100, 30); //sets the location and size
	    eButton.addActionListener(this); //add the listener
		this.add(eButton); //add to JPanel

		cButton = new JButton("Clear Filter");
		cButton.setBounds(650,50, 200, 30); //sets the location and size
	    cButton.addActionListener(this); //add the listener
		this.add(cButton); //add to JPanel
	 
		//TextField
		search = new JTextField(20);
		search.setBounds(50,50, 80, 30);
		this.add(search);
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,600);
    }
    public void paintComponent(Graphics g) {
		//draw background
        g.setColor(Color.white);
        g.fillRect(0,0,800,600);
        
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.red);
		int x = 150;
		int y = 100;
        for(Employee emp : emps) {
            emp.getDeleteButton().setVisible(false);
        }
		for(Employee emp : displayList) {

			emp.getDeleteButton().setBounds(550,y,100,30);
            emp.getDeleteButton().setVisible(true);

			g.drawString(emp.toString(), x, y+10);
			emp.drawPhoto(g, x-50, y);
			y += 50;
		}

    }
    public void updateDisplayList(String query) {
        displayList.clear();
        for(Employee emp : emps) {
            if(emp.getJobTitle().equals(query) || emp.getName().equals(query)) {
                displayList.add(emp);
            }
        }
    }
    public void updateDisplayList() {
        displayList.clear();
        for(Employee emp : emps) {
            displayList.add(emp);
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pButton) { 
            updateDisplayList("Police Officer");
        }
        else if (e.getSource() == tButton) { 
            updateDisplayList("Teacher");
        }
        else if (e.getSource() == eButton) { 
            updateDisplayList("Engineer");
        }
        else if (e.getSource() == bButton) { 
            updateDisplayList("Banker");
        }
        else if(e.getSource() == cButton) {
            updateDisplayList();
        }
        else if(e.getSource() == sButton) {
            updateDisplayList(search.getText());
        }
        else {
            for(int i = 0;i < displayList.size(); i++) {
                if(displayList.get(i).getDeleteButton() == e.getSource()) {
                    emps.remove(displayList.get(i));
                    displayList.remove(i);
                    i--;
                }
            }
        }
        repaint();
    }
}
