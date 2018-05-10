import java.util.*;

public class Card {
	private Suit suit;
	private String rank;

	public Card(Suit s, String r) {
		suit = s;
		rank = r;
	}

	public int compareTo(Card c) {
		List<String> ranks = Arrays.asList("2","3","4","5","6","7","8","9","10","J","Q","K","A");
		if (ranks.indexOf(this.rank) < ranks.indexOf(c.rank)) {
			return -1;
		} else if (ranks.indexOf(this.rank) > ranks.indexOf(c.rank)) {
			return 1;
		} else {
			return 0;
		}
	}

	public String toString() {
		return this.rank + " of " + this.suit;
	}
}