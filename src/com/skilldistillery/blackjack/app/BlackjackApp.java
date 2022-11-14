package com.skilldistillery.blackjack.app;

import java.util.Scanner;
import com.skilldistillery.cardgame.BlackjackHand;
import com.skilldistillery.players.*;

public class BlackjackApp {

	public static void main(String[] args) {

		BlackjackApp app = new BlackjackApp();

		app.run();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		Boolean playAgain;

		System.out.println("Welcome to the Blackjack Simulator!\n");

		// Run a game of blackjack, keep going while the player wants to
		do {
			playAgain = playBlackjack(sc);
		} while (playAgain);
		
		System.out.println("Thanks for playing the Blackjack Simulator!");

		sc.close();
	}

	private boolean playBlackjack(Scanner sc) {
		boolean playAgain = false;
		boolean playerWins = true;
		boolean blackjack = false;
		boolean tieGame = false;

		String response = "";
		BlackjackHand visibleDealerHand = new BlackjackHand();

		// Create the players
		//We create a new player and new dealer every round so that we get a fresh deck and fresh hands.
		//This also give the option to swap out a player, and the ability to name said player.
		//This was set up to allow for easy modification to add multiple players, and to emulate casinos where a fresh deck is always used
		//in order to prevent tampering or marking cards.
		Player player = new Player("Player");
		player.setHand(new BlackjackHand());
		Dealer dealer = new Dealer();
		dealer.setHand(new BlackjackHand());

		// Initialize the hands
		startGame(dealer, visibleDealerHand, player);

		// Check the starting hands for blackjack. If found, end the game.
		if (((BlackjackHand) dealer.getHand()).isBlackjack() && ((BlackjackHand) player.getHand()).isBlackjack()) {
			System.out.println("Push! Both players got blackjack, it's a tie!");
			tieGame = true;
		} else if (((BlackjackHand) dealer.getHand()).isBlackjack()) {
			System.out.println("The dealer got blackjack!");
			playerWins = false;
			blackjack = true;
		} else if (((BlackjackHand) player.getHand()).isBlackjack()) {
			System.out.println("The player got blackjack!");
			blackjack = true;
		}

		// If the player doesn't win on his turn, run the dealer's turn and save the
		// winner
		if (!blackjack && !tieGame) {
			if (!playerTurn(player, dealer, sc, visibleDealerHand, playerWins)) {
				// If the player busted, they lost!
				if (((BlackjackHand) player.getHand()).isBust()) {
					playerWins = false;
				} else {
					playerWins = dealerTurn(player, dealer);

					// If tie game, push and bypass the win statements
					if (dealer.getHand().getHandValue() == player.getHand().getHandValue()) {
						System.out.println("Push! Both players got a value of " + dealer.getHand().getHandValue()
								+ ". It's a tie!");
						tieGame = true;
					}
				}
			}
		}

		// Output who won the game
		if (!tieGame) {
			if (playerWins) {
				System.out.println("\n" + player.getName() + " has won the game!");
			} else {
				System.out.println("\n" + dealer.getName() + " has won the game!");
			}
		}

		// Check if the player wants to go again
		while (true) {
			System.out.println("Do you want to play again? [Y/N]");
			response = sc.nextLine();
			if (response.equalsIgnoreCase("y")) {
				playAgain = true;
				break;
			} else if (response.equalsIgnoreCase("n")) {
				break;
			} else {
				System.out.println("Please enter a valid response.");
			}
		}

		return playAgain;
	}

	private void startGame(Dealer dealer, BlackjackHand visibleDealerHand, Player player) {
		dealer.shuffleDeck();
		dealer.dealCard(player.getHand());
		dealer.dealCard(dealer.getHand());
		dealer.dealCard(player.getHand());
		dealer.dealCard(dealer.getHand());
		visibleDealerHand.getHandContents().add(dealer.getHand().getHandContents().get(1));
	}

	private boolean playerTurn(Player player, Dealer dealer, Scanner sc, BlackjackHand visibleDealerHand,
			boolean playerWins) {
		System.out.println("You were dealt:\n" + player.getHand());
		System.out.println();

		// If the player gets a blackjack, win the game instantly
		while (true) {
			// If player busts, end the game
			if (((BlackjackHand) player.getHand()).isBust()) {
				System.out.println("You bust with a value of: " + player.getHand().getHandValue());
				playerWins = false;
				break;
			}

			else if (((BlackjackHand) player.getHand()).is21()) {
				System.out.println("You have a hand value of 21. Proceeding to dealer's turn.");
				playerWins = false;
				break;
			}

			// Show the dealer's visible card
			System.out.println("Dealer's face up card:");
			System.out.println(visibleDealerHand);
			System.out.println(visibleDealerHand.getHandValue() + "\n");

			// Show player's current hand
			System.out.println("Your current hand: ");
			System.out.println(player.getHand());
			System.out.println(player.getHand().getHandValue() + "\n");

			// Choose to hit or stand
			System.out.println("Hit or Stand?");
			String response = sc.nextLine();

			// If hit, get a new card and loop again
			if (response.equalsIgnoreCase("hit")) {
				System.out.println("You were dealt: " + dealer.dealCard(player.getHand()));
			}
			// If stand, print the player's value and exit loop
			else if (response.equalsIgnoreCase("stand")) {
				System.out.println("You stand with a total of: " + player.getHand().getHandValue() + "\n");
				playerWins = false;
				break;
			}
			// If neither response is given, loops back through to get a valid response
			else {
				System.out.println("Please enter a valid response.\n");
			}
		}

		return playerWins;

	}

	private boolean dealerTurn(Player player, Dealer dealer) {
		// TODO Auto-generated method stub
		boolean playerWins = false;
		
		System.out.println("\nDealer's current hand: " + dealer.getHand());
		System.out.println(dealer.getHand().getHandValue() + "\n");
		
		while (dealer.getHand().getHandValue() < 17) {
			System.out.println("Dealt the dealer: " + dealer.dealCard(dealer.getHand()));
			System.out.println("Dealer's current hand: " + dealer.getHand());
			System.out.println(dealer.getHand().getHandValue());
		}
		if (((BlackjackHand) dealer.getHand()).isBust()) {
			System.out.println("The dealer busts with a value of: " + dealer.getHand().getHandValue());
			playerWins = true;
		} else if (dealer.getHand().getHandValue() < player.getHand().getHandValue()) {
			System.out.println("The dealer stands with a value of: " + dealer.getHand().getHandValue());
			playerWins = true;
		} else {
			System.out.println("The dealer stands with a value of: " + dealer.getHand().getHandValue());
		}

		return playerWins;
	}

}
