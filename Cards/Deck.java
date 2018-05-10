import java.util.Collections;
import java.util.LinkedList;

public class Deck {
	private LinkedList<Card> deck = new LinkedList<>();

	public Deck() {
		Suit suits[] = {Suit.HEARTS, Suit.CLUBS, Suit.DIAMONDS, Suit.SPADES};
		String ranks[] = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		for (Suit suit : suits) {
			for (String rank : ranks) {
				deck.add(new Card(suit, rank));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public String toString() {
		String r = "";
		for (Card card : deck) {
			r += card + "\n";
		}
		return r;
	}

	public LinkedList<Card> getDeck() {
		return deck;
	}
	
	public Card draw() {
		return deck.poll();
	}
}