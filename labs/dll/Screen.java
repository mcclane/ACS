import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Screen extends JPanel implements ActionListener{

    JButton draw;
    DLList<Card> deck;
    DLList<Card> player;
    
    public Screen() {
        setLayout(null);
        player = new DLList<Card>();
        deck = new DLList<Card>();
        deck.add(new Card("2", "Clubs"));
        deck.add(new Card("3", "Clubs"));
        deck.add(new Card("4", "Clubs"));
        deck.add(new Card("5", "Clubs"));
        deck.add(new Card("6", "Clubs"));
        deck.add(new Card("7", "Clubs"));
        deck.add(new Card("8", "Clubs"));
        deck.add(new Card("9", "Clubs"));
        deck.add(new Card("10", "Clubs"));
        deck.add(new Card("Jack", "Clubs"));
        deck.add(new Card("Queen", "Clubs"));
        deck.add(new Card("King", "Clubs"));
        deck.add(new Card("Ace", "Clubs"));
        deck.add(new Card("2", "Hearts"));
        deck.add(new Card("3", "Hearts"));
        deck.add(new Card("4", "Hearts"));
        deck.add(new Card("5", "Hearts"));
        deck.add(new Card("6", "Hearts"));
        deck.add(new Card("7", "Hearts"));
        deck.add(new Card("8", "Hearts"));
        deck.add(new Card("9", "Hearts"));
        deck.add(new Card("10", "Hearts"));
        deck.add(new Card("Jack", "Hearts"));
        deck.add(new Card("Queen", "Hearts"));
        deck.add(new Card("King", "Hearts"));
        deck.add(new Card("Ace", "Hearts"));
        deck.add(new Card("2", "Diamonds"));
        deck.add(new Card("3", "Diamonds"));
        deck.add(new Card("4", "Diamonds"));
        deck.add(new Card("5", "Diamonds"));
        deck.add(new Card("6", "Diamonds"));
        deck.add(new Card("7", "Diamonds"));
        deck.add(new Card("8", "Diamonds"));
        deck.add(new Card("9", "Diamonds"));
        deck.add(new Card("10", "Diamonds"));
        deck.add(new Card("Jack", "Diamonds"));
        deck.add(new Card("Queen", "Diamonds"));
        deck.add(new Card("King", "Diamonds"));
        deck.add(new Card("Ace", "Diamonds"));
        deck.add(new Card("2", "Spades"));
        deck.add(new Card("3", "Spades"));
        deck.add(new Card("4", "Spades"));
        deck.add(new Card("5", "Spades"));
        deck.add(new Card("6", "Spades"));
        deck.add(new Card("7", "Spades"));
        deck.add(new Card("8", "Spades"));
        deck.add(new Card("9", "Spades"));
        deck.add(new Card("10", "Spades"));
        deck.add(new Card("Jack", "Spades"));
        deck.add(new Card("Queen", "Spades"));
        deck.add(new Card("King", "Spades"));
        deck.add(new Card("Ace", "Spades"));

        draw = new JButton("Draw");
        draw.setBounds(200,440,200,60);
        draw.addActionListener(this);
        this.add(draw);
    }
    public Dimension getPreferredSize() {
        return new Dimension(1000,800); //size
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0,0,1000,800);
        Font font = new Font("Arial",Font.PLAIN,20);
        g.setFont(font);
        g.setColor(Color.black);
        int x = 0;
        int y = 0;
        for(int i = 0;i < player.size();i++) {
            player.get(i).drawMe(g, x, y);
            x = (x+100)%1000; 
        }
    }
    public void playSound(String sound) {
       try
         {
            URL url = this.getClass().getClassLoader().getResource(sound+".wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
         }
         catch (Exception exc)
         {
             exc.printStackTrace(System.out);
         } 
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == draw) {
            while(player.size() > 0) {
                deck.add(player.remove(0));
            }
            for(int i = 0;i < 5;i++) {
                player.add(deck.remove((int)(Math.random()*deck.size())));
            }
        }
        repaint();

    }
}
