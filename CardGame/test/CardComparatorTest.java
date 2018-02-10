import cs3500.hw02.*;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class CardComparatorTest {

  Card AceSpades = new Card(Value.Ace, Suit.Spades);
  Card KingHearts = new Card(Value.King, Suit.Hearts);
  Card QueenDiamonds = new Card(Value.Queen, Suit.Diamonds);
  Card JackClubs = new Card(Value.Jack, Suit.Clubs);
  Card TwoClubs = new Card(Value.Two, Suit.Clubs);
  Card ThreeSpades = new Card(Value.Three, Suit.Spades);
  Card FourDiamonds = new Card(Value.Four, Suit.Diamonds);
  Card FiveHearts = new Card(Value.Five, Suit.Hearts);

  //Same tests as compareTo in Card class
  @Test
  public void compareWorks() {
    assertEquals(AceSpades.compareTo(KingHearts), 1);
    assertEquals(KingHearts.compareTo(QueenDiamonds), 1);
    assertEquals(JackClubs.compareTo(AceSpades), -1);
    assertEquals(QueenDiamonds.compareTo(AceSpades), -1);
    assertEquals(AceSpades.compareTo(ThreeSpades), -1);
    assertEquals(KingHearts.compareTo(FiveHearts), -1);
    assertEquals(TwoClubs.compareTo(JackClubs), 1);
    assertEquals(FourDiamonds.compareTo(QueenDiamonds), 1);
    assertEquals(AceSpades.compareTo(AceSpades), 0);
    assertEquals(FourDiamonds.compareTo(FourDiamonds), 0);

  }
}
