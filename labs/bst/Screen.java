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

public class Screen extends JPanel implements ActionListener {

    BinaryTree<Account> accounts;
    
    private JTextArea accountsTextArea;
    private JScrollPane accountScrollPane;
    
    private JTextField name;
    private JButton enter;
    
    private Account display = null;

    public Screen() {
        this.setLayout(null);
        setFocusable(true);
        
        accounts = new BinaryTree<Account>();
        
        //read in the file
        try {
            Scanner scan = new Scanner(new File("names.txt"));
            int i = 1;
            while(scan.hasNext()) {
                String[] splitted = scan.nextLine().split(",");
                accounts.add(new Account(splitted[1], splitted[0], Math.random()*100000));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        
        accountsTextArea = new JTextArea(200, 250);
        accountsTextArea.setEditable(false);
        accountsTextArea.setText(accounts.toString().trim());
        accountScrollPane = new JScrollPane(accountsTextArea);
        accountScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        accountScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        accountScrollPane.setBounds(10,10,300,500);
        this.add(accountScrollPane);
        
        name = new JTextField(100);
        name.setBounds(400, 50, 200, 30);
        name.setText("Enter first and last name");
        this.add(name);
        
        enter = new JButton("Submit");
        enter.setBounds(400, 100, 200, 30);
        enter.addActionListener(this);
        this.add(enter);
    }

    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(800,600);
    }

    public void paintComponent(Graphics g) {
        // draw background
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.white);
        g.fillRect(0,0,800,600);
        
        g.setColor(Color.black);
        g.drawString("Enter first and last name", 400, 25);
        
        if(display != null) {
            g.drawString("Passes: "+accounts.passes, 400, 150);
            g.drawString(display.infoString(), 400, 200);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter) {
            String[] splitted = name.getText().split(" ");
            display = accounts.search(new Account(splitted[0], splitted[1], 123456));
        }
        repaint();
    }
}
