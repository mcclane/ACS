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
	private ArrayList<JButton> empsDeleteButtons;
    private JTextField search;
	private ArrayList<Employee> emps;

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
		empsDeleteButtons = new ArrayList<JButton>();
		int x = 100;
		int y = 100;
		for(Employee emp : emps) {
			JButton temp = new JButton("Delete");
			temp.setBounds(x,y,100,30);
			temp.addActionListener(this);
			this.add(temp);
			empsDeleteButtons.add(temp);
			y += 50;
		}
        this.setLayout(null);
		pButton = new JButton("Show Police Officers");
		pButton.setBounds(100,170, 200, 30); //sets the location and size
		pButton.addActionListener(this); //add the listener
		this.add(pButton); //add to JPanel
	 
		//TextField
		search = new JTextField(20);
		search.setBounds(100,50, 80, 30);
		this.add(search);
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
		int x = 250;
		int y = 100;
		for(Employee emp : emps) {
			g.drawString(emp.toString(), x, y+10);
			emp.drawPhoto(g, x-50, y);
			y += 50;
		}

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pButton) { 
            System.out.println("police");
            repaint();
        }
    }
}
