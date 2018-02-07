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
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Collections;

public class Screen extends JPanel implements KeyListener{
    
    private HashTable<Article> grid;

    int pR = 4;
    int pC = 10;
    
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
                //grid.add(new Tree(r, c, 1));
            }
        }
        for(int r = 5;r < 15;r++) {
            for(int c = 0;c < 5;c++) {
                grid.add(new Grass(r, c, 0));
                //grid.add(new Bush(r, c, 1));
            }
        }
        for(int r = 5;r < 15;r++) {
            for(int c = 15;c < 20;c++) {
                grid.add(new Dirt(r, c, 0));
                //grid.add(new Rock(r, c, 1));
            }
        }
        for(int r = 15;r < 20;r++) {
            for(int c = 0;c < 20;c++) {
                grid.add(new Grass(r, c, 0));
            }
        }
        //add in the random stuff
        int countOfArticlesAdded = 0;
        while(countOfArticlesAdded <= 75) {
            int row = (int)(Math.random()*20);
            int column = (int)(Math.random()*20);
            int index = row*20+column;
            if((grid.get(index).size() == 1 && !grid.get(index).get(0).name().equals("water")) && pC != column && pR != row) {
                int selection = (int)(Math.random()*3);
                if(selection == 0)
                    grid.add(new Tree(row, column, 1));
                else if(selection == 1)
                    grid.add(new Bush(row, column, 1));
                else if(selection == 2) 
                    grid.add(new Rock(row, column, 1));
                countOfArticlesAdded++;
            }
        }
        addKeyListener(this);
        setFocusable(true);
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
        g.setColor(Color.red);
        g.fillRect(pC*50, pR*50, 50, 50);
        g.setColor(Color.green);
        g.fillRect(pC*50+5, pR*50+5, 25, 25);
        g.fillRect(pC*50+15, pR*50+15, 25, 25);
        
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
    public void keyPressed(KeyEvent e) {
        int keyDown = e.getKeyCode();
        //System.out.println(keyDown);
        int nC = pC;
        int nR = pR;
        switch(keyDown) {
            // right
            case 39:
                nC++;
                break;
            // left
            case 37:
                nC--;
                break;
            // down
            case 40:
                nR++;
                break;
            // up
            case 38:
                nR--;
                break;
        }
        if(grid.get(nR*20+nC) != null && grid.get(nR*20+nC).size() == 1 && !grid.get(nR*20+nC).get(0).name().equals("water") && nC < 20 && nC >= 0 && nR < 20 && nC >= 0) {
            pC = nC;
            pR = nR;
            repaint();
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
