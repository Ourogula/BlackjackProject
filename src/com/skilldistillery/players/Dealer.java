package com.skilldistillery.players;

import java.util.Collections;

import com.skilldistillery.cardgame.BlackjackHand;
import com.skilldistillery.cardgame.Hand;
import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;

public class Dealer extends Player {

	private Deck deck = new Deck();

	// Dealer constructor with name Dealer
	public Dealer() {
		super("Dealer");
	}

	// Deal a fresh hand
	public Hand dealHand() {
		hand = new BlackjackHand();
		dealCard(hand);
		dealCard(hand);
		return hand;
	}

	// Deck getter
	public Deck getDeck() {
		return deck;
	}

	// Grab a fresh deck
	public void freshDeck() {
		deck = new Deck();
	}

	//Deal a card to a hand
	public Card dealCard(Hand hand) {
		Card removedCard = deck.getDeck().remove(0);
		hand.addCard(removedCard);
		return removedCard;
	}

	// Shuffle the current deck
	public void shuffleDeck() {
		Collections.shuffle(deck.getDeck());
	}

}
