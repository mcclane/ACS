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

    private JTextField search;
	private JButton select;
	private JButton clear;

    private JTextField addPeriod;
    private JTextField addClass;
    private JButton addClassPeriod;

    private boolean selected;

    ArrayList<Pair<Student, Schedule>> sl;
    Pair<Student, Schedule> selectedStudent;

    public Screen() {

        this.setLayout(null);

        //set up students
        sl = new ArrayList<Pair<Student, Schedule>>();
        Schedule s = new Schedule();
        s.addClass(1, "Geometry");
        s.addClass(2, "P.E.");
        s.addClass(3, "US History");
        sl.add(new Pair<Student, Schedule>(new Student("Jose", "Jose.jpg"), s));
        s = new Schedule();
        s.addClass(1, "Algebra");
        s.addClass(2, "Civics");
        s.addClass(3, "Stats");
        sl.add(new Pair<Student, Schedule>(new Student("Austin", "Austin.jpg"), s));
        s = new Schedule();
        s.addClass(1, "Multivariable Calculus");
        s.addClass(2, "AP Chemistry");
        s.addClass(3, "Advanced Computer Science");
        sl.add(new Pair<Student, Schedule>(new Student("McClane", "McClane.jpg"), s));
        
        //JButton
		select = new JButton("Select Student");
		select.setBounds(250, 50, 100, 30); //sets the location and size
	    select.addActionListener(this); //add the listener
		this.add(select); //add to JPanel

		clear = new JButton("Clear");
		clear.setBounds(350, 50, 100, 30); //sets the location and size
	    clear.addActionListener(this); //add the listener
		this.add(clear); //add to JPanel

		addClassPeriod = new JButton("addClassPeriod");
		addClassPeriod.setBounds(350, 50, 100, 30); //sets the location and size
	    addClassPeriod.addActionListener(this); //add the listener
		this.add(addClassPeriod); //add to JPanel
        addClassPeriod.setVisible(false);


		//TextField
		search = new JTextField(20);
		search.setBounds(50,50, 80, 30);
		this.add(search);

        addPeriod = new JTextField(20);
		addPeriod.setBounds(50,50, 80, 30);
		this.add(addPeriod);
        addPeriod.setVisible(false);
        
        addClass = new JTextField(20);
		addClass.setBounds(50,50, 80, 30);
		this.add(addClass);
        addClass.setVisible(false);


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
        int y = 200;
        if(selected) {
            selectedStudent.getKey().drawStudent(g, x, y);
            String lines[] = selectedStudent.getValue().toString().split("\\n");
            for(String l : lines) {
                g.drawString(l, x+110, y+30);
                y += 20;
            }
        }
        else {
            for(Pair<Student, Schedule> ss : sl) {
                ss.getKey().drawStudent(g, x, y);
                y += 100;
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == select) { 
            String text = search.getText();
            for(Pair<Student, Schedule> ss: sl) {
                if(ss.getKey().toString().equals(text)) {
                    selectedStudent = ss;
                    selected = true;
                    System.out.println("selected");
                    break;
                }
            }
        }
        else if(e.getSource() == clear) {
            selected = false;
            System.out.println("cleared");
        }
        repaint();
    }
}
