/*import javax.swing.JPanel;
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

public class Screen extends JPanel implements ActionListener {
    
    BinaryTree<Item> bt;

    public Screen() {
        this.setLayout(null);
        setFocusable(true);  
        bt = new BinaryTree<Item>();
        bt.add(new Item("Hello", 123.0));
        bt.add(new Item("World", 123.0));
        bt.add(new Item("McClane", 123.0));
        bt.add(new Item("Austin", 123.0));
        bt.add(new Item("Banana", 123.0));
        bt.add(new Item("Oorange", 123.0));
        bt.add(new Item("adsfads", 123.0));
        bt.add(new Item("adsfadfa", 123.0));
        bt.add(new Item("adsfafdaads", 123.0));
        bt.add(new Item("avcadafcdxz", 123.0));
        bt.add(new Item("aw3rfadzc", 123.0));
        bt.add(new Item("q2eawfdszvfch", 123.0));
        bt.add(new Item("aryagd", 123.0));
        bt.add(new Item("aehjg", 123.0));
        bt.balance();
    }
    
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1600,1000);
    }

    public void paintComponent(Graphics g) {
        // draw background
        g.setColor(Color.white);
        g.fillRect(0,0,1600,1000);
        g.setColor(Color.black);
        bt.drawMe(g);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
*/
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
 
public class Screen extends JPanel {
    
    BinaryTree<Item> bt;
    private Dimension area; //indicates area taken up by graphics
    private JPanel drawingPane;
 
    public Screen() {
        super(new BorderLayout());
 
        area = new Dimension(0,0);

        bt = new BinaryTree<Item>();
        for(int i = 0;i < 1002;i++) {
            bt.add(new Item(""+i, 123.0));
        }
        bt.balance();
        
        //JPanel instructionPanel = new JPanel(new GridLayout(0,1));
        //instructionPanel.setFocusable(true);
 
        //Set up the drawing area.
        drawingPane = new DrawingPane();
        drawingPane.setBackground(Color.white);
        area.width = (int)(Math.pow(2, bt.getHeight()+1))*20;
        drawingPane.setPreferredSize(area);

 
        //Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(drawingPane);
        scroller.setPreferredSize(new Dimension(1600,900));
         
        //Lay out this demo.
        //add(instructionPanel, BorderLayout.PAGE_START);
        add(scroller, BorderLayout.CENTER);
    }
 
    /** The component inside the scroll pane. */
    public class DrawingPane extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            bt.drawMe(g, area.width);

        }
    }
 
 
 
  
}
