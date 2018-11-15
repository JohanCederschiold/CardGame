package cardgame;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	private ArrayList <Card> cardDeck = new ArrayList<>();
	private String [] färger = {"Hjärter", "Ruter", "Spader", "Klöver"};
	private String [] valörer = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Knekt", "Dam", "Kung", "Ess"};
	private int [] värde = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	
	public Deck () {
		createCardDeck();
	}
	
	
	
	public void createCardDeck ( ) {
		
		int deckCounter = 0;
		
		for (String färg : färger) {
			//int counter = 0;
			for (int i = 0 ; i < valörer.length ; i++) {
				
				Card card = new Card();
				card.setFärg(färg);
				card.setValör(valörer[i]);
				card.setVärde(värde[i]);
				cardDeck.add(card);
				deckCounter++;
				

				
			}
		}
	}
	
	public void printCardDeck () {
		
		for (Card card : cardDeck) {
			System.out.println(card.toString());
		}
		
	}
	
	public List<Card> getCardDeck () {
		return cardDeck;
	}
	




}
