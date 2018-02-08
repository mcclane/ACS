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
    
    private JTextField enterCountryAbbreviation;
    private JButton submitCountryAbbreviation;
    private JButton backToList;
        
    private JTextArea countriesTextArea;
    private String countriesTextAreaDisplayText = "";
    private JScrollPane countriesScrollPane;

    private int tab = 0;
    private DLList<MyImage> currentImages;
    private Country currentCountry;

    MyHashMap<Country, MyImage> countries;
    
    public Screen() {
        this.setLayout(null);
        setFocusable(true);

        countries = new MyHashMap<Country, MyImage>();
        String[] splitted;

        //read in the file
        try {
            Scanner scan = new Scanner(new File("countries.txt"));
            int i = 1;
            while(scan.hasNext()) {
                splitted = scan.nextLine().split(",");
                countriesTextAreaDisplayText += splitted[1]+" - "+splitted[0]+"\n";
                countries.put(new Country(splitted[0], splitted[1]), null);
                i++;
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // buttons
        submitCountryAbbreviation = new JButton("Submit");
        submitCountryAbbreviation.setBounds(400, 200, 200, 30); //sets the location and size
        submitCountryAbbreviation.addActionListener(this); //add the listener
        this.add(submitCountryAbbreviation); //add to JPanel

        backToList = new JButton("Back to Countries List");
        backToList.setBounds(0, 0, 300, 30); //sets the location and size
        backToList.addActionListener(this); //add the listener
        this.add(backToList); //add to JPanel
        backToList.setVisible(false);
        
        // textFields
        enterCountryAbbreviation = new JTextField(2);
        enterCountryAbbreviation.setBounds(400, 100, 200, 30);
        this.add(enterCountryAbbreviation);
        
        // TextAreas
        countriesTextArea = new JTextArea(200,250); //sets the location and size
        countriesTextArea.setText(countriesTextAreaDisplayText);
        countriesTextArea.setEditable(false);
         
        // JScrollPane
        countriesScrollPane = new JScrollPane(countriesTextArea); 
        countriesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        countriesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        countriesScrollPane.setBounds(50,50,320,700);
        this.add(countriesScrollPane);

    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1000,800);
    }
    public void paintComponent(Graphics g) {
        // draw background
        g.setColor(Color.white);
        g.fillRect(0,0,1000,800);
        
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.black);

        //countriesScrollPane.setVisible(false);
        //submitCountryAbbreviation.setVisible(false);
        //enterCountryAbbreviation.setVisible(false);
        if(tab == 0) {
            countriesScrollPane.setVisible(true);
            submitCountryAbbreviation.setVisible(true);
            enterCountryAbbreviation.setVisible(true);
            countriesTextArea.setVisible(true);
            g.drawString("Enter country abbreviation", 400, 50);
        }
        else if(tab == 1) {
            countriesScrollPane.setVisible(false);
            submitCountryAbbreviation.setVisible(false);
            enterCountryAbbreviation.setVisible(false);
            countriesTextArea.setVisible(false);

            backToList.setVisible(true);
            g.drawString(currentCountry.toString(), 400, 50);
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitCountryAbbreviation) {
            currentImages = countries.get(new Country(enterCountryAbbreviation.getText(), null));
            for(int i = 0;i < countries.keySet().size();i++) {
                if(countries.keySet().get(i).hashCode() == new Country(enterCountryAbbreviation.getText(), null).hashCode()) {
                    currentCountry = countries.keySet().get(i);
                    tab = 1;
                    System.out.println(currentCountry);
                    
                    break;
                }
            }
        }
        else if(e.getSource() == backToList) {
            tab = 0;
        }
        repaint();
    }
}