package cardgame;

import java.util.ArrayList;
import static javax.swing.JOptionPane.*;
import java.util.List;
import java.util.Random;

public class BlackJackGame {
	
	//Kort och kortleksvariabler
	Deck blackJackDeck;
	List<Card> blackJackCards;
	List<Card> playerCards = new ArrayList<>();
	List<Card> dealerCards = new ArrayList<>();
	
	//Håller reda på hur mycket spelarna är upp i. 
	private int playerScore = 0;
	private int dealerScore = 0;
	
	private final int BUST = 21;
	
	
	public BlackJackGame () { //Konstruktor
		
		blackJackDeck = new Deck();
		blackJackCards = blackJackDeck.getCardDeck();
		
		boolean spelaVidare = true;
		
		while (spelaVidare) {
			startRound();
			int val = showConfirmDialog(null, "Vill du spela vidare?");
			if (val != 0) {
				spelaVidare = false;
			}
		}
		
		
		

	}
	
	
	
	private int startRound () {
		
		checkReshuffleNeeded(); //Check if remaining cards is sufficient for round
		
		int winner = 0;
		
		playerCards.clear();
		dealerCards.clear();
		
		dealCardToDealer(2);
		dealCardToPlayer(2);
		
		int choice = 0;
		
		while (playerScore <= BUST && choice == 0) {
			choice = showConfirmDialog(null, getCardsAsString() + "\n\n\nVill du ha ett kort till?\n");
			if (choice == 0 ) { //Spelaren väljer att de vill ha ett kort till
				dealCardToPlayer(); //Ger spelaren ytterligare ett kort
			} else if (choice == 3) {
				System.exit(0);
			}
			
			if (playerScore > BUST) { //Om spelaren spricker sänks eventuella ess till värde 1
				checkAces(playerCards);
				updatePlayerScore();
			}

		}
		
		//Om spelaren sprack meddelas denne om sin förlust. 
		if (playerScore > BUST) {
			showMessageDialog(null, getCardsAsString() + "\n\n\nDu sprack!!!\n");
		}
		
		if (playerScore <= BUST) {
			//Dealerns tur under förutsättning att spelaren inte spräckt sig. 
			while (dealerScore < 17) {
				dealCardToDealer();
				showMessageDialog(null, getCardsAsString() + "\n\n\nDealern tog ett kort\n");
				
				if (dealerScore >= BUST) {
					checkAces(dealerCards);
					updateDealerScore();
				}
				
			}
			
		}
		
		//Check who wins. 1 = player 2 = dealer 0 = divided. 
		if (playerScore <= BUST && playerScore > dealerScore || dealerScore > BUST) {
			winner = 1;
		} else if (dealerScore <= BUST && dealerScore > playerScore || playerScore > BUST) {
			winner = 2;
		} else {
			winner = 0;
		}
		
		System.out.println("DealerScore: " + dealerScore);
		System.out.println("PlayerScore: " + playerScore);
		System.out.println(winner);
		

		return winner;
	}
	
	
	

	private Card getRandomCard () {
		
		//Chooses a random card from the deck and removes it from the deck (to be added to the players deck).
		
		Random random = new Random();
		int chosenCard = random.nextInt(blackJackCards.size());
		Card card = blackJackCards.get(chosenCard);
		blackJackCards.remove(chosenCard);
		
		return card;

	}
	
	
	private void dealCardToPlayer () {
		dealCardToPlayer(1);
	}
	
	private void dealCardToPlayer (int noCards) {
		//Deals card an updates score. 
		for (int count = 0; count < noCards ; count++ ) {
			Card playerCard = getRandomCard();
			playerCards.add(playerCard);
			updatePlayerScore();
		}

	}
	
	private void dealCardToDealer () {
		dealCardToDealer(1);

	}
	
	private void dealCardToDealer (int noCards) {
		//Deals card an updates score. 
		for (int count = 0; count < noCards ; count++ ) {
			Card dealerCard = getRandomCard();
			dealerCards.add(dealerCard);
			updateDealerScore();
		}

	}
	
	private String getCardsAsString () {
		
		//Created a table with the current standings. Returned as a string so other content can be added. 
		
		String cardString = "Dealer:\n-------\n";
		
		for (int i = 0 ; i < dealerCards.size() ; i++ ) {
			cardString += dealerCards.get(i) + "\n";
		}
		
		cardString += "-------\nVärde: " + dealerScore + "\n\n\n\nPlayer\n-------\n";
		
		
		
		for (int i = 0 ; i < playerCards.size() ; i++ ) {
			cardString += playerCards.get(i) + "\n";
		}
		
		cardString += "-------\nVärde: " + playerScore + "\n\n"; 
		
		return cardString;
		
	}
	
	private void updatePlayerScore () {
		
		//Counts the value of the cards for the player. 
		
		int score = 0;
		
		for (Card card : playerCards) {
			score += card.getVärde();
		}
		
		playerScore = score;
		
	}
	
	private void updateDealerScore () {
		
		//Counts the value of the cards for the player. 
		
		int score = 0;
		
		for (Card card : dealerCards) {
			score += card.getVärde();
		}
		
		dealerScore = score;
		
	}
	
	private void checkAces (List<Card> list) {
		
		//This method should be called when a player goes bust. It checks if the player has any aces and set the 
		//value from 11 to 1 if that is the case - thus avoiding a bust. 
		
		for (Card card : list) {
			if (card.getVärde() == 11) {
				card.setVärde(1);
			}
		}
		
	}
	
	private void checkReshuffleNeeded () {
		
		//The method checks if the remaining cards are sufficient for one more round. If not a new deck i shuffled. 
		//Should be called before the start of a round.
		
		int shuffleLimit = 2 * 21; //Two players that should get at least 21. 
		int netSumOfDeck = 0;
		
		for (Card card : blackJackCards) {
			netSumOfDeck += card.getVärde();
		}
		
		if (netSumOfDeck < shuffleLimit) {
			blackJackDeck.createCardDeck();
			blackJackCards = blackJackDeck.getCardDeck();
		} 

	}
	

	

	
	
	

}
