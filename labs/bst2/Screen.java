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
