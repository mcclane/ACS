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
import java.util.ArrayList;

public class Screen extends JPanel implements ActionListener {
    
    private JTextField enterCountryAbbreviation;
    private JButton submitCountryAbbreviation;
    private JButton navList;
    private JButton navOverview;
    private JButton nextPicture;
    private JButton prevPicture;
    private JButton deletePicture;
        
    private JTextArea countriesTextArea;
    private String countriesTextAreaDisplayText = "";
    private JScrollPane countriesScrollPane;

    private int tab = 0;
    private DLList<MyImage> currentImages;
    private int currentImageIndex = 0;
    private Country currentCountry;
    
    private ArrayList<JButton> countryButtons;
    
    MyHashMap<Country, DLList<MyImage>> countries;
    MyHashMap<String, String> names;

    public Screen() {
        this.setLayout(null);
        setFocusable(true);

        countries = new MyHashMap<Country, DLList<MyImage>>();
        countryButtons = new ArrayList<JButton>();
        names = new MyHashMap<String, String>();
        String[] splitted;
        Country nc;
        JButton tempButton;

        //read in the file
        try {
            Scanner scan = new Scanner(new File("countries.txt"));
            int i = 1;
            while(scan.hasNext()) {
                splitted = scan.nextLine().split(",");
                nc = new Country(splitted[0], splitted[1]);
                countriesTextAreaDisplayText += splitted[1]+" - "+splitted[0]+"\n";
                countries.put(nc, null);
                names.put(splitted[0], splitted[1]);
                i++;
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        DLList<MyImage> temp = new DLList<MyImage>();
        temp.add(new MyImage("https://upload.wikimedia.org/wikipedia/commons/2/25/Ascension_island_be.png", "A nice mountain", "6/4/17"));        
        temp.add(new MyImage("https://i1.wp.com/www.ascension-island.gov.ac/wp-content/uploads/2012/12/P1030821.jpg", "A nice town", "7/4/2013"));
        temp.add(new MyImage("https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Ascension_Island_Comfortless_Cove.jpg/220px-Ascension_Island_Comfortless_Cove.jpg", "A Nice Beach", "8/4/2013"));
        countries.put(new Country("ac", "Ascension Island"), temp);
        
        // buttons
        submitCountryAbbreviation = new JButton("Submit");
        submitCountryAbbreviation.setBounds(400, 200, 200, 30); //sets the location and size
        submitCountryAbbreviation.addActionListener(this); //add the listener
        this.add(submitCountryAbbreviation); //add to JPanel

        navList = new JButton("Countries List");
        navList.setBounds(0, 0, 300, 30); //sets the location and size
        navList.addActionListener(this); //add the listener
        this.add(navList); //add to JPanel
        
        navOverview = new JButton("Overview Tab");
        navOverview.setBounds(300, 0, 300, 30); //sets the location and size
        navOverview.addActionListener(this); //add the listener
        this.add(navOverview); //add to JPanel
        
        nextPicture = new JButton("Next Picture");
        nextPicture.setBounds(800, 400, 200, 30); //sets the location and size
        nextPicture.addActionListener(this); //add the listener
        this.add(nextPicture); //add to JPanel
        prevPicture = new JButton("Previous Picture");
        prevPicture.setBounds(0, 400, 200, 30); //sets the location and size
        prevPicture.addActionListener(this); //add the listener
        this.add(prevPicture); //add to JPanel
        deletePicture = new JButton("Delete");
        deletePicture.setBounds(400, 700, 200, 30); //sets the location and size
        deletePicture.addActionListener(this); //add the listener
        this.add(deletePicture); //add to JPanel
        nextPicture.setVisible(false);
        prevPicture.setVisible(false);
        deletePicture.setVisible(false);
        
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
            clearView();
            nextPicture.setVisible(true);
            prevPicture.setVisible(true);
            deletePicture.setVisible(true);
            g.drawString(currentCountry.toString(), 400, 50);
            if(countries.get(currentCountry) != null && countries.get(currentCountry).get(currentImageIndex) != null) {
                countries.get(currentCountry).get(currentImageIndex).drawMe(g, 200, 100);
            }
        }
        else if(tab == 2) {
            clearView();
            g.drawString("Overview Tab", 400, 200);
            
        }
    }
    public void clearView() {
        countriesScrollPane.setVisible(false);
        submitCountryAbbreviation.setVisible(false);
        enterCountryAbbreviation.setVisible(false);
        countriesTextArea.setVisible(false);
        nextPicture.setVisible(false);
        prevPicture.setVisible(false);
        deletePicture.setVisible(false);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitCountryAbbreviation) {
            String abbrev = enterCountryAbbreviation.getText();
            String name = names.get(abbrev);
            if(name != null) {
                currentCountry = new Country(abbrev, name);
                currentImages = countries.get(currentCountry);
                tab = 1;
            }
        }
        else if(e.getSource() == navList) {
            tab = 0;
        }
        else if(e.getSource() == navOverview) {
            tab = 2;
        }
        else if(e.getSource() == nextPicture && currentImageIndex < countries.get(currentCountry).size()-1) {
            currentImageIndex++;
        }
        else if(e.getSource() == prevPicture && currentImageIndex > 0) {
            currentImageIndex--;
        }
        else if(e.getSource() == deletePicture && countries.get(currentCountry).size() > 0) {
            countries.get(currentCountry).remove(currentImageIndex);
            currentImageIndex = 0;
        }
        repaint();
    }
}
