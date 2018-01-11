import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener {

    private JButton add;
    private JButton delete;

    private JButton sortByTime;
    private JButton sortByName;
    private JButton sortByPrice;

    private JTextField name;
    private JTextField price;

    private String sortSelection = "time";

    SLList<Item> original;
    SLList<Item> sorted;

    public Screen() {
        this.setLayout(null);
        setFocusable(true);

        add = new JButton("Add Item");
        add.setBounds(100, 200, 200, 30);
        add.addActionListener(this);
        this.add(add);

        delete = new JButton("Add Item");
        delete.setBounds(100, 230, 200, 30);
        delete.addActionListener(this);
        this.add(delete);

        sortByTime = new JButton("Sort by Time");
        sortByTime.setBounds(100, 260, 200, 30);
        sortByTime.addActionListener(this);
        this.add(sortByTime);

        sortByName = new JButton("Sort by Name");
        sortByName .setBounds(100, 290, 200, 30);
        sortByName.addActionListener(this);
        this.add(sortByName);

        sortByPrice= new JButton("Sort by Price");
        sortByPrice.setBounds(100, 320, 200, 30);
        sortByPrice.addActionListener(this);
        this.add(sortByPrice);

        name = new JTextField(100);
        name.setBounds(100, 100, 200, 30);
        this.add(name);

        price = new JTextField(100);
        price.setBounds(100, 130, 200, 30);
        this.add(price);

        original = new SLList<Item>();
        sorted = new SLList<Item>();
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

        //draw in the labels for the text fields
        g.setColor(Color.black);
        g.drawString("Name", 10, 120);
        g.drawString("Price", 10, 150);

        //draw the shopping cart list
        int total = 0;
        int y = 50;
        for(int i = 0;i < sorted.size();i++) {
            g.drawString(sorted.get(i).toString(), 300, y);
            total += sorted.get(i).price*sorted.get(i).quantity;
            y += 50;
        }
        g.drawString("Total: "+total, 600, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add) {
            boolean added = false;
            Item newItem = new Item(name.getText(), Double.parseDouble(price.getText()));
            for(int i = 0;i < sorted.size();i++) {
                if(newItem.equals(original.get(i))) {
                    original.get(i).quantity++;
                    added = true;
                    break;
                }
            }
            if(!added) {
                original.add(newItem);
            }
        }
        else if(e.getSource() == delete) {
            for(int i = 0;i < original.size();i++) {
                if(original.get(i).name.equals(name.getText())) {
                    original.remove(original.get(i));
                    break;
                }
            }
        }
        else if(e.getSource() == sortByPrice) {
            sortSelection = "price";
        }
        else if(e.getSource() == sortByName) {
            sortSelection = "name";
        }
        else if(e.getSource() == sortByTime) {
            sortSelection = "time";
        }
        sorted = sort(original, sortSelection);
        repaint();
    }

    public static SLList<Item> sort(SLList<Item> items, String selection) {
        SLList<Item> sorted;
        switch(selection) {
            case "name":
                sorted = new SLList<Item>();
                for(int i = 0;i < items.size();i++) {
                    boolean added = false;
                    for(int j = 0;j < sorted.size();j++) {
                        if(sorted.get(j).name.compareTo(items.get(i).name) > 0) {
                            sorted.add(j, items.get(i));
                            added = true;
                            break;
                        }
                    }
                    if(!added) {
                        sorted.add(items.get(i));
                    }
                }
                return sorted;
            case "price":
                sorted = new SLList<Item>();
                for(int i = 0;i < items.size();i++) {
                    boolean added = false;
                    for(int j = 0;j < sorted.size();j++) {
                        if(sorted.get(j).price > items.get(i).price) {
                            sorted.add(j, items.get(i));
                            added = true;
                            break;
                        }
                    }
                    if(!added) {
                        sorted.add(items.get(i));
                    }
                }
                return sorted;
        }
        return items;
    }
}
