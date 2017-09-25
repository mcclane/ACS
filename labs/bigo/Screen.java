import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Screen extends JPanel implements ActionListener {
	
	private JTextField search;
	private JButton binaryButton;
	private JButton linearButton;
	
	private JTextField addStudentField;
	private JButton addButton;
	private JButton deleteButton;
	
	private JTextArea textArea;
	private String displayText = "";
	
	private ArrayList<Student> students;
	private String selectedStudent = "";
	
	Search s;
	private int passes;
	
    public Screen() {
        this.setLayout(null);
		//read in the file
		students = new ArrayList<Student>();
		try {
			Scanner scan = new Scanner(new File("names.txt"));
			while(scan.hasNext()) {
				ListIterator<Student> lit = students.listIterator();
				String fname = scan.next();
				String lname = scan.next();
				boolean added = false;
				while(lit.hasNext()) {
					if(lname.compareTo(lit.next().getLastName()) < 0) {
						lit.previous();
						lit.add(new Student(fname, lname, (int)(Math.random()*4+14)));
						added = true;
						break;
					}
				}
				if(added == false) {
					students.add(new Student(fname, lname, (int)(Math.random()*4+14)));
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
        //Contact Info
        //Binary button
		binaryButton = new JButton("Binary Search");
		binaryButton.setBounds(350, 50, 200, 30); //sets the location and size
	    binaryButton.addActionListener(this); //add the listener
		this.add(binaryButton); //add to JPanel
		//linear button
		linearButton = new JButton("Linear Search");
		linearButton.setBounds(550, 50, 200, 30); //sets the location and size
	    linearButton.addActionListener(this); //add the listener
		this.add(linearButton); //add to JPanel
		//add button
		addButton = new JButton("Add Student");
		addButton.setBounds(550, 500, 200, 30); //sets the location and size
	    addButton.addActionListener(this); //add the listener
		this.add(addButton); //add to JPanel
		//delete button
		deleteButton = new JButton("Delete Student");
		deleteButton.setBounds(350, 500, 200, 30);
		deleteButton.addActionListener(this);
		this.add(deleteButton);
		//add student textfield
		addStudentField = new JTextField();
		addStudentField.setBounds(50,500, 300, 30);
		this.add(addStudentField);
		//Search TextField
		search = new JTextField();
		search.setBounds(50,50, 300, 30);
		this.add(search);
		
        //TextArea
        textArea = new JTextArea(200,250); //sets the location and size
		updateText();
        //textArea.setText(displayText);
		//ListIterator<Student> lit = students.listIterator();
		//int i = 0;
		//while(lit.hasNext()) {
		//	i++;
		//	displayText += " "+i+".\t"+lit.next().toString();
		//}
		//textArea.setText(displayText);
         
        //JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(800,50,300,700);
 
		s = new Search();
		
        this.add(scrollPane);
		
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1500,800);
    }
    public void paintComponent(Graphics g) {
		//draw background
        g.setColor(Color.black);
        g.fillRect(0,0,1500,800);
		
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.white);
		
		g.drawString("Enter a name to search", 50, 30);
		g.drawString("Enter firstname lastname age to add/delete student", 50, 480);
		
		g.drawString(selectedStudent, 200, 200);
    }
	public void updateText() {
		displayText = "";
		ListIterator<Student> lit = students.listIterator();
		int i = 0;
		while(lit.hasNext()) {
			i++;
			displayText += " "+i+".\t"+lit.next().toString();
		}
		textArea.setText(displayText);
	}
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == binaryButton) {
			s.passes = 0;
			int index = s.binarySearch(students, search.getText().trim(), 0, students.size()-1);
			if(index != -1) {
				selectedStudent = students.get(index).toString()+". Found in "+s.passes+" passes";
			}
			else {
				selectedStudent = "Not Found, Try Again";
			}
		}
		else if(e.getSource() == linearButton) {
			s.passes = 0;
			int index = s.linearSearch(students, search.getText());
			if(index != -1) {
				selectedStudent = students.get(index).toString()+". Found in "+s.passes+" passes";
			}
			else {
				selectedStudent = "Not Found, Try Again";
			}
		}
		else if(e.getSource() == addButton) {
			ListIterator<Student> lit = students.listIterator();
			String[] split = addStudentField.getText().trim().split(" ");
			String fname = split[0];
			String lname = split[1];
			int age = Integer.parseInt(split[2]);
			boolean added = false;
			while(lit.hasNext()) {
				if(lname.compareTo(lit.next().getLastName()) < 0) {
					lit.previous();
					lit.add(new Student(fname, lname, age));
					added = true;
					break;
				}
			}
			if(added == false) {
				students.add(new Student(fname, lname, age));
			}
			updateText();
		}
		else if(e.getSource() == deleteButton) {
			ListIterator<Student> lit = students.listIterator();
			String[] split = addStudentField.getText().trim().split(" ");
			String fname = split[0];
			String lname = split[1];
			int age = Integer.parseInt(split[2]);
			String toString = lname+", "+fname+" - "+age+"\n";
			while(lit.hasNext()) {
				if(toString.equals(lit.next().toString())) {
					lit.remove();
					break;
				}
			}
			updateText();
		}
        repaint();
    }
}
class Search {
  public static int passes = 0;
  public Search() {}
  public int binarySearch(ArrayList<Student> list, String value, int start_pos, int end_pos) {
	if(start_pos <= end_pos) {
		int mid_pos= (start_pos + end_pos)/2;
		passes++;
		if(list.get(mid_pos).getLastName().equals(value)) {
		  //System.out.println("found");
		  return mid_pos;
		}
		else if(value.compareTo(list.get(mid_pos).getLastName()) < 0) {
		  //search bottom half
		  //System.out.println("split");
		  return binarySearch(list, value, start_pos, mid_pos-1);
		}
		else if(value.compareTo(list.get(mid_pos).getLastName()) > 0) {
		  //search upper half
		  //System.out.println("split");
		  return binarySearch(list, value, mid_pos+1, end_pos);
		}
	}
	return -1;
  }
  public int linearSearch(ArrayList<Student> list, String value) {
	  for(int i = 0;i < list.size();i++) {
		  passes++;
		  if(list.get(i).getLastName().equals(value)) {
			  return i;
		  }
	  }
	  return -1;
  }
}

