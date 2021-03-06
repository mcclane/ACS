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
    private JButton addStudent;

    private JTextField addPeriod;
    private JTextField addClass;
    private JButton addClassPeriod;

    private JTextField studentName;
    private JButton submitNewStudent;

    private int selection = 0;

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
        s.addClass(1, "Space Camp");
        s.addClass(2, "Chinese Studies");
        s.addClass(3, "Advanced Computer Science");
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
		addClassPeriod.setBounds(450, 300, 200, 30); //sets the location and size
	    addClassPeriod.addActionListener(this); //add the listener
		this.add(addClassPeriod); //add to JPanel
        addClassPeriod.setVisible(false);

		addStudent = new JButton("Add Student");
		addStudent.setBounds(450, 50, 200, 30); //sets the location and size
	    addStudent.addActionListener(this); //add the listener
		this.add(addStudent); //add to JPanel
        addStudent.setVisible(true);

		submitNewStudent = new JButton("Submit Student");
		submitNewStudent.setBounds(250, 450, 300, 30); //sets the location and size
	    submitNewStudent.addActionListener(this); //add the listener
		this.add(submitNewStudent); //add to JPanel
        submitNewStudent.setVisible(false);

		//TextField
		search = new JTextField(20);
		search.setBounds(50,50, 80, 30);
		this.add(search);

        addPeriod = new JTextField(20);
		addPeriod.setBounds(450,250, 50, 30);
		this.add(addPeriod);
        addPeriod.setVisible(false);
       
        addClass = new JTextField(20);
		addClass.setBounds(510, 250, 200, 30);
		this.add(addClass);
        addClass.setVisible(false);

        studentName = new JTextField(20);
		studentName.setBounds(250,350, 50, 30);
		this.add(studentName);
        studentName.setVisible(false);
 
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
        if(selection == 1) {
            studentName.setVisible(false);
            submitNewStudent.setVisible(false);
 
            //draw the student
            selectedStudent.getKey().drawStudent(g, x, y);
            String lines[] = selectedStudent.getValue().toString().split("\\n");
            for(String l : lines) {
                g.drawString(l, x+110, y+30);
                y += 20;
            }

            //set the textfields and jbuttons to visible
            addClass.setVisible(true);
            addPeriod.setVisible(true);
            addClassPeriod.setVisible(true);
        }
        else if(selection == 2) {
            studentName.setVisible(true);
            submitNewStudent.setVisible(true);
        }
        else {
            studentName.setVisible(false);
            submitNewStudent.setVisible(false);
            addClass.setVisible(false);
            addPeriod.setVisible(false);
            addClassPeriod.setVisible(false);
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
                    selection = 1;
                    System.out.println("selected");
                    break;
                }
            }
        }
        else if(e.getSource() == clear) {
            selection = 0;
            System.out.println("cleared");
        }
        else if(e.getSource() == addClassPeriod) {
            selectedStudent.getValue().addClass(Integer.parseInt(addPeriod.getText()), addClass.getText());
        }
        else if(e.getSource() == addStudent) {
            selection = 2;
        }
        else if(e.getSource() == submitNewStudent) {
            sl.add(new Pair<Student, Schedule>(new Student(studentName.getText(), "Jennifer.jpg"), new Schedule()));
        }
        repaint();
    }
}

