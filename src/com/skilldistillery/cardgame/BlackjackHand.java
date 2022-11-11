package com.skilldistillery.cardgame;

import com.skilldistillery.cards.Card;

public class BlackjackHand extends Hand {

	public BlackjackHand() {

	}

	public int getHandValue() {
		int value = 0;
		if (hand != null) {
			for (Card card : hand) {
				value += card.getValue();
			}
		}
		return value;
	}
	
	public boolean isBlackjack () {
		boolean blackjack = false;
		if (hand.size() == 2 && getHandValue() == 21) {
			blackjack = true;
		}
		return blackjack;
	}
	
	public boolean isBust() {
		boolean bust = false;
		if (getHandValue() > 21) {
			bust = true;
		}
		return bust;
	}
}
