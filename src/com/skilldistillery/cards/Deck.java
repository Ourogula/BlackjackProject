package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Card> getDeck() {
		return deck;
	}

	public int size () {
		return deck.size();
	}

}
