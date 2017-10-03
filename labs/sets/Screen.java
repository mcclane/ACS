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
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Set;
import java.util.HashSet;

public class Screen extends JPanel implements ActionListener {
	
	private JTextField search;
    private JButton submitSearch;
	private JButton displayDuplicates;
    private JButton displayAll;
    private JButton displayAUnique;
    private JButton displayBUnique;
    private JButton addA;
    private JButton addB;
    private JTextField add;
    
    Set<Item> A, B;
    
    private JTextArea textAreaA, textAreaB, textAreaR;
	private String displayTextA = ""; 
    private String displayTextB = "";
    private String displayTextR = "";
	
    public Screen() {
        this.setLayout(null);
		//read in the file
        A = new HashSet<Item>();
        B = new HashSet<Item>();
		try {
			Scanner scan = new Scanner(new File("StoreA.txt"));
            int i = 1;
			while(scan.hasNext()) {
				String[] split = scan.nextLine().split(",");    
                Item temp = new Item(split[0], Integer.parseInt(split[1].trim()));
                if(A.add(temp)) {
                    displayTextA += i+". "+temp+"\n";
                    i++;
                }
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
        try {
			Scanner scan = new Scanner(new File("StoreB.txt"));
            int i = 1;
			while(scan.hasNext()) {
				String[] split = scan.nextLine().split(",");    
                Item temp = new Item(split[0], Integer.parseInt(split[1].trim()));
                if(B.add(temp)) {
                    displayTextB += i+". "+temp+"\n";
                    i++;
                }
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
        
        // buttons
		displayAll = new JButton("Display All");
		displayAll.setBounds(900, 200, 200, 30); //sets the location and size
	    displayAll.addActionListener(this); //add the listener
		this.add(displayAll); //add to JPanel
        
        displayDuplicates = new JButton("Display Duplicates");
		displayDuplicates.setBounds(900, 230, 200, 30); //sets the location and size
	    displayDuplicates.addActionListener(this); //add the listener
		this.add(displayDuplicates); //add to JPanel
        
        displayAUnique = new JButton("Display Unique to A");
		displayAUnique.setBounds(900, 260, 200, 30); //sets the location and size
	    displayAUnique.addActionListener(this); //add the listener
		this.add(displayAUnique); //add to JPanel
        
        displayBUnique = new JButton("Display Unique to B");
		displayBUnique.setBounds(900, 290, 200, 30); //sets the location and size
	    displayBUnique.addActionListener(this); //add the listener
		this.add(displayBUnique); //add to JPanel
                
        submitSearch = new JButton("Search");
		submitSearch.setBounds(1100, 100, 100, 30); //sets the location and size
	    submitSearch.addActionListener(this); //add the listener
		this.add(submitSearch); //add to JPanel
        
        addA = new JButton("Add to Store A");
		addA.setBounds(1000, 600, 150, 30); //sets the location and size
	    addA.addActionListener(this); //add the listener
		this.add(addA); //add to JPanel
        
        addB = new JButton("Add to Store B");
		addB.setBounds(1150, 600, 150, 30); //sets the location and size
	    addB.addActionListener(this); //add the listener
		this.add(addB); //add to JPanel  
        
        // textFields
        search = new JTextField(100);
        search.setBounds(900, 100, 200, 30);
        this.add(search);
        
        add = new JTextField(100);
        add.setBounds(800, 600, 200, 30);
        this.add(add);
		
        // TextAreas
        textAreaA = new JTextArea(200,250); //sets the location and size
		textAreaA.setText(displayTextA);
         
        textAreaB = new JTextArea(200,250); //sets the location and size
		textAreaB.setText(displayTextB);
        
        textAreaR = new JTextArea(200,250); //sets the location and size
		textAreaR.setText(displayTextR);
        
        // JScrollPane
        JScrollPane scrollPaneA = new JScrollPane(textAreaA); 
        scrollPaneA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneA.setBounds(100,50,300,700);
        this.add(scrollPaneA);
        
        JScrollPane scrollPaneB = new JScrollPane(textAreaB); 
        scrollPaneB.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneB.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneB.setBounds(500,50,300,700);
        this.add(scrollPaneB);
        
        JScrollPane scrollPaneR = new JScrollPane(textAreaR); 
        scrollPaneR.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneR.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneR.setBounds(1300,50,300,700);
        this.add(scrollPaneR);
        
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1700,800);
    }
    public void paintComponent(Graphics g) {
		// draw background
        g.setColor(Color.black);
        g.fillRect(0,0,1700,800);
		
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.white);	
        
        g.drawString("A", 200, 20);
        g.drawString("B", 600, 20);
        
        g.drawString("Enter name, price to search", 900, 80);
    }
    public void actionPerformed(ActionEvent e) {
        Set<Item> all = new HashSet<Item>();
        if(e.getSource() == displayAll) {
            displayTextR = "";
            int i = 1;
            Iterator<Item> it = A.iterator();
            while(it.hasNext()) {
                displayTextR += i+". "+it.next()+"\n";
                i++;
            }
            it = B.iterator();
            while(it.hasNext()) {
                Item curr = it.next();
                if(A.contains(curr))
                    continue;
                displayTextR += i+". "+curr+"\n";
                i++;
            }
            textAreaR.setText(displayTextR);
        }
        else if(e.getSource() == displayDuplicates) {
            displayTextR = "";
            int i = 1;
            Iterator<Item> it = A.iterator();
            while(it.hasNext()) {
                Item curr = it.next();
                if(B.contains(curr)) {
                    displayTextR += i+". "+curr+"\n";
                    i++;
                }
            }
            textAreaR.setText(displayTextR);
        }
        else if(e.getSource() == displayAUnique) {
            displayTextR = "";
            Iterator<Item> it = A.iterator();
            int i = 1;
            while(it.hasNext()) {
                Item curr = it.next();
                if(B.contains(curr) == false) {
                    displayTextR += i+". "+curr+"\n";
                    i++;
                }
            }
            textAreaR.setText(displayTextR);
        }
        else if(e.getSource() == displayBUnique) {
            displayTextR = "";
            Iterator<Item> it = B.iterator();
            int i = 1;
            while(it.hasNext()) {
                Item curr = it.next();
                if(A.contains(curr) == false) {
                    displayTextR += i+". "+curr+"\n";
                    i++;
                }
            }
            textAreaR.setText(displayTextR);   
        }
        else if(e.getSource() == submitSearch) {
            displayTextR = "";
            System.out.println(search.getText());
            String[] split = search.getText().split(",");
            Item temp = new Item(split[0], Integer.parseInt(split[1].trim()));
            if(A.contains(temp)) {
                displayTextR += "Store A: "+temp+"\n";
            }
            if(B.contains(temp)) {
                displayTextR += "Store B: "+temp+"\n";
            }
            textAreaR.setText(displayTextR);
        }
        else if(e.getSource() == addA) {
            String[] tba = add.getText().split(",");
            Item temp = new Item(tba[0], Integer.parseInt(tba[1].trim()));
            if(A.add(temp)) {
                displayTextA += A.size()+". "+temp+"\n";
            }
            textAreaA.setText(displayTextA);
        }
        else if(e.getSource() == addB) {
            String[] tba = add.getText().split(",");
            Item temp = new Item(tba[0], Integer.parseInt(tba[1].trim()));
            if(B.add(temp)) {
                displayTextB += B.size()+". "+temp+"\n";
            }
            textAreaB.setText(displayTextB);
        }
        repaint();
    }
}
