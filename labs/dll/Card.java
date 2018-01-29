import java.awt.Graphics;
import java.awt.Color;

public class Card {

	public static final String HEART = "hearts", CLUB = "clubs", DIAMOND = "diamonds", SPADE = "spades";
	String name, suit;
	int value;
    private boolean flipped = false;

	public Card(String name, String suit) {
		this.name = name;
		this.suit = suit;
	}
	
	public String toString() {
		return name + " of " + suit;
	}
	
	public void flip() {
        flipped = !flipped;
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
