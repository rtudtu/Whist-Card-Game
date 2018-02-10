import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import cs3500.hw02.Card;
import cs3500.hw02.Hand;
import cs3500.hw02.Suit;
import cs3500.hw02.Value;

// Test Hand methods
public class HandTest {

  Card AceSpades = new Card(Value.Ace, Suit.Spades);
  Card KingHearts = new Card(Value.King, Suit.Hearts);
  Card QueenDiamonds = new Card(Value.Queen, Suit.Diamonds);
  Card JackClubs = new Card(Value.Jack, Suit.Clubs);
  Card TwoClubs = new Card(Value.Two, Suit.Clubs);
  Card ThreeSpades = new Card(Value.Three, Suit.Spades);
  Card FourDiamonds = new Card(Value.Four, Suit.Diamonds);
  Card FiveHearts = new Card(Value.Five, Suit.Hearts);
  ArrayList<Card> AKQJ = new ArrayList<Card>();
  ArrayList<Card> AKQJsorted = new ArrayList<Card>();
  ArrayList<Card> AKQ = new ArrayList<Card>();
  Hand AKQJHand = new Hand(AKQJ);
  Hand AKQHand = new Hand(AKQ);


  ArrayList<Card> HandOne = new ArrayList<Card>();
  ArrayList<Card> HandOnesorted = new ArrayList<Card>();
  Hand HandOneHand = new Hand(HandOne);

  @Test
  public void getHandSortsProperly() {
    AKQJ.add(AceSpades);
    AKQJ.add(KingHearts);
    AKQJ.add(QueenDiamonds);
    AKQJ.add(JackClubs);
    Collections.sort(AKQJ);
    AKQJsorted.add(JackClubs);
    AKQJsorted.add(QueenDiamonds);
    AKQJsorted.add(KingHearts);
    AKQJsorted.add(AceSpades);
    assertEquals(0, AKQJ.get(0).compareTo(AKQJsorted.get(0)));
    assertEquals(0, AKQJ.get(1).compareTo(AKQJsorted.get(1)));
    assertEquals(0, AKQJ.get(2).compareTo(AKQJsorted.get(2)));
    assertEquals(0, AKQJ.get(3).compareTo(AKQJsorted.get(3)));
    HandOne.add(ThreeSpades);
    HandOne.add(AceSpades);
    HandOne.add(JackClubs);
    HandOne.add(TwoClubs);
    Collections.sort(HandOne);
    HandOnesorted.add(JackClubs);
    HandOnesorted.add(TwoClubs);
    HandOnesorted.add(AceSpades);
    HandOnesorted.add(ThreeSpades);
    assertEquals(0, HandOne.get(0).compareTo(HandOnesorted.get(0)));
    assertEquals(0, HandOne.get(1).compareTo(HandOnesorted.get(1)));
    assertEquals(0, HandOne.get(2).compareTo(HandOnesorted.get(2)));
    assertEquals(0, HandOne.get(3).compareTo(HandOnesorted.get(3)));
  }


  @Test
  public void handToStringWorks() {
    AKQJ.add(AceSpades);
    AKQJ.add(KingHearts);
    AKQJ.add(QueenDiamonds);
    AKQJ.add(JackClubs);
    assertEquals(AKQJHand.handToString(), " J♣, Q♦, K♥, A♠");

  }

}
