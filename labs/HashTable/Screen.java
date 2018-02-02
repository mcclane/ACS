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

public class Screen extends JPanel {
    
    private HashTable<Article> grid;
    
    public Screen() {
        setLayout(null);
        
        grid = new HashTable<Article>();
        
        for(int r = 5;r < 15;r++) {
            for(int c = 5;c < 15;c++) {
                grid.add(new Water(r, c, 0));
            }
        }
        for(int r = 0;r < 5;r++) {
            for(int c = 0;c < 20;c++) {
                grid.add(new Dirt(r, c, 0));
                grid.add(new Tree(r, c, 1));
            }
        }
        for(int r = 5;r < 15;r++) {
            for(int c = 0;c < 5;c++) {
                grid.add(new Grass(r, c, 0));
            }
        }
        for(int r = 5;r < 15;r++) {
            for(int c = 15;c < 20;c++) {
                grid.add(new Dirt(r, c, 0));
            }
        }
        for(int r = 15;r < 20;r++) {
            for(int c = 0;c < 20;c++) {
                grid.add(new Grass(r, c, 0));
            }
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(1000,1000); //size
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,1000,1000);
        Font font = new Font("Arial",Font.PLAIN,20);
        g.setFont(font);
        g.setColor(Color.black);
                
        for(int i = 0;i < 400;i++) {
            if(grid.get(i) != null) {
                for(int j = 0;j < grid.get(i).size();j++) {
                    grid.get(i).get(j).drawMe(g);
                }
            }
        }
        
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
}
