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

	public LinkedList<LinkedList<Card>> deal(int players, int cards) {
		LinkedList<LinkedList<Card>> hands = new LinkedList<LinkedList<Card>>();
		for (int i=0; i<players+1; i++) {
			hands.add(new LinkedList<Card>());
		}

		int n = players*cards;
		if(cards == 0 || n>52)
			n = 52;
		for(int i=0; i<n; i++) {
			hands.get(i%players).add(deck.get(i));
		}
		for (int i=n; i<52; i++) {
			hands.get(players).add(deck.get(i));
		}

		return hands;
	}
}