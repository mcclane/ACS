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
import java.util.HashMap;

public class Screen extends JPanel implements ActionListener {
    
    private JTextField enterCountryAbbreviation;
    private JButton submitCountryAbbreviation;
    private JButton navList;
    private JButton navOverview;
    private JButton nextPicture;
    private JButton prevPicture;
    private JButton deletePicture;
    private JButton addPicture;

    private JTextField addPictureURL;
    private JTextField addPictureDescription;
    private JTextField addPictureDate;
        
    private JTextArea countriesTextArea;
    private String countriesTextAreaDisplayText = "";
    private JScrollPane countriesScrollPane;

    private int tab = 0;
    private DLList<MyImage> currentImages;
    private int currentImageIndex = 0;
    private Country currentCountry;
    
    private HashMap<JButton, Country> countryButtons;
    private int overviewX = 10;
    private int overviewY = 50;
    
    MyHashMap<Country, DLList<MyImage>> countries;
    MyHashMap<String, String> names;

    public Screen() {
        this.setLayout(null);
        setFocusable(true);

        countries = new MyHashMap<Country, DLList<MyImage>>();
        countryButtons = new HashMap<JButton, Country>();
        names = new MyHashMap<String, String>();
        String[] splitted;
        Country nc;

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
        submitCountryAbbreviation.setBounds(400, 130, 200, 30); //sets the location and size
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
        nextPicture.setBounds(700, 300, 200, 30); //sets the location and size
        nextPicture.addActionListener(this); //add the listener
        this.add(nextPicture); //add to JPanel
        prevPicture = new JButton("Previous Picture");
        prevPicture.setBounds(100, 300, 200, 30); //sets the location and size
        prevPicture.addActionListener(this); //add the listener
        this.add(prevPicture); //add to JPanel
        deletePicture = new JButton("Delete");
        deletePicture.setBounds(400, 600, 200, 30); //sets the location and size
        deletePicture.addActionListener(this); //add the listener
        this.add(deletePicture); //add to JPanel
        addPicture = new JButton("Add");
        addPicture.setBounds(100, 750, 200, 30); //sets the location and size
        addPicture.addActionListener(this); //add the listener
        this.add(addPicture); //add to JPanel
        
        // textFields
        enterCountryAbbreviation = new JTextField(2);
        enterCountryAbbreviation.setBounds(400, 100, 200, 30);
        this.add(enterCountryAbbreviation);
        addPictureURL = new JTextField();
        addPictureURL.setBounds(100, 650, 200, 30);
        this.add(addPictureURL);
        addPictureDescription = new JTextField();
        addPictureDescription.setBounds(100, 680, 200, 30);
        this.add(addPictureDescription);
        addPictureDate = new JTextField();
        addPictureDate.setBounds(100, 710, 200, 30);
        this.add(addPictureDate);

        
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

        for(int i = 0;i < countries.keySet().size();i++) {
            if(countries.get(countries.keySet().get(i)) != null) {
                addOverviewCountryButton(countries.keySet().get(i));
            }
        }
        clearView();

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
        if(tab == 0) {
            countriesScrollPane.setVisible(true);
            submitCountryAbbreviation.setVisible(true);
            enterCountryAbbreviation.setVisible(true);
            countriesTextArea.setVisible(true);
            
            g.drawString("Enter country abbreviation", 400, 50);
        }
        else if(tab == 1) {
            nextPicture.setVisible(true);
            prevPicture.setVisible(true);
            deletePicture.setVisible(true);
            addPicture.setVisible(true);
            addPictureURL.setVisible(true);
            addPictureDescription.setVisible(true);
            addPictureDate.setVisible(true);
            g.drawString(currentCountry.toString(), 400, 50);
            if(countries.get(currentCountry) != null && countries.get(currentCountry).get(currentImageIndex) != null) {
                countries.get(currentCountry).get(currentImageIndex).drawMe(g, 300, 100);
            }
        }
        else if(tab == 2) {
            g.drawString("Overview", 400, 50);
            for(JButton jb : countryButtons.keySet()) {
                jb.setVisible(true);
            }
        }
    }
    public void addOverviewCountryButton(Country c) {
        JButton tempButton = new JButton(c.toString());
        tempButton.setBounds(overviewX, overviewY, 200, 30);
        tempButton.addActionListener(this);
        this.add(tempButton);
        tempButton.setVisible(false);
        countryButtons.put(tempButton, c);
        overviewY += 30;
        if(overviewY >= 770) {
            overviewY = 50;
            overviewX += 200;
        }
    }
    public void clearView() {

        // tab 0
        countriesScrollPane.setVisible(false);
        submitCountryAbbreviation.setVisible(false);
        enterCountryAbbreviation.setVisible(false);
        countriesTextArea.setVisible(false);

        // tab 1
        nextPicture.setVisible(false);
        prevPicture.setVisible(false);
        deletePicture.setVisible(false);

        addPicture.setVisible(false);
        addPictureURL.setVisible(false);
        addPictureDescription.setVisible(false);
        addPictureDate.setVisible(false);

        // tab 2
        for(JButton jb : countryButtons.keySet()) {
            jb.setVisible(false);
        }

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitCountryAbbreviation) {
            String abbrev = enterCountryAbbreviation.getText();
            String name = names.get(abbrev);
            if(name != null) {
                currentCountry = new Country(abbrev, name);
                currentImages = countries.get(currentCountry);
                tab = 1;
                clearView();
            }
        }
        else if(e.getSource() == navList) {
            tab = 0;
            clearView();
        }
        else if(e.getSource() == navOverview) {
            clearView();
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
        else if(e.getSource() == addPicture) {
            if(countries.get(currentCountry) == null) {
                countries.put(currentCountry, new DLList<MyImage>());
                addOverviewCountryButton(currentCountry);
            }
            countries.get(currentCountry).add(new MyImage(addPictureURL.getText(), addPictureDescription.getText(), addPictureDate.getText()));
        }
        else {
            for(JButton jb : countryButtons.keySet()) {
                if(e.getSource() == jb) {
                    currentCountry = countryButtons.get(jb);
                    currentImages = countries.get(currentCountry);
                    tab = 1;
                    clearView();
                }
            }
        }
        repaint();
    }
}
