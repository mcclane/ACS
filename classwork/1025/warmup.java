import java.util.Stack;
import java.util.Iterator;

class Card {
    private String suit;
    private String value;
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }
    public String toString() {
        return suit+" - "+value;
    }
}
public class warmup {
    public static void main(String[] args) {
        Stack<Card> player1 = new Stack<Card>();
        player1.push(new Card("Diamonds", "5"));
        player1.push(new Card("Clubs", "2"));
        player1.push(new Card("Hearts", "4"));
        player1.push(new Card("Clubs", "5"));
        player1.push(new Card("Clubs", "4"));
        System.out.println("Player 1: "+player1);

        Stack<Card> player2 = new Stack<Card>();
        player2.push(new Card("Hearts", "3"));
        player2.push(new Card("Diamonds", "4"));
        player2.push(new Card("Clubs", "6"));
        System.out.println("Player 2: "+player2);
        
        Stack<Card> deck = new Stack<Card>();
        Iterator<Card> p1 = player1.iterator();
        Iterator<Card> p2 = player2.iterator();
        int i = 0;
        while(p1.hasNext() && p2.hasNext()) {
            if(i%2 == 0) {
                deck.push(p1.next());
            }
            else {
                deck.push(p2.next());
            }
            i++;
        }
        if(p1.hasNext()) {
            while(p1.hasNext()) {
                deck.push(p1.next());
            }
        }
        else if(p2.hasNext()) {
            while(p2.hasNext()) {
                deck.push(p2.next());
            }
        }
        System.out.println("Deck: "+deck);
        
    }
}