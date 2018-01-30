import java.awt.Graphics;
import java.awt.Color;

public class Card implements Comparable<Card> {

	public static final String HEART = "hearts", CLUB = "clubs", DIAMOND = "diamonds", SPADE = "spades";
	String name, suit;
	int value;
    private boolean flipped = false;

	public Card(String name, String suit) {
		this.name = name;
		this.suit = suit;
	}
	
	public String toString() {
		return name + "" + suit;
	}
    public String name() {
        return name;
    }
    public String suit() {
        return suit;
    }
	public void flip() {
        flipped = !flipped;
	}
    public void faceUp() {
        flipped = false;
    }
    public int getValue() {
        if(name.equals("J"))
            return 11;
        if(name.equals("Q"))
            return 12;
        if(name.equals("K"))
            return 13;
        if(name.equals("A"))
            return 14;
        return Integer.parseInt(name);
    }
    public boolean equals(Object o) {
        Card c = (Card)(o);
        if(c.getValue() == getValue())
            return true;
        return false;
    }
    public int compareTo(Card c) {
        return getValue() - c.getValue();
    }
    public boolean flipped() {
        return flipped;
    }
	
	public void render(Graphics g,int x,int y) {
		if (flipped) {
			g.drawImage(ImageLoader.images.get("back"), x, y, null);
		} else {
			g.drawImage(ImageLoader.images.get(toString()), x, y, null);
		}
    }
}
