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
		Player player = new Player("Player");
		player.setHand(new BlackjackHand());
		Dealer dealer = new Dealer();
		dealer.setHand(new BlackjackHand());

		// Initialize the hands
		startGame(player, dealer, visibleDealerHand);

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
					playerWins = dealerTurn(player, dealer, sc);
					
					//If tie game, push and bypass the win statements
					if (dealer.getHand().getHandValue() == player.getHand().getHandValue()) {
						System.out.println("Push! Both players got a value of " + dealer.getHand().getHandValue() + ". It's a tie!");
						tieGame = true;
					}
				}
			}
		}

		// Output who won the game
		if (!tieGame) {
			if (playerWins) {
				System.out.println(player.getName() + " has won the game!");
			} else {
				System.out.println(dealer.getName() + " has won the game!");
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

	private void startGame(Player player, Dealer dealer, BlackjackHand visibleDealerHand) {
		dealer.shuffleDeck();
		dealer.dealCard(player.getHand());
		dealer.dealCard(dealer.getHand());
		dealer.dealCard(player.getHand());
		dealer.dealCard(dealer.getHand());
		visibleDealerHand.getHand().add(dealer.getHand().getHand().get(1));
	}

	private boolean playerTurn(Player player, Dealer dealer, Scanner sc, BlackjackHand visibleDealerHand,
			boolean playerWins) {
		System.out.println("You were dealt:\n" + player.getHand());
		System.out.println();

		// If the player gets a blackjack, win the game instantly
		if (((BlackjackHand) player.getHand()).isBlackjack()) {
			System.out.println("Blackjack!");
			playerWins = true;
		} else {
			while (true) {
				// If player busts, end the game
				if (((BlackjackHand) player.getHand()).isBust()) {
					System.out.println("You bust with a value of: " + player.getHand().getHandValue());
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
		}

		return playerWins;
	}

	private boolean dealerTurn(Player player, Dealer dealer, Scanner sc) {
		// TODO Auto-generated method stub
		boolean playerWins = false;

		while (dealer.getHand().getHandValue() < 17) {
			System.out.println("Dealt the dealer: " + dealer.dealCard(dealer.getHand()));
			System.out.println("Dealer's current hand: " + dealer.getHand());
			System.out.println(dealer.getHand().getHandValue());
		}
		if (((BlackjackHand)dealer.getHand()).isBust()) {
			System.out.println("The dealer busts with a value of: " + dealer.getHand().getHandValue());
			playerWins = true;
		}
		else if (dealer.getHand().getHandValue() < player.getHand().getHandValue()) {
			System.out.println("The dealer stands with a value of: " + dealer.getHand().getHandValue());
			playerWins = true;
		} 
		else {
			System.out.println("The dealer stands with a value of: " + dealer.getHand().getHandValue());
		}

		return playerWins;
	}

}
