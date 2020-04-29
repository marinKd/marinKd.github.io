import java.util.*;
import java.util.Scanner;

//enum Suit {SPADE, HEART, DIAMOND, CLUB};
class Card implements Comparable<Card>{
	private Rank rank;
	private Suit suit;
	private String name;
	private int value;
	public Card(Rank rank, Suit suit){
		this.rank=rank;
		this.suit=suit;
		this.value=rank.getRankValue();
		name = rank.toString()+" of "+suit.toString();
	}
	public Card(Card c){
		rank=c.rank;
		suit=c.suit;
		value=c.rank.getRankValue();
		name=rank.toString()+" of "+suit.toString();
	}
	public int getValue(){
		return value;
	}
	public boolean equals(Card c){
		return rank.equals(c.rank)&&suit.equals(c.suit);		
	}
	public boolean equalsSuit(Card c){
		return suit.equals(c.suit);
	}
	public int compareTo(Card c){
		if(rank.rankValue==c.rank.rankValue){
			return 0;
		}else if(rank.rankValue<c.rank.rankValue){
			return -1;
		}else {
			return 1;
		}
	}
	public void copy(Card c){
		rank=c.rank;
		suit=c.suit;
	}
	public String toString(){
		return name;
	}
}
class Rank{
	protected int rankValue;
	protected String rankName;
	public Rank(int rankValue){
		this.rankValue=rankValue;
		if(rankValue==0){
			rankName = "2";
		}
		if(rankValue<=8&&rankValue!=0){
			rankName = Integer.toString(rankValue+2);
		}else if(rankValue==9){
			rankName = "J";
		}else if(rankValue==10){
			rankName = "Q";
		}else if(rankValue==11){
			rankName = "K";
		}else if(rankValue==12){
			rankName = "A";
		}
	}
	public boolean equals(Rank c){
		return rankValue==c.rankValue;
	}
	public int getRankValue(){
		return rankValue;
	}
	public String toString(){
		return rankName;
	}
}
class Suit{
	private String name;
	private int value;
	public Suit(String name){
		this.name=name;
		this.value=value;
	}
	public String toString(){
		return name;
	}
	public boolean equals(Suit s){
		return this.name.equals(s.name);
	}
}
class Heart extends Suit{
	private int value;
	public Heart(){
		super("Hearts");
	}
}
class Spade extends Suit{
	private int value;
	public Spade(){
		super("Spades");
	}
}
class Diamond extends Suit{
	private int value;
	public Diamond(){
		super("Diamonds");
	}
}
class Club extends Suit{
	private int value;
	public Club(){
		super("Clubs");
	}
}
class Player{
	private String name;
	private int score;
	private ArrayList<Card> hand;
	private ArrayList<Card> bag;
	public Player(String name){
		this.name=name;
		score=0;
	}
	/*public Player(Player p){
		Player(p.name);
	}*/
	public void setScore(int score){
		this.score=score;
	}
	public int getScore(){
		return score;
	}
	public void setHand(ArrayList<Card> hand){
		this.hand=hand;
	}
	public void takesTrick(ArrayList<Card> trick){
		for(int i=0;i<trick.size();i++){
			//bag.add(trick.get(i));
			score += trick.get(i).getValue();
		}
	}
	public void showHand(){
		for(int i=0;i<hand.size();i++){
			System.out.println(i+" - "+hand.get(i).toString());
		}
	}
	public void showBag(){
		for(int i=0;i<bag.size();i++){
			System.out.println(i+" - "+bag.get(i).toString());
		}
	}
	public int getHandLength(){
		return hand.size();
	}
	public String getName(){
		return "Player "+name;
	}
	public Card getCard(int i){
		return hand.get(i);
	}
	public Card getCard(Scanner stdin){
		int i = stdin.nextInt();
		Card temp = hand.get(i);
		hand.remove(i);
		return temp;
	}
	public void cardImport(Card[] importedCards){
		for(int i=0;i<=2;i++){
			hand.add(importedCards[i]);
		}
	}
	public void deleteCard(int i){
		hand.remove(i);
	}
	public Card playCard(Scanner stdin){
		int i = stdin.nextInt();
		Card temp = hand.get(i);
		hand.remove(i);
		return temp;
	}
	public Card playCard(int i){
		Card temp = hand.get(i);
		hand.remove(i);
		return temp;
	}
}
class Deck{
	private Card[] deck;
	public Deck(){
		deck = new Card[52];
		for(int i=0;i<=12;i++){
			deck[i] = new Card(new Rank(i), new Spade());
		}
		for(int i=0;i<=12;i++){
			deck[i+13] = new Card(new Rank(i), new Heart());
		}
		for(int i=0;i<=12;i++){
			deck[i+26] = new Card(new Rank(i), new Diamond());
		}
		for(int i=0;i<=12;i++){
			deck[i+39] = new Card(new Rank(i), new Club());
		}
		Random rand = new Random();
		for(int i=0;i<deck.length;i++){
			int randomIndexToSwap = rand.nextInt(deck.length);
			Card temp = deck[randomIndexToSwap];
			deck[randomIndexToSwap] = deck[i];
			deck[i] = temp;
		}
	}
	public void shuffle(){
		for(int i=0;i<=12;i++){
			deck[i] = new Card(new Rank(i), new Spade());
		}
		for(int i=0;i<=12;i++){
			deck[i+13] = new Card(new Rank(i), new Heart());
		}
		for(int i=0;i<=12;i++){
			deck[i+26] = new Card(new Rank(i), new Diamond());
		}
		for(int i=0;i<=12;i++){
			deck[i+39] = new Card(new Rank(i), new Club());
		}
		Random rand = new Random();
		for(int i=0;i<deck.length;i++){
			int randomIndexToSwap = rand.nextInt(deck.length);
			Card temp = deck[randomIndexToSwap];
			deck[randomIndexToSwap] = deck[i];
			deck[i] = temp;
		}
	}
	public Card[] getDeck(){
		return deck;
	}
	public Card getCard(int i){
		return deck[i];
	}
}
class Game{
	private int[] scoreBoard;
	private Player[] players;
	private int firstPlayer;
	private int roundCounter;
	private int exchangeRoundCounter;
	private Scanner stdin;
	private Deck deck;
	public Game(){
		deck = new Deck();
		exchangeRoundCounter=0;
		roundCounter=0;
		firstPlayer=0;
		scoreBoard = new int[4];
		for(int i=0;i<=3;i++){
			scoreBoard[i]=0;
		}
		players = new Player[4];
		players[0] = new Player("One");
		players[1] = new Player("Two");
		players[2] = new Player("Three");
		players[3] = new Player("Four");
		stdin = new Scanner(System.in);
	}
	public void newGame(){
		deck.shuffle();
		deal();
		while(!endGame()){
			round();
		}
	}
	public void round(){
		exchange();
		ArrayList<Card> trick = new ArrayList<Card>();
		if(roundCounter==0){
			for(int i=0;i<=3;i++){
				System.out.println(players[i].getName());
				System.out.println("");
				players[i].showHand();
			}
			System.out.println("Player with the 2 of clubs makes the first move");
			firstPlayer=0;
			//int cardToDelete=0;
			for(int i=0;i<=3;i++){
				for(int n=0;n<players[i].getHandLength();n++){
					if(players[i].getCard(n).equals(new Card(new Rank(0), new Club()))){
						firstPlayer = i;
						System.out.println(players[i].getName()+", your turn");
						players[i].showHand();
						trick.add(players[i].playCard(stdin));	
						//cardToDelete = n;
					}
				
				}
			}
		}
		int playedCard=0;
		int playerWithHighestCard=0;
		Card highestCard = new Card(new Rank(0), new Club());
		if(firstPlayer==3){
			for(int i=0;i<=2;i++){
				System.out.println(players[i].getName()+", your turn");
				players[i].showHand();
				playedCard=stdin.nextInt();
				if(players[i].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[i].getCard(playedCard));
					playerWithHighestCard=i;
				}
				trick.add(players[i].playCard(playedCard));
				
			}
		}
		else if(firstPlayer==0){
			for(int i=1;i<=3;i++){
				System.out.println(players[i].getName()+", your turn");
				players[i].showHand();
				playedCard=stdin.nextInt();
				if(players[i].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[i].getCard(playedCard));
					playerWithHighestCard=i;
				}
				trick.add(players[i].playCard(playedCard));
			}
		}
		else if(firstPlayer==1){
			for(int i=2;i<=3;i++){
				System.out.println(players[i].getName()+", your turn");
				players[i].showHand();
				playedCard=stdin.nextInt();
				if(players[i].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[i].getCard(playedCard));
					playerWithHighestCard=i;
				}
				trick.add(players[i].playCard(playedCard));
			}
				System.out.println(players[0].getName()+", your turn");
				players[0].showHand();
				playedCard=stdin.nextInt();
				if(players[0].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[0].getCard(playedCard));
					playerWithHighestCard=0;
				}
				trick.add(players[0].playCard(playedCard));
		}
		else if(firstPlayer==2){
				System.out.println(players[3].getName()+", your turn");
				players[3].showHand();
				playedCard=stdin.nextInt();
				if(players[3].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[3].getCard(playedCard));
					playerWithHighestCard=3;
				}
				trick.add(players[3].playCard(playedCard));
			for(int i=0;i<=1;i++){
				System.out.println(players[i].getName()+", your turn");
				players[i].showHand();
				playedCard=stdin.nextInt();
				if(players[i].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[i].getCard(playedCard));
					playerWithHighestCard=i;
				}
				trick.add(players[i].playCard(playedCard));
			}
		}
		for(int i=0;i<trick.size();i++){
			System.out.println(trick.get(i).toString());
		}
		firstPlayer=playerWithHighestCard;
		players[playerWithHighestCard].takesTrick(trick);
		updateScoreboard();
		printScoreboard();
	}
	/*public void shuffle(){
		for(int i=0;i<=12;i++){
			deck[i] = new Card(new Rank(i), new Spade());
		}
		for(int i=0;i<=12;i++){
			deck[i+13] = new Card(new Rank(i), new Heart());
		}
		for(int i=0;i<=12;i++){
			deck[i+26] = new Card(new Rank(i), new Diamond());
		}
		for(int i=0;i<=12;i++){
			deck[i+39] = new Card(new Rank(i), new Club());
		}
		Random rand = new Random();
		for(int i=0;i<deck.length;i++){
			int randomIndexToSwap = rand.nextInt(deck.length);
			Card temp = deck[randomIndexToSwap];
			deck[randomIndexToSwap] = deck[i];
			deck[i] = temp;
		}
	}*/
	public void updateScoreboard(){
		for(int i=0;i<=3;i++){
			scoreBoard[i]=players[i].getScore();
		}
	}
	public void printScoreboard(){
		for(int i=0;i<=3;i++){
			System.out.println(scoreBoard[i]);
		}
	}
	public void deal(){
			for(int i=0;i<=3;i++){
				ArrayList<Card> dealer = new ArrayList<Card>();
				for(int n=0;n<=12;n++){			
					dealer.add(deck.getCard(n+(13*i)));
					}
				players[i].setHand(dealer);
				System.out.println("--------");
				System.out.println(players[i].getName());
				System.out.println("");
				players[i].showHand();
			}
		}

	public void exchange(){
		if(exchangeRoundCounter>3){
			exchangeRoundCounter=0;
			}
		if(exchangeRoundCounter==0){
			System.out.println("Player One, pick 3 cards to give to player Four");
			players[0].showHand();
			Card[] cardsOne = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsOne[i] = players[0].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[0].getCard(cardIndex).toString());
				players[0].deleteCard(cardIndex);
				players[0].showHand();
			}
			System.out.println("Player Four, pick 3 cards to give to player Three");
			players[3].showHand();
			Card[] cardsFour = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsFour[i] = players[3].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[3].getCard(cardIndex).toString());
				players[3].deleteCard(cardIndex);
				players[3].showHand();
			}
			System.out.println("Player Three, pick 3 cards to give to player Two");
			players[2].showHand();
			Card[] cardsThree = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsThree[i] = players[2].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[2].getCard(cardIndex).toString());
				players[2].deleteCard(cardIndex);
				players[2].showHand();
			}
			System.out.println("Player Two, pick 3 cards to give to player One");
			players[1].showHand();
			Card[] cardsTwo = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsTwo[i] = players[1].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[1].getCard(cardIndex).toString());
				players[1].deleteCard(cardIndex);
				players[1].showHand();
			}

			players[0].cardImport(cardsTwo);
			players[1].cardImport(cardsThree);
			players[2].cardImport(cardsFour);
			players[3].cardImport(cardsOne);
		}
		else if(exchangeRoundCounter==1){
			System.out.println("Player One, pick 3 cards to give to player Two");
			players[0].showHand();
			Card[] cardsOne = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsOne[i] = players[0].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[0].getCard(cardIndex).toString());
				players[0].deleteCard(cardIndex);
				players[0].showHand();
			}
			System.out.println("Player Two, pick 3 cards to give to player Three");
			players[1].showHand();
			Card[] cardsTwo = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsTwo[i] = players[1].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[1].getCard(cardIndex).toString());
				players[1].deleteCard(cardIndex);
				players[1].showHand();
			}
			System.out.println("Player Three, pick 3 cards to give to player Four");
			players[2].showHand();
			Card[] cardsThree = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsThree[i] = players[2].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[2].getCard(cardIndex).toString());
				players[2].deleteCard(cardIndex);
				players[2].showHand();
			}
			System.out.println("Player Four, pick 3 cards to give to player One");
			players[3].showHand();
			Card[] cardsFour = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsFour[i] = players[3].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[3].getCard(cardIndex).toString());
				players[3].deleteCard(cardIndex);
				players[3].showHand();
			}
			players[0].cardImport(cardsFour);
			players[1].cardImport(cardsOne);
			players[2].cardImport(cardsTwo);
			players[3].cardImport(cardsThree);
		}
		else if(exchangeRoundCounter==3){
			System.out.println("Player One, pick 3 cards to give to player Yhree");
			players[0].showHand();
			Card[] cardsOne = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsOne[i] = players[0].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[0].getCard(cardIndex).toString());
				players[0].deleteCard(cardIndex);
				players[0].showHand();
			}
			System.out.println("Player Two, pick 3 cards to give to player Four");
			players[1].showHand();
			Card[] cardsTwo = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsTwo[i] = players[1].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[1].getCard(cardIndex).toString());
				players[1].deleteCard(cardIndex);
				players[1].showHand();
			}
			System.out.println("Player Three, pick 3 cards to give to player One");
			players[2].showHand();
			Card[] cardsThree = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsThree[i] = players[2].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[2].getCard(cardIndex).toString());
				players[2].deleteCard(cardIndex);
				players[2].showHand();
			}
			System.out.println("Player Four, pick 3 cards to give to player Two");
			players[3].showHand();
			Card[] cardsFour = new Card[3];
			for(int i=0;i<=2;i++){
				int cardIndex = stdin.nextInt();
				cardsFour[i] = players[3].getCard(cardIndex);
				System.out.println("Given cards:");
				System.out.println(players[3].getCard(cardIndex).toString());
				players[3].deleteCard(cardIndex);
				players[3].showHand();
			}
			players[0].cardImport(cardsThree);
			players[1].cardImport(cardsFour);
			players[2].cardImport(cardsOne);
			players[3].cardImport(cardsTwo);
		}
		exchangeRoundCounter++;
	}
	public boolean endGame(){
		for(int i=0;i<=3;i++){
			if(players[i].getScore()>=100){
				return true;
			}
		}
		return false;
	}
}
public class hearts{

public static void main(String[] args){
	
	System.out.println("------Hearts------");
	System.out.println();
	System.out.println("Choose option by typing key");
	System.out.println("A - New game");
	System.out.println("Q - Quit");
	Scanner stdin = new Scanner(System.in);
	String input = stdin.next();
	while(!input.equalsIgnoreCase("Q")){
		if(input.equalsIgnoreCase("A")){
			Game gameOne = new Game();
			gameOne.newGame();
		}
	}
	// in game class:
	/*	Player[] players = new Player[4];
		players[0] = new Player("One");
		players[1] = new Player("Two");
		players[2] = new Player("Three");
		players[3] = new Player("Four");
		int[] scoreBoard = new int[4];
		Card[] deck = new Card[52];
		for(int i=0;i<=12;i++){
			deck[i] = new Card(new Rank(i), new Spade());
		}
		for(int i=0;i<=12;i++){
			deck[i+13] = new Card(new Rank(i), new Heart());
		}
		for(int i=0;i<=12;i++){
			deck[i+26] = new Card(new Rank(i), new Diamond());
		}
		for(int i=0;i<=12;i++){
			deck[i+39] = new Card(new Rank(i), new Club());
		}
		Random rand = new Random();
		for(int i=0;i<deck.length;i++){
			int randomIndexToSwap = rand.nextInt(deck.length);
			Card temp = deck[randomIndexToSwap];
			deck[randomIndexToSwap] = deck[i];
			deck[i] = temp;
		}
		//To print deck:
		//for(int i=0;i<deck.length;i++){
		//	System.out.println(deck[i].toString());
		//}
		for(int i=0;i<=3;i++){
			ArrayList<Card> deal = new ArrayList<Card>();
			for(int n=0;n<=12;n++){			
				deal.add(deck[n+(13*i)]);
				}
			players[i].setHand(deal);
			System.out.println("--------");
			System.out.println(players[i].getName());
			System.out.println("");
			players[i].showHand();
		}
		System.out.println("Player One, pick 3 cards to give to player Four");
		players[0].showHand();
		Card[] cardsOne = new Card[3];
		for(int i=0;i<=2;i++){
			int cardIndex = stdin.nextInt();
			cardsOne[i] = players[0].getCard(cardIndex);
			System.out.println("Given cards:");
			System.out.println(players[0].getCard(cardIndex).toString());
			players[0].deleteCard(cardIndex);
			players[0].showHand();
		}
		System.out.println("Player Four, pick 3 cards to give to player Three");
		players[3].showHand();
		Card[] cardsFour = new Card[3];
		for(int i=0;i<=2;i++){
			int cardIndex = stdin.nextInt();
			cardsFour[i] = players[3].getCard(cardIndex);
			System.out.println("Given cards:");
			System.out.println(players[3].getCard(cardIndex).toString());
			players[3].deleteCard(cardIndex);
			players[3].showHand();
		}
		System.out.println("Player Three, pick 3 cards to give to player Two");
		players[2].showHand();
		Card[] cardsThree = new Card[3];
		for(int i=0;i<=2;i++){
			int cardIndex = stdin.nextInt();
			cardsThree[i] = players[2].getCard(cardIndex);
			System.out.println("Given cards:");
			System.out.println(players[2].getCard(cardIndex).toString());
			players[2].deleteCard(cardIndex);
			players[2].showHand();
		}
		System.out.println("Player Two, pick 3 cards to give to player One");
		players[1].showHand();
		Card[] cardsTwo = new Card[3];
		for(int i=0;i<=2;i++){
			int cardIndex = stdin.nextInt();
			cardsTwo[i] = players[1].getCard(cardIndex);
			System.out.println("Given cards:");
			System.out.println(players[1].getCard(cardIndex).toString());
			players[1].deleteCard(cardIndex);
			players[1].showHand();
		}

		players[0].cardImport(cardsTwo);
		players[1].cardImport(cardsThree);
		players[2].cardImport(cardsFour);
		players[3].cardImport(cardsOne);
		
	*/
	/*
		for(int i=0;i<=3;i++){
			System.out.println(players[i].getName());
			System.out.println("");
			players[i].showHand();
		}
		ArrayList<Card> trick = new ArrayList<Card>();
		System.out.println("Player with the 2 of clubs makes the first move");
		int firstPlayer=0;
		//int cardToDelete=0;
		for(int i=0;i<=3;i++){
			for(int n=0;n<players[i].getHandLength();n++){
				if(players[i].getCard(n).equals(new Card(new Rank(0), new Club()))){
					firstPlayer = i;
					System.out.println(players[i].getName()+", your turn");
					players[i].showHand();
					trick.add(players[i].playCard(stdin));	
					//cardToDelete = n;
				}
				
			}
		}
		//players[firstPlayer].deleteCard(cardToDelete);
		//Card highestCard = new Card();
		int playedCard=0;
		int playerWithHighestCard=0;
		Card highestCard = new Card(new Rank(0), new Club());
		if(firstPlayer==3){
			for(int i=0;i<=2;i++){
				System.out.println(players[i].getName()+", your turn");
				players[i].showHand();
				playedCard=stdin.nextInt();
				if(players[i].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[i].getCard(playedCard));
					playerWithHighestCard=i;
				}
				trick.add(players[i].playCard(playedCard));
				
			}
		}
		else if(firstPlayer==0){
			for(int i=1;i<=3;i++){
				System.out.println(players[i].getName()+", your turn");
				players[i].showHand();
				playedCard=stdin.nextInt();
				if(players[i].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[i].getCard(playedCard));
					playerWithHighestCard=i;
				}
				trick.add(players[i].playCard(playedCard));
			}
		}
		else if(firstPlayer==1){
			for(int i=2;i<=3;i++){
				System.out.println(players[i].getName()+", your turn");
				players[i].showHand();
				playedCard=stdin.nextInt();
				if(players[i].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[i].getCard(playedCard));
					playerWithHighestCard=i;
				}
				trick.add(players[i].playCard(playedCard));
			}
				System.out.println(players[0].getName()+", your turn");
				players[0].showHand();
				playedCard=stdin.nextInt();
				if(players[0].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[0].getCard(playedCard));
					playerWithHighestCard=0;
				}
				trick.add(players[0].playCard(playedCard));
		}
		else if(firstPlayer==2){
				System.out.println(players[3].getName()+", your turn");
				players[3].showHand();
				playedCard=stdin.nextInt();
				if(players[3].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[3].getCard(playedCard));
					playerWithHighestCard=3;
				}
				trick.add(players[3].playCard(playedCard));
			for(int i=0;i<=1;i++){
				System.out.println(players[i].getName()+", your turn");
				players[i].showHand();
				playedCard=stdin.nextInt();
				if(players[i].getCard(playedCard).compareTo(highestCard)==1){
					highestCard.copy(players[i].getCard(playedCard));
					playerWithHighestCard=i;
				}
				trick.add(players[i].playCard(playedCard));
			}
		}
		for(int i=0;i<trick.size();i++){
			System.out.println(trick.get(i).toString());
		}
		players[playerWithHighestCard].takesTrick(trick);
		System.out.println(players[playerWithHighestCard].getScore());
	}
	*/
}
}
