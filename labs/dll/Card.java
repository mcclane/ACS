import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Card {

    private String name;
    private String suit;

    public Card(String name, String suit) {
        this.name = name;
        this.suit = suit;
    }
    public String name() {
        return name;
    }
    public String suit() {
        return suit;
    }
    public void drawMe(Graphics g,int x,int y) {
        g.setColor(Color.white);
        g.fillRoundRect(x,y,100,200,20,20);
        g.setColor(Color.black);
        g.drawString(name+" "+suit, x+50, y+50);
        if(suit.equals("Diamonds")) {
            g.setColor(Color.red);
            int[] xcoordinates = {x+30,x+55,x+80,x+55,x+30};
            int[] ycoordinates = {y+80,y+140,y+80,y+20,y+80};
            g.fillPolygon(xcoordinates,ycoordinates,5);
        }
        else if(suit.equals("Hearts")) {
            g.setColor(Color.red);
            int[] xcoordinates = {x+30,x+80,x+55,x+30};
            int[] ycoordinates = {y+80,y+80,y+140,y+80};
            g.fillPolygon(xcoordinates,ycoordinates,4);
            g.fillOval(x+30,y+60,25,25);
            g.fillOval(x+55,y+60,25,25);
        }
        else if(suit.equals("Clubs")) {
            g.setColor(Color.black);
            g.fillOval(x+30,y+60,30,30);
            g.fillOval(x+15,y+90,30,30);
            g.fillOval(x+45,y+90,30,30);
        }
        else if(suit.equals("Spades")) {
            g.setColor(Color.black);
            int[] xcoordinates = {x+30,x+60,x+90,x+30};
            int[] ycoordinates = {y+100,y+50,y+100,y+100};
            g.fillPolygon(xcoordinates,ycoordinates,4);
            g.fillRect(x+50,y+70,20,60);
        }
    }
}
