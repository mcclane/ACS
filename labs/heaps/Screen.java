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
import java.awt.image.BufferedImage;

public class Screen extends JPanel implements ActionListener {
    
    public static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
    
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
    JButton cook;
    JButton deliver;
    
    MenuItem[] items;
    
    int time = 0;
    
    public Screen() {
        this.setLayout(null);
        open = new MinHeap<Order>();
        completed = new MinHeap<Order>();
        
        menu = new HashMap<JButton, MenuItem>();
        
        items = new MenuItem[8];
        items[0] = new MenuItem("Corn White Bread", 2.30, "CornWhiteBread.jpg");
        items[1] = new MenuItem("Pickle Toast", 4.30, "PickleToast.png");
        items[2] = new MenuItem("Balonee Sandwitch", 5.00, "BaloneeSandwitch.jpg");
        items[3] = new MenuItem("Rare Chicken Strips", 3.98, "RareChickenStrips.jpg");
        items[4] = new MenuItem("Sawsage and Beens", 4.12, "SawsaugeAndBeens.png");
        items[5] = new MenuItem("Pepto Bismol Hot Dog", 9.99, "PeptoBismolHotDog.jpg");
        items[6] = new MenuItem("Frozen Mack and Chease", 14.99, "FrozenMackAndChease.png");
        items[7] = new MenuItem("Grilled Chease Sandwitch", 18.99, "GrilledCheaseSandwitch.png");
        for(int i = 0;i < items.length;i++) {
            JButton temp = new JButton(items[i].toString());
            temp.setBounds(200, i*100+100, 300, 30);
            temp.addActionListener(this);
            this.add(temp);
            menu.put(temp, items[i]);
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
        
        cook = new JButton("Cook");
        cook.setBounds(500, 800, 200, 30);
        cook.addActionListener(this);
        this.add(cook);
        currentOrder = null;
        
        deliver = new JButton("Deliver");
        deliver.setBounds(700, 800, 200, 30);
        deliver.addActionListener(this);
        this.add(deliver);
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
            cook.setVisible(false);
            deliver.setVisible(true);
            for(int i = 0;i < items.length;i++) {
                items[i].drawMyImage(g, 0, i*100+100, 200, 100);
            }
            for(JButton jb : menu.keySet()) {
                jb.setVisible(true);
            }
            g.drawString("Menu", 10, 50);
            g.drawString("Current Order", 500, 50);
            if(currentOrder != null) {
               currentOrder.drawMe(g, 500, 100);
            }
            g.drawString("Completed Orders to Deliver:", 800, 50);
            if(completed.peek() != null) {
               completed.peek().drawMe(g, 800, 100);
            }
        }
        // chef view
        else if(view == 1) {
            cook.setVisible(true);
            order.setVisible(false);
            deliver.setVisible(false);
            for(JButton jb : menu.keySet()) {
                jb.setVisible(false);
            }
            if(open.size() > 0) {
                open.peek().drawMe(g, 500, 100);
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
        else if(e.getSource() == cook) {
            completed.add(open.poll());
        }
        else if(e.getSource() == deliver) {
            completed.poll();
        }
        else {
            // adding things to order from menu
            for(JButton jb : menu.keySet()) {
                if(e.getSource() == jb) {
                    if(currentOrder == null) {
                        currentOrder = new Order(time);
                        time++;
                    }
                    currentOrder.add(menu.get(jb).copy());
                }
            }
        }
        repaint();
    }
}