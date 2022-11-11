package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.skilldistillery.cardgame.Hand;

public class Deck {
	private List<Card> deck;
	
	public Deck () {
		deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
	}
	
	public int checkDeckSize () {
		return deck.size();
	}
	
	public Card dealCard () {
		return deck.remove(0);
	}
	
	public void dealCard (Hand hand) {
		hand.addCard(deck.remove(0));
	}
	
	public void shuffle () {
		Collections.shuffle(deck);
	}
	
	public int size () {
		return deck.size();
	}

}
