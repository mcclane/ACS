import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.util.HashMap;

public class Screen extends JPanel implements ActionListener {
    
    MinHeap<Order> open;
    MinHeap<Order> completed;
    
    HashMap<JButton, MenuItem> menu;
    
    Order currentOrder;
    
    // view selection int
    int view = 0;
    
    // view tab buttons
    JButton viewServer;
    JButton viewChef;
    
    JButton order;
    
    public Screen() {
        this.setLayout(null);
        open = new MinHeap<Order>();
        completed = new MinHeap<Order>();
        
        menu = new HashMap<JButton, MenuItem>();
        String[] items = new String[]{"Hello 2.3", "World 5.0"};
        for(int i = 0;i < items.length;i++) {
            String[] splitted = items[i].split(" ");
            JButton temp = new JButton(splitted[0]+" - $"+splitted[1]);
            temp.setBounds(0, i*30+100, 200, 30);
            temp.addActionListener(this);
            this.add(temp);
            menu.put(temp, new MenuItem(splitted[0], Double.parseDouble(splitted[1])));
        }
        
        // view tab buttons
        viewServer = new JButton("Server View");
        viewServer.setBounds(100, 0, 200, 30);
        viewServer.addActionListener(this);
        this.add(viewServer);
        
        viewChef = new JButton("Chef View");
        viewChef.setBounds(300, 0, 200, 30);
        viewChef.addActionListener(this);
        this.add(viewChef);
        
        
        order = new JButton("Order");
        order.setBounds(500, 800, 200, 30);
        order.addActionListener(this);
        this.add(order);
        
        currentOrder = null;
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1200, 900);
    }
    public void paintComponent(Graphics g) {
		// draw background
        g.setColor(Color.black);
        g.fillRect(0, 0, 1200, 900);
        g.setColor(Color.white);
        
        // server view
        if(view == 0) {
            order.setVisible(true);
            for(JButton jb : menu.keySet()) {
                jb.setVisible(true);
            }
            g.drawString("Menu", 10, 50);
            g.drawString("Current Order", 500, 50);
            if(currentOrder != null) {
               System.out.println(currentOrder);
               for(int i = 0;i < currentOrder.items.size();i++) {
                g.drawString(currentOrder.items.get(i).toString(), 500, i*50+100);
               }
            }      
        }
        // chef view
        else if(view == 1) {
            order.setVisible(false);
            for(JButton jb : menu.keySet()) {
                jb.setVisible(false);
            }
            if(open.size() > 0) {
                Order o = open.poll();
                for(int i = 0;i < o.items.size();i++) {
                    g.drawString(o.items.get(i).toString(), 200, i*50+100);
                }
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewServer) {
            view = 0;
        }
        else if(e.getSource() == viewChef) {
            view = 1;
        }
        else if(e.getSource() == order) {
            if(currentOrder != null) {
                open.add(currentOrder);
                currentOrder = null;
            }
        }
        // adding things to order from menu
        for(JButton jb : menu.keySet()) {
            if(e.getSource() == jb) {
                if(currentOrder == null) {
                    currentOrder = new Order((int)(Math.random()*1000));
                }
                currentOrder.add(menu.get(jb).copy());
            }
        }
        repaint();
    }
}