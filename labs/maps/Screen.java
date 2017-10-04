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
import java.util.HashMap;
import java.util.TreeMap;

public class Screen extends JPanel implements ActionListener {
    
    private JTextArea textAreaA;
	private String displayTextA = ""; 

    HashMap<Item, Integer> store;
    TreeMap<Item, Integer> cart;
    
	
    public Screen() {
        this.setLayout(null);
        
        store = new HashMap<Item, Integer>();
        store.put(new Item("Oranges", 100, "orange.jpg"), 10);
        store.put(new Item("Apples", 200, "apple.jpg"), 10);
        store.put(new Item("Bananas", 300, "banan.jpg"), 10);
        store.put(new Item("Kiwis", 400, "kiwi.jpg"), 10);
        store.put(new Item("Grapes", 500, "grape.jpg"), 10);
        
        cart = new TreeMap<Item, Integer>();
        
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1000,800);
    }
    public void paintComponent(Graphics g) {
		// draw background
        g.setColor(Color.black);
        g.fillRect(0,0,1000,800);
	
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.white);	
        int x = 50;
        int y = 50;
        for(Item i : store.keySet()) {
            i.drawMe(g, store.get(i), x, y);
            if(i.addButton.getParent() == null) {
                this.add(i.addButton);
                i.addButton.addActionListener(this);
            }
            y += 100;
        }
        x = 500;
        y = 50;
        int total = 0;
        for(Item i : cart.keySet()) {
            if(cart.get(i) == 0) {
                i.addButton.setVisible(false);
                continue;
            }
            i.addButton.setVisible(false);
            i.drawMe(g, cart.get(i), x, y);
            if(i.addButton.getParent() == null) {
                this.add(i.addButton);
                i.addButton.addActionListener(this);
            }
            i.addButton.setText("Remove");
            total += i.price*cart.get(i);
            y += 100;
        }
        g.drawString("Total: $"+total, x, y);
    }
    public void actionPerformed(ActionEvent e) {
        for(Item i : store.keySet()) {
            if(e.getSource() == i.addButton) {
                if(cart.containsKey(i) && store.get(i) > 0) {
                    Item temp = new Item(i.name, i.price, i.filename);
                    cart.put(temp, cart.get(temp)+1);
                    store.put(i, store.get(i)-1);
                }
                else if(store.get(i) > 0){
                    cart.put(new Item(i.name, i.price, i.filename), 1);
                    store.put(i, store.get(i)-1);
                }
                break;
            }
        }
        for(Item i : cart.keySet()) {
            if(e.getSource() == i.addButton) {
                if(store.containsKey(i) && cart.get(i) > 0) {
                    Item temp = new Item(i.name, i.price, i.filename);
                    store.put(temp, store.get(temp)+1);
                    cart.put(i, cart.get(i)-1);
                }
                else if(cart.get(i) > 0){
                    store.put(new Item(i.name, i.price, i.filename), 1);
                    cart.put(i, cart.get(i)-1);
                }
                break;
            }
        }
        repaint();
    }
}
