package cs3500.hw02;

import java.util.Comparator;

/**
 * Represents a single Card from a 52 card standard deck The card suits are Clubs, Diamonds,
 * Hearts, and Spades. Their "alphabetical order" is also in the order above. The card values
 * range from 1 to 13, 1 being an Ace, 11 being a Jack, 12 being a Queen, 13 being a King,
 * and 2-10 being their respective card values
 */
public class Card implements Comparable<Card> {

  // public fields so default package classes may access them
  public Suit suit;
  public Value value;

  /**
   * Constructs a Card with a specified value and suit, represented as an int
   *
   * @param value value of a card from 2-14 representing 2-10, J, Q, K, A
   * @param suit  suit of card from 1-4 representing Clubs, Diamonds, Hearts, and Spades
   */
  public Card(Value value, Suit suit) {
    this.value = value;
    this.suit = suit;

  }


  /**
   * Creates a card given a value and suit number
   *
   * @param valNum  value number ranging from 2-14
   * @param suitNum suit number ranging from 1-4
   * @return returns an object Card based on the parameters it is given
   */
  public static Card getCard(int valNum, int suitNum) {
    return new Card(Value.getValue(valNum), Suit.getSuit(suitNum));
  }

  /**
   * Compares this card with that card
   *
   * @param card card "that" that "this" is compared to
   * @return returns -1 if this card comes before that card
   *         returns 1 if that card comes before this card
   *         returns 0 if this card and that card are the same
   */
  @Override
  public int compareTo(Card card) {
    int suitDiff = this.suit.suitNum() - card.suit.suitNum();
    int valueDiff = this.value.valueNum() - card.value.valueNum();
    if (suitDiff < 0) {
      return -1;
    } else if (suitDiff > 0) {
      return 1;
    } else if (suitDiff == 0) {
      if (valueDiff > 0) {
        return -1;
      } else if (valueDiff < 0) {
        return 1;
      } else if (valueDiff == 0) {
        return 0;
      }
    }
    throw new IllegalArgumentException("Illegal card(s)");
  }

  /**
   * Returns a String of the Card
   *
   * @return the value and suit of a card in string form based on the Value and Suit classes
   */
  public String cardToString() {
    return value.toString() + suit.toString();
  }


  /**
   * Returns a suit of a Card
   *
   * @return the suit of the card
   */
  public Suit retrieveSuit() {
    return this.suit;
  }

  /**
   * Returns a value of a Card
   *
   * @return the value of the card
   */
  public Value retrieveValue() {
    return this.value;
  }

}