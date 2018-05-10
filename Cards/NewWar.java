import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class NewWar {
	private ArrayList<Player> players = new ArrayList<>();
	private int turns = 0;
	
	private class Player implements Comparable<Player> {
		private String name;
		private LinkedList<Card> hand = new LinkedList<>();
		
		public String getName() {
			return name;
		}

		public LinkedList<Card> getHand() {
			return hand;
		}

		public Player(String name) { this.name = name; }

		@Override
		public int compareTo(Player o) {
			int i = 0;
			while (hand.get(i < hand.size() ? i : hand.size()-1).compareTo(o.getHand().get(i<o.getHand().size() ? i : o.getHand().size()-1)) == 0) {
				i += 4;
			}
			return hand.get(i < hand.size() ? i : hand.size()-1).compareTo(o.getHand().get(i<o.getHand().size() ? i : o.getHand().size()-1));
		}
	}
	
	public NewWar(int number_of_players) {
		for (int i=0; i<number_of_players; i++)
			players.add(new Player("Player " + (i+1)));
		Deck deck = new Deck();
		deck.shuffle();
		Card card = deck.draw();
		int i = 0;
		while (card != null) {
			players.get(i).getHand().add(card);
			card = deck.draw();
			i = (i+1)%players.size();
		}
	}
	
	public int getTurns() { return turns; }
	
	public void doTurn() {
		turns++;
		Collections.sort(players, Collections.reverseOrder());
		ArrayList<Card> wonCards = new ArrayList<>();
		resolveWar(players, wonCards);
		players.get(0).getHand().addAll(wonCards);
		players.removeIf((Player player) -> player.getHand().size() == 0);
	}
	
	public void resolveWar(ArrayList<Player> players, ArrayList<Card> cards) {
		if (players.size() == 1)
			return;
		Card highCard = players.get(0).getHand().get(0);
		ArrayList<Player> playersInWar = new ArrayList<>();
		for (Player player : players) {
			if (player.getHand().get(0).compareTo(highCard) == 0) {
				playersInWar.add(player);
				if (player.getHand().size() > 1)
					cards.add(player.getHand().poll());
			}
			else
				cards.add(player.getHand().poll());
		}
		if (playersInWar.size() > 1) {
			for (Player player : playersInWar) {
				for (int i=0; i<3; i++) {
					if (player.getHand().size() > 1)
						cards.add(player.getHand().poll());
				}
			}
		}
		resolveWar(playersInWar, cards);
	}
	
	public Player getWinner() {
		return players.size() == 1 ? players.get(0) : null;
	}
	
	public static void main(String[] args) {
		NewWar game = new NewWar(Integer.parseInt(args[0]));
		while (game.getWinner() == null) {
			game.doTurn();
		}
		System.out.println(game.getWinner().getName() + " wins.");
		System.out.println(("Turns: " + game.getTurns()));
	}
}