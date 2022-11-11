package com.skilldistillery.cards;

public enum Suit {
	HEARTS("Hearts"), CLUBS("Clubs"), DIAMONDS("Diamonds"), SPADES("Spades");
	
	private String name;
	
	Suit(String name) {
		this.name = name;
	}
	
	public String toString () {
		return name;
	}
}
