import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
 
public class Screen extends JPanel implements ActionListener{
    
    BinaryTree<Item> bt;
    private Dimension area; //indicates area taken up by graphics
    private JPanel drawingPane;
    
    private boolean autoBalance;
    
    private JButton toggleAutoBalance;
    private JButton manualBalance;
    private JButton add;
    
    private JTextField addName;
    private JTextField addPrice;
 
    public Screen() {
        super(new BorderLayout());
        area = new Dimension(0,0);

        bt = new BinaryTree<Item>();
        for(int i = 0;i < 63;i++) {
            bt.add(new Item(""+i, 123.0));
        }
        //bt.balance();
        
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
        
        addName = new JTextField(100);
        addName.setBounds(0, 60, 200, 30);
        addName.setText("Enter Name");
        this.add(addName);
        
        addPrice = new JTextField(100);
        addPrice.setBounds(0, 90, 200, 30);
        addPrice.setText("Enter Price");
        this.add(addPrice);
        
        add = new JButton("Add");
        add.setBounds(0, 120, 200, 30);
        add.addActionListener(this);
        this.add(add);
 
        //Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(drawingPane);
        scroller.setPreferredSize(new Dimension(1600,900));
        //scroller.setBounds(0, 500, 1600, 900);
         
        //Lay out this demo.
        //add(instructionPanel, BorderLayout.PAGE_START);
        //add(scroller, BorderLayout.CENTER);
        add(scroller);
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0, 0, 1600, 900);
        g.setColor(Color.black);
        g.drawString("Hello World", 0, 10);
    }
    /** The component inside the scroll pane. */
    public class DrawingPane extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            bt.drawMe(g, area.width);
        }
    }
    public void checkBalancedAndThenBalance() {
        bt.balance();
        area.width = (int)(Math.pow(2, bt.getHeight()+1))*20;
        drawingPane.setPreferredSize(area);
        drawingPane.repaint();
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == toggleAutoBalance) {
            if(autoBalance) {
                toggleAutoBalance.setText("Auto Balance: Off");
            }
            else {
                toggleAutoBalance.setText("Auto Balance: On");
                checkBalancedAndThenBalance();
            }
            autoBalance = !autoBalance;
        }
        else if(e.getSource() == manualBalance) {
            checkBalancedAndThenBalance();
        }
        repaint();
    }
}
