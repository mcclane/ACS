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
import java.util.HashMap;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class Screen extends JPanel implements ActionListener, DocumentListener {
    
    private JTextArea textAreaInput;
    
    private JTextArea textAreaOutput;
    private String displayOutputString;
    
    private JTextArea textAreaStoE;
    private String displayStoEString;
    
    private JButton addButton;
    private JTextField addField;
    
    private TreeMap<String, String> StoE;
    
    public Screen() {
        this.setLayout(null);
        textAreaInput = new JTextArea(200, 250); //sets the location and size
        textAreaInput.getDocument().addDocumentListener(this);
        textAreaInput.setLineWrap(true);

        textAreaOutput = new JTextArea(200, 250);
        textAreaOutput.setEditable(false);
        textAreaOutput.setLineWrap(true);
        
        textAreaStoE = new JTextArea(200, 250);
        textAreaStoE.setEditable(false);
        textAreaStoE.setLineWrap(true);
        
        JScrollPane scrollPaneInput = new JScrollPane(textAreaInput); 
        scrollPaneInput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneInput.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneInput.setBounds(0,50,300,400);
        this.add(scrollPaneInput);
        
        JScrollPane scrollPaneOutput = new JScrollPane(textAreaOutput); 
        scrollPaneOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneOutput.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneOutput.setBounds(300,50,300,400);
        this.add(scrollPaneOutput);
        
        JScrollPane scrollPaneStoE = new JScrollPane(textAreaStoE); 
        scrollPaneStoE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneStoE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneStoE.setBounds(600,50,300,400);
        this.add(scrollPaneStoE);
        
        addButton = new JButton("Add word");
        addButton.setBounds(300, 500, 200, 30);
        addButton.addActionListener(this);
        this.add(addButton);
        
        addField = new JTextField();
        addField.setBounds(100, 500, 200, 30);
        this.add(addField);
        
        StoE = new TreeMap<String, String>();
        // read in the file
        try {
			Scanner scan = new Scanner(new File("spantoeng.txt"));
            int i = 1;
			while(scan.hasNext()) {
				String[] split = scan.nextLine().split(" ");    
                StoE.put(split[0], split[1]);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
        updateStoE();
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1000,800);
    }
    public void paintComponent(Graphics g) {
		// draw background
        g.setColor(Color.black);
        g.fillRect(0,0,1000,800);
	
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Enter new words in format \"Spanish=English\"", 100, 500);
    }
    public void updateText() {
        String[] words = textAreaInput.getText().split(" ");
        String translatedWords = "";
        for(String w : words) {
            if(StoE.containsKey(w.trim())) {
                translatedWords += StoE.get(w.trim())+" ";
                if(w.contains("\n")) {
                    translatedWords += "\n";
                }
            }
            else {
                translatedWords += w+" ";
            }
        }
        textAreaOutput.setText(translatedWords);
    }
    public void updateStoE() {
        displayStoEString = "";
        for(String spanish : StoE.keySet()) {
            displayStoEString += spanish+"="+StoE.get(spanish)+"\n";
        }
        textAreaStoE.setText(displayStoEString);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton) {
            String[] splitted = addField.getText().split("=");
            StoE.put(splitted[0], splitted[1]);
            updateStoE();
        }
    }
    public void insertUpdate(DocumentEvent e) {
        updateText();
    }
    public void removeUpdate(DocumentEvent e) {
        updateText();
    }
    public void changedUpdate(DocumentEvent e) {
        updateText();
    }
}
