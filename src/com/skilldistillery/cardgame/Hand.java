package com.skilldistillery.cardgame;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.Card;

public abstract class Hand {
	
	protected List<Card> hand;
	
	public Hand () {
		hand = new ArrayList<>();
	}
	
	//Add a card to the hand
	public void addCard (Card card) {
		hand.add(card);
	}
	
	public List<Card> getHand () {
		return hand;
	}
	
	//Empty the current hand
	public void clear () {
		hand = null;
	}
	
	//Calculate value of current hand
	public abstract int getHandValue();
	
	public String toString() {
		String contents = "";
		if (!hand.isEmpty()) {
			for (int i = 0; i < hand.size(); i++) {
				contents += hand.get(i);
				if (i != hand.size() - 1) {
					contents += ", ";
				}
			}
		}
		return contents;
	}
}
