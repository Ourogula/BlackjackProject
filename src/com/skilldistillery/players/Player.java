package com.skilldistillery.players;

import com.skilldistillery.cardgame.Hand;

public class Player {
	
	private Hand hand;
	private String name;
	
	public Player (String name)  {
		this.name = name;
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hand getHand() {
		return hand;
	}
}
