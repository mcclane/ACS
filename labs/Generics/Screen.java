import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Screen extends JPanel implements ActionListener {
	private JButton pButton;

    private JTextField search;

    public Screen() {
        this.setLayout(null);
        
        //JButton
		pButton = new JButton("Police Officers");
		pButton.setBounds(250,50, 100, 30); //sets the location and size
		pButton.addActionListener(this); //add the listener
		this.add(pButton); //add to JPanel

		//TextField
		search = new JTextField(20);
		search.setBounds(50,50, 80, 30);
		this.add(search);
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,600);
    }
    public void paintComponent(Graphics g) {
		//draw background
        g.setColor(Color.white);
        g.fillRect(0,0,800,600);
        
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.red);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pButton) { 
            System.out.println("Police Officer");
        }
        repaint();
    }
}
