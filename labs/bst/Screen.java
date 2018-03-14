import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;

public class Screen extends JPanel implements ActionListener {

    BinaryTree<Account> accounts;
    
    private JTextArea accountsTextArea;
    private JScrollPane accountScrollPane;
    
    private JTextField name;
    private JButton enter;

    private JTextField displayFName;
    private JTextField displayLName;
    private JTextField displayBalance;
    private JButton update;
    private JButton delete;
    private JButton add;
    
    private Account display = null;

    public Screen() {
        this.setLayout(null);
        setFocusable(true);
        
        accounts = (BinaryTree<Account>)(Loader.load());
        
        accountsTextArea = new JTextArea(200, 250);
        accountsTextArea.setEditable(false);
        accountsTextArea.setText(accounts.toString().trim());
        accountScrollPane = new JScrollPane(accountsTextArea);
        accountScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        accountScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        accountScrollPane.setBounds(10,10,300,500);
        this.add(accountScrollPane);
        
        // search box and button
        name = new JTextField(100);
        name.setBounds(350, 50, 200, 30);
        name.setText("Enter first and last name");
        this.add(name);
        enter = new JButton("Submit");
        enter.setBounds(550, 50, 200, 30);
        enter.addActionListener(this);
        this.add(enter);


        // display account
        displayFName = new JTextField(100);
        displayFName.setBounds(350, 100, 200, 30);
        displayFName.addActionListener(this);
        displayFName.setText("First Name");
        this.add(displayFName);
        displayLName = new JTextField(100);
        displayLName.setBounds(550, 100, 200, 30);
        displayLName.addActionListener(this);
        displayLName.setText("Last Name");
        this.add(displayLName);
        displayBalance = new JTextField(100);
        displayBalance.setBounds(350, 130, 200, 30);
        displayBalance.setText("Balance");
        this.add(displayBalance);
        update = new JButton("Update Account");
        update.setBounds(550, 130, 200, 30);
        update.addActionListener(this);
        this.add(update);
        delete = new JButton("Delete Account");
        delete.setBounds(350, 160, 200, 30);
        delete.addActionListener(this);
        this.add(delete);
        add = new JButton("Add Account");
        add.setBounds(550, 160, 200, 30);
        add.addActionListener(this);
        this.add(add);
    }

    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(800,600);
    }

    public void paintComponent(Graphics g) {
        // draw background
        g.setColor(Color.white);
        g.fillRect(0,0,800,600);
        
        g.setColor(Color.black);
        
        if(display != null) {
            g.drawString("Passes: "+accounts.passes, 400, 90);
            displayFName.setText(display.fname);
            displayLName.setText(display.lname);
            g.drawString("$", 340, 150);
            displayBalance.setText(""+display.balance);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter) {
            displayFName.setText("First Name");
            displayLName.setText("Last Name");
            displayBalance.setText("Balance");
            String[] splitted = name.getText().split(" ");
            if(splitted.length >= 2) {
                display = accounts.search(new Account(splitted[0], splitted[1], 123456));
            }
        }
        else if(e.getSource() == update && display != null) {
            display.fname = displayFName.getText();
            display.lname = displayLName.getText();
            display.balance = Double.parseDouble(displayBalance.getText());
            accountsTextArea.setText(accounts.toString().trim());
            Loader.save(accounts);
        }
        else if(e.getSource() == delete && display != null) {
            accounts.remove(display);
            display = null;
            accountsTextArea.setText(accounts.toString().trim());
            Loader.save(accounts);
        }
        else if(e.getSource() == add ) {
            try {
                accounts.add(new Account(displayFName.getText(), displayLName.getText(), Double.parseDouble(displayBalance.getText())));
                accountsTextArea.setText(accounts.toString().trim());
                Loader.save(accounts);
            }
            catch(NumberFormatException nfe) {
                System.out.println("Not a number");
            }
        }
        repaint();
    }
}
