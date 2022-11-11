package com.skilldistillery.players;

import com.skilldistillery.cardgame.*;
import com.skilldistillery.cards.Deck;

public class Dealer extends Player {

	private Deck deck;
	private Hand dealtHand;
	
	//Dealer constructor with name and a new deck
	public Dealer (String name) {
		super(name);
		deck = new Deck();
	}
	
	//Deal a fresh hand
	public Hand dealHand () {
		dealtHand = new BlackjackHand();
		deck.dealCard(dealtHand);
		deck.dealCard(dealtHand);
		return dealtHand;
	}
	
	//Deck getter
	public Deck getDeck() {
		return deck;
	}

	//Dealt Hand getter
	public Hand getDealtHand() {
		return dealtHand;
	}
	
	//Grab a fresh deck
	public void freshDeck () {
		deck = new Deck();
	}
	
	//Shuffle the current deck
	public void shuffleDeck() {
		deck.shuffle();
	}
	
}
