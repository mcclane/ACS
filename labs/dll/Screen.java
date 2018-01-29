import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Screen extends JPanel implements ActionListener, MouseListener {

    JButton draw;
    DLList<Card> deck, player;

    int playerCardOffsetX = 100;
    int playerCardOffsetY = 100;
    int cardHeight = 1056/5;
    int cardWidth = 691/5;
    
    Color felt = new Color(28, 130, 30);
    
    public Screen() {
        setLayout(null);
        player = new DLList<Card>();
        deck = new DLList<Card>();

        ImageLoader.load();
        for (int i = 0; i<ImageLoader.names.length; i++) {
			for (int j = 0; j<ImageLoader.suits.length; j++) {
				deck.add(new Card(ImageLoader.names[i], ImageLoader.suits[j]));
			}
		}
        deck.shuffle();

        draw = new JButton("Draw");
        draw.setBounds(200,440,200,60);
        draw.addActionListener(this);
        this.add(draw);
        addMouseListener(this);
    }
    public Dimension getPreferredSize() {
        return new Dimension(1000,800); //size
    }
    public void paintComponent(Graphics g) {
        g.setColor(felt);
        g.fillRect(0,0,1000,800);
        Font font = new Font("Arial",Font.PLAIN,20);
        g.setFont(font);
        g.setColor(Color.black);
        for(int i = 0;i < player.size();i++) {
            player.get(i).render(g, playerCardOffsetX+i*cardWidth, playerCardOffsetY);
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
            if(player.size() == 0) {
                for(int i = 0;i < 5;i++) {
                    player.add(deck.remove((int)(Math.random()*deck.size())));
                }
            }
            else {
                for(int i = 0;i < player.size();i++) {
                    if(player.get(i).flipped()) {
                        player.get(i).flip();
                        deck.add(player.remove(i));
                        if(i == 4)
                            player.add(deck.remove(0));
                        else
                            player.add(i, deck.remove(0));
                    }
                }
            }
        }
        repaint();
    }
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(y > playerCardOffsetY && y < playerCardOffsetY + cardHeight) {
            int index = (x-playerCardOffsetX)/cardWidth;
            if(index < player.size()) {
                player.get(index).flip();
                repaint();
            }
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
