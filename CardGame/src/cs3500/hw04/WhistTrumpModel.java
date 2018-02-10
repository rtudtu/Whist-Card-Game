package cs3500.hw04;


import java.util.ArrayList;

import cs3500.hw02.Card;
import cs3500.hw02.Suit;
import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;

/**
 * Represents a Whist Game that includes Trumps and extends the methods of WhistModel
 */
public class WhistTrumpModel extends WhistModel implements CardGameModel<Card> {

  Suit trumpSuit; // The suit of the Trump for the game


  // empty constructor for WhistTrumpModel
  public WhistTrumpModel() {
    super();
    this.trumpSuit = getDeck().get(0).suit;

  }

  @Override
  public void play(int playerNo, int cardIdx) {
    // If the game is over, no player can play a card
    if (isGameOver()) {
      throw new IllegalArgumentException("Game is over, no player may play any card");
    }
    // The playerNo cannot be anything but the currentPlayer - only the currentPlayer can use the
    // play method and actually play a card.
    if (currentPlayer != playerNo) {
      throw new IllegalArgumentException("Not this player's turn or invalid playerNo, " +
              "please check getCurrentPlayer");
    }
    // The cardIdx given cannot be negative or greater than the player's hand size
    if (cardIdx >= hands.get(playerNo).getHand().size() || cardIdx < 0) {
      throw new IllegalArgumentException("cardIdx must be between 0 (first card) " +
              "and the size of your hand");
    }
    if (playerNo > hands.size() || playerNo < 0) {
      throw new IllegalArgumentException("playerNo must be between 0 (first player) " +
              "and the size of the hands(number of players)");
    }
    //For faster typing and better readability:
    currentHand = hands.get(playerNo);
    currentCard = hands.get(playerNo).getHand().get(cardIdx);

    // If the trick size is 0, then it is the first player's turn and they may play any card
    // The played card's suit becomes the trick's suit. Only allowing other players to win by
    // playing the same suit
    if (trick.size() == 0) {
      currentHand.getHand().remove(currentCard);
      trick.add(currentCard);
      winningHand = playerNo;
      winningCard = currentCard;
      trickSuit = currentCard.retrieveSuit();
      // If the current player plays a card matching the suit of the trick, then add their card
      // to the trick "pile" and determine if that card's value is higher than the winningCard's
      // value
    } else if (currentCard.retrieveSuit() == trickSuit) {
      currentHand.getHand().remove(currentCard);
      trick.add(currentCard);
      if (currentCard.retrieveValue().valueNum() >
              winningCard.retrieveValue().valueNum()) {
        winningHand = playerNo;
        winningCard = currentCard;
      }
      // If the current player plays a card that does not match the suit of the trick, and they do
      // not have a card with the same suit as the trick's suit, then add their card to the trick
      // but do not give them a chance to win the trick - winningCard does not change
    } else if (currentCard.retrieveSuit() != trickSuit) {
      //Throws exceptions if the player playing the card has a card that matches the trickSuit
      //but is not playing it
      if (currentHand.containsSuit(trickSuit) && currentCard.retrieveSuit() == trumpSuit) {
        throw new IllegalArgumentException("Can only play a Trump when the player does not" +
                " have a card matching the trick's current suit");
      } else if (currentHand.containsSuit(trickSuit)) {
        throw new IllegalArgumentException("Must play a card matching the trick's current suit");
      }
      // Added for Trump:
      if (currentCard.retrieveSuit() == trumpSuit && trickSuit == trumpSuit) {
        if (currentCard.retrieveValue().valueNum() > winningCard.retrieveValue().valueNum()) {
          winningHand = playerNo;
          winningCard = currentCard;
        }
      } else if (currentCard.retrieveSuit() == trumpSuit &&
              winningCard.retrieveSuit() == trumpSuit) {
        if (currentCard.retrieveValue().valueNum() > winningCard.retrieveValue().valueNum()) {
          winningHand = playerNo;
          winningCard = currentCard;
        }
      } else if (currentCard.retrieveSuit() == trumpSuit &&
              winningCard.retrieveSuit() != trumpSuit) {
        winningHand = playerNo;
        winningCard = currentCard;
      } else if (currentCard.retrieveSuit() != trickSuit &&
              currentCard.retrieveSuit() != trumpSuit) {
        //do nothing
      }
      currentHand.getHand().remove(currentCard);
      trick.add(currentCard);

    }
    // If the trick size is less than the hands.size, then that means there are still players
    // who haven't played yet. So advance the currentPlayer and mod it
    if (trick.size() < hands.

            size()

            )

    {
      currentPlayer = currentPlayer + 1;
      currentPlayer = currentPlayer % hands.size();
    }


    // If the trick's size reaches the hands size, then every player has played. Also, if the
    // winningHand's size is not empty, set the currentPlayer (next player's turn) to winningHand
    // If the winningHand does not have a card to play, the next if statement should run
    if (trick.size() >= hands.size() && hands.get(winningHand).

            getHand()

            .

                    size()

            != 0 &&
            hands.get(hands.size() - 1).

                    getHand()

                    .

                            size()

                    > 0)

    {
      hands.get(winningHand).score = hands.get(winningHand).score + 1;
      currentPlayer = winningHand;
      trick = new ArrayList<Card>();
    }
    // If the winner of the previous trick has run out of cards but other players can still play,
    // we want to jump to the 0th player's turn - the 0th player has to have the same or more
    // cards than other players after every trick
    if (trick.size() >= hands.size() && hands.get(winningHand).

            getHand()

            .

                    size()

            == 0)

    {
      hands.get(winningHand).score = hands.get(winningHand).score + 1;
      currentPlayer = 0;
      trick = new ArrayList<Card>();
      for (int i = 0; i < hands.size(); i++) {
        if (hands.get(i).getHand().size() == 1) {
          handsLeft = handsLeft + 1;
        }
      }
    }
    // If it is the last trick (last player's hand size is 0), then do the same thing as the
    // previous if statement, we want handsLeft to start counting as it is the last trick
    if (trick.size() >= hands.size() && hands.get(hands.size() - 1).

            getHand()

            .

                    size()

            == 0)

    {
      hands.get(winningHand).score = hands.get(winningHand).score + 1;
      currentPlayer = 0;
      trick = new ArrayList<Card>();
      for (int i = 0; i < hands.size(); i++) {
        if (hands.get(i).getHand().size() == 1) {
          handsLeft = handsLeft + 1;
        }
      }
    }
    // If the trick size is equal to the handsLeft, and the trick size is not 0, then we want
    // to end the trick as all the remaining players with cards left has played.
    if (trick.size() == handsLeft && trick.size() != 0)

    {
      hands.get(winningHand).score = hands.get(winningHand).score + 1;
      trick = new ArrayList<Card>();
    }

  }


  //getGameState - save javadoc, except this one returns the Trump Suit as well
  @Override
  public String getGameState() {
    return super.getGameState() + "\n" + "Trump Suit: " + trumpSuit.token;
  }


}
