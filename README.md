##Blackjack Project

##Description
This program simulates the core mechanics of blackjack. A player and a dealer are both dealt two cards, and the goal is to get to a value of 21 without going over 21. If either the player or the dealer is dealt blackjack (21 on initial deal) the game ends immediately. If both the dealer and the player get blackjack, the game is a push (tie). If either the dealer or the player get blackjack by themselves, they automatically win the game. Only the face up card of the dealer is visible to the player, and their entire hand is only shown when either the dealer gets blackjack, or the player ends their turn.

Proceeding with the game, a player is given the option to hit(by typing hit) or stand(by typing stand) with their initial hand. If they get over 21, they bust and automatically lose the game. If they get to 21 with their hit, they automatically end their turn and the dealer takes their turn. A player can stand at any value below 21 and automatically proceed to the dealer's turn with their current hand value. 

The dealer follow two rules: if they have a hand value of less than 17, they must hit; if the dealer has a hand value of 17 or greater, they must stand. 

After the dealer's turn, if neither player busts or has blackjack, the player with the highest value hand wins the game.

This game goes in a loop, with each loop providing a fresh deck and fresh hands to prevent any kind of counting cards or marking cards. If the player would like to exit the loop, they simply need to enter 'y' or 'n' at the end of a given game.

##Lessons Learned
This program showed how useful in practice but painful in design OOP concepts can be. Many of the conditionals in the game relied on strings of 2 method calls, namely getting a player's hand, and then doing something with that hand to get a resulting output. Most of the value check were done by the BlackjackHand class, but there was no way to transfer the core logic of the blackjack game outside of the respective player's turns. 

All in all, the biggest lesson learned from this blackjack program was that a good UML diagram, or even just theorycrafting what each class needs to be doing, can save one a lot of time when actually writing things out. A lot of times I would need to look back at each class to recall what the classes had access to, and to make sure they were return the right value type. One big one was for getHand() to return a Hand  object instead of the List<Cards> that the hand consisted of. I use the Hand objects to retain their method calls, but I could have easily gone the route of manipulating the List of Cards through another class or even the dealer.

##Technologies Used
- Java
- Git