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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Collections;

public class Screen extends JPanel implements ActionListener, MouseListener {

    JButton draw;
    DLList<Card> deck, player;
    int points = 50;
    int pointsWon = 0;
    int state = 0;

    int playerCardOffsetX = 100;
    int playerCardOffsetY = 100;
    int cardHeight = 1056/5;
    int cardWidth = 691/5;

    Color felt = new Color(28, 130, 30);

    HashMap<String, String> consecutiveFaces = new HashMap<String, String>();
    
    public Screen() {
        setLayout(null);
        player = new DLList<Card>();
        deck = new DLList<Card>();

        ImageLoader.load();
        for (int i = 0; i<ImageLoader.imagenames.length; i++) {
			for (int j = 0; j<ImageLoader.imagesuits.length; j++) {
				deck.add(new Card(ImageLoader.imagenames[i], ImageLoader.imagesuits[j]));
			}
		}
        deck.shuffle();

        draw = new JButton("Draw");
        draw.setBounds(200,440,200,60);
        draw.addActionListener(this);
        this.add(draw);

        addMouseListener(this);

        consecutiveFaces.put("10", "J");
        consecutiveFaces.put("J", "Q");
        consecutiveFaces.put("Q", "K");
        consecutiveFaces.put("K", "A");
        consecutiveFaces.put("A", "2");

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
        g.setColor(Color.white);
        g.drawString("Points: "+points, 300, 700);
        g.drawString("Points won last round: "+pointsWon, 450, 700);
        g.drawString("Flip the cards you want to get rid of", 100, 50);
    }
    public void playSound(String sound) {
       try
         {
            URL url = this.getClass().getClassLoader().getResource(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
         }
         catch (Exception exc)
         {
             exc.printStackTrace(System.out);
         } 
    }
    public int calculatePoints() {

        boolean flush = true;
        boolean consecutive = true;
        boolean royal = false;
        ArrayList<Integer> pairs = new ArrayList<Integer>();
        boolean three = false;
        boolean four = false;

        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        ArrayList<Integer> values = new ArrayList<Integer>();
        for(int i = 0;i < 5;i++) {
            int val = player.get(i).getValue();
            values.add(val);
            if(counts.containsKey(val))
                counts.put(val, counts.get(val)+1);
            else
                counts.put(val, 1);
        }
        Collections.sort(values);

        //check for royal
        if(values.get(0) == 10 && values.get(4) == 13) {
            royal = true;
        }
        // check for flush        
        String suit = player.get(0).suit();
        for(int i = 0;i < 5;i++) {
            if(!player.get(i).suit().equals(suit)) {
                flush = false;
            }
        }
        // check for consecutive cards
        for(int i = 0;i < values.size()-1;i++) {
            if((values.get(i)+1)%13 != (values.get(i+1))%13) {
                consecutive = false;
                break;
            }
        }
        // check for pairs, three of a kind, four of a kind, full house
        for(int k : counts.keySet()) {
            if(counts.get(k) == 2) {
                pairs.add(k);
            }
            else if(counts.get(k) == 3) {
                three = true;
            }
            else if(counts.get(k) == 4) {
                four = true;
            }
        }
        
        /*
        System.out.println("Consecutive: "+consecutive);
        System.out.println("Flush: "+flush);
        System.out.println("Royal: "+royal);
        System.out.println("Pairs: "+pairs);
        System.out.println("Three: "+three);
        System.out.println("Four: "+four);
        */

        if(flush && consecutive && royal) {
            return 250;
        }
        if(flush && consecutive) {
            return 50;
        }
        if(four) {
            return 25;
        }
        if(pairs.size() == 1 && three) {
            return 9;
        }
        if(flush) {
            return 6;
        }
        if(consecutive) {
            return 4;
        }
        if(three) {
            return 3;
        }
        if(pairs.size() == 2) {
            return 2;
        }
        if(pairs.size() == 1 && pairs.get(0) > 10) {
            return 1;
        }
        return 0;
        // Royal Flush (Combination of 10,J,Q,K,A all with the same suit) - 250 points.
        // Straight Flush (Consecutive 5 cards in order all with the same suit) - 50 points.
        // Four of Kind (4 cards with the same name) - 25 points.
        // Full House (3 of the kind and a 2 of a kind) -  9 points.
        // Flush (All 5 cards with the same suit) - 6 points.
        // Straight (Consecutive 5 cards in consecutive order) - 4 Points.
        // A straight will start from A, 2, 3, 4, 5  and goes to 10, J, Q, K A
        // (Hint: To help determine straights, you may want to sort the cards by value)
        // 3 of a Kind - 3 points.
        // 2 Pairs - 2 points.
        // Pair of Jacks or Higher - 1 Point.  This is the break even as the game cost 1 point and player wins 1 point.
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == draw) {
            if(points > 0) {
                if(state == 0) {
                    while(player.size() > 0) {
                        Card c = player.remove(0);
                        c.faceUp();
                        deck.add(c);
                    }
                    for(int i = 0;i < 5;i++) {
                        player.add(deck.remove(0));
                    }
                    points--;
                    state++;
                    draw.setText("Draw");
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
                    pointsWon = calculatePoints();
                    if(pointsWon > 0) {
                        playSound("win.wav");
                    }
                    else {
                        playSound("lose.wav");
                    }
                    points += pointsWon;
                    state = 0;
                    draw.setText("Try again");
                }
            }
            if((int)(Math.random()*10) == 5)
                deck.shuffle();
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
