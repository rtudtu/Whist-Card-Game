import org.junit.Assert;
import org.junit.Test;

import cs3500.hw02.Suit;

import static org.junit.Assert.assertEquals;

// Test Suit methods
public class SuitTest {

  @Test
  public void suitNumWorks() {
    Assert.assertEquals(Suit.Clubs.suitNum(), 1);
    assertEquals(Suit.Diamonds.suitNum(), 2);
    assertEquals(Suit.Hearts.suitNum(), 3);
    assertEquals(Suit.Spades.suitNum(), 4);
  }

  @Test
  public void suitToStringWorks() {
    assertEquals(Suit.Clubs.toString(), "♣");
    assertEquals(Suit.Diamonds.toString(), "♦");
    assertEquals(Suit.Hearts.toString(), "♥");
    assertEquals(Suit.Spades.toString(), "♠");
  }

  @Test
  public void getSuitWorks() {
    assertEquals(Suit.getSuit(1), Suit.Clubs);
    assertEquals(Suit.getSuit(2), Suit.Diamonds);
    assertEquals(Suit.getSuit(3), Suit.Hearts);
    assertEquals(Suit.getSuit(4), Suit.Spades);
  }
}


