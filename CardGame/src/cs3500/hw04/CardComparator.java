package cs3500.hw04;

import java.util.Comparator;

import cs3500.hw02.Card;

/**
 * Represents a CardComparator
 */
public class CardComparator implements Comparator<Card> {

  @Override
  public int compare(Card c1, Card c2) {
    return c1.compareTo(c2);
  }

}
