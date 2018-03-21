import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
 
public class Screen extends JPanel implements ActionListener{
    
    BinaryTree<Item> bt;
    private Dimension area; //indicates area taken up by graphics
    private JPanel drawingPane;

    private JTextArea accountsTextArea;
    private JScrollPane accountScrollPane;
    
    private boolean autoBalance;
    
    private JButton toggleAutoBalance;
    private JButton manualBalance;
    private JButton add;
    private JButton delete;
    private JButton search;
    
    private JTextField addName;
    private JTextField addPrice;

    public Screen() {
        //super(new BorderLayout());
        area = new Dimension(0,0);

        bt = new BinaryTree<Item>();
        bt.add(new Item("Socks", 10));
        bt.add(new Item("Kiwis", 15));
        bt.add(new Item("Austin", 3));
        bt.add(new Item("Nic", 4));
        //bt.balance();

        accountsTextArea = new JTextArea();
        accountsTextArea.setEditable(false);
        accountsTextArea.setText(bt.toString().trim());
        accountScrollPane = new JScrollPane(accountsTextArea);
        accountScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        accountScrollPane.setBounds(0,0,50,50);
        this.add(accountScrollPane);
 
        
        //JPanel instructionPanel = new JPanel(new GridLayout(0,1));
        //instructionPanel.setFocusable(true);
 
        //Set up the drawing area.
        drawingPane = new DrawingPane();
        drawingPane.setBackground(Color.white);
        area.width = (int)(Math.pow(2, bt.getHeight()+1))*20;
        drawingPane.setPreferredSize(area);
        
        toggleAutoBalance = new JButton("Auto Balance: Off");
        toggleAutoBalance.setBounds(0, 0, 200, 30);
        toggleAutoBalance.addActionListener(this);
        this.add(toggleAutoBalance);
        
        manualBalance = new JButton("Balance");
        manualBalance.setBounds(0, 30, 200, 30);
        manualBalance.addActionListener(this);
        this.add(manualBalance);
        
        addName = new JTextField(7);
        addName.setBounds(0, 60, 200, 30);
        addName.setText("Enter Name");
        this.add(addName);
        
        addPrice = new JTextField(7);
        addPrice.setBounds(0, 90, 200, 30);
        addPrice.setText("Enter Price");
        this.add(addPrice);
        
        add = new JButton("Add");
        add.setBounds(0, 120, 200, 30);
        add.addActionListener(this);
        this.add(add);

        delete = new JButton("Delete");
        delete.setBounds(0, 150, 200, 30);
        delete.addActionListener(this);
        this.add(delete);

        search = new JButton("Search by Name");
        search.setBounds(0, 180, 200, 30);
        search.addActionListener(this);
        this.add(search);

 
        //Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(drawingPane);
        scroller.setPreferredSize(new Dimension(1600,600));
        //scroller.setBounds(0, 500, 1600, 900);
         
        //Lay out this demo.
        //add(instructionPanel, BorderLayout.PAGE_START);
        //add(scroller, BorderLayout.CENTER);
        add(scroller);
    }
    
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1920,800);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0, 0, 1920, 800);
        g.setColor(Color.black);
        g.drawString("Tree Height: "+bt.getHeight(), 0, 10);
        g.drawString("Tree Size: "+bt.size(), 0, 20);
        g.drawString("Passes: "+bt.passes, 0, 30);
    }
    /** The component inside the scroll pane. */
    public class DrawingPane extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            bt.drawMe(g, area.width);
        }
    }
    public void checkBalancedAndThenBalance() {
        if(!bt.isBalanced()) {
            bt.balance();
            area.width = (int)(Math.pow(2, bt.getHeight()+1))*20;
            drawingPane.setPreferredSize(area);
            drawingPane.repaint();
        }
        accountsTextArea.setText(bt.toString().trim());
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == toggleAutoBalance) {
            if(autoBalance) {
                toggleAutoBalance.setText("Auto Balance: Off");
                manualBalance.setVisible(true);
            }
            else {
                toggleAutoBalance.setText("Auto Balance: On");
                manualBalance.setVisible(false);
                checkBalancedAndThenBalance();
            }
            autoBalance = !autoBalance;
        }
        else if(e.getSource() == manualBalance) {
            checkBalancedAndThenBalance();
        }
        else if(e.getSource() == add) {
            bt.add(new Item(addName.getText(), Double.parseDouble(addPrice.getText())));
            if(autoBalance) {
                checkBalancedAndThenBalance();
            }
            accountsTextArea.setText(bt.toString().trim());
        }
        else if(e.getSource() == delete) {
            bt.remove(new Item(addName.getText(), Double.parseDouble(addPrice.getText())));
            if(autoBalance) {
                checkBalancedAndThenBalance();
            }
            accountsTextArea.setText(bt.toString().trim());
        }
        else if(e.getSource() == search) {
            Item result = bt.search(new Item(addName.getText(), 0));
            if(result != null) {
                addPrice.setText(""+result.price);
            }
        }
        repaint();
    }
}
