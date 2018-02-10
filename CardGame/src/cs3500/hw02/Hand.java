package cs3500.hw02;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a player's cards - their hand The Hand is a list of cards the player is allowed to
 * play with
 */
public class Hand {

  ArrayList<Card> cards;
  static int numberOfPlayers = 0;
  // Added to Hand to keep score for the Whist game
  public int score = 0;


  /**
   * Constructs a Hand that is a list of Cards
   *
   * @param cards List of Card
   */
  public Hand(ArrayList<Card> cards) {
    this.cards = cards;
  }


  /**
   * Takes in a list of cards, and returns a sorted list of cards.
   *
   * @return a list of Card Objects
   */
  public List<Card> getHand() {
    Collections.sort(this.cards);
    return cards;
  }


  /**
   * Returns a String of the Hand
   *
   * @return the value and suit of all the cards in this Hand as a String Changed to public so
   * whistModel's getGameState can use it
   */
  public String handToString() {
    String result = "";
    Collections.sort(cards);
    for (int i = 0; i < cards.size(); i++) {
      result = result + " " + cards.get(i).cardToString();
      if (i != cards.size() - 1) {
        result = result + ",";
      }
    }
    return result;
  }

  /**
   * Adds a Card to a Hand
   *
   * @param card Card that is being added to Hand
   */
  public void addCard(Card card) {
    this.cards.add(card);
  }

  /**
   * Returns a boolean based on whether this hand contains the given suit or not Added for
   * WhistModel play method - in order to see if a player is able to play a card that matches the
   * trick's suit or not
   *
   * @param suit suit being compared to each card in the hand
   * @return true if this hand contains a card with the same suit as the given suit, and false if
   * this hand does not contain a card with the same suit
   */
  public boolean containsSuit(Suit suit) {
    boolean containsTheSuit = false;
    for (int i = 0; i < cards.size(); i++) {
      if (cards.get(i).retrieveSuit() == suit) {
        containsTheSuit = true;
      }
    }
    return containsTheSuit;
  }

}
