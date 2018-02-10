import org.junit.Test;

import cs3500.hw02.Card;
import cs3500.hw02.Suit;
import cs3500.hw02.Value;

import static org.junit.Assert.assertEquals;

// Test Card Methods
public class CardTest {

  Card AceSpades = new Card(Value.Ace, Suit.Spades);
  Card KingHearts = new Card(Value.King, Suit.Hearts);
  Card QueenDiamonds = new Card(Value.Queen, Suit.Diamonds);
  Card JackClubs = new Card(Value.Jack, Suit.Clubs);
  Card TwoClubs = new Card(Value.Two, Suit.Clubs);
  Card ThreeSpades = new Card(Value.Three, Suit.Spades);
  Card FourDiamonds = new Card(Value.Four, Suit.Diamonds);
  Card FiveHearts = new Card(Value.Five, Suit.Hearts);


  @Test
  public void compareToWorks() {
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

  @Test
  public void getCardWorks() {
    assertEquals(AceSpades.compareTo(Card.getCard(14, 4)), 0);
    assertEquals(KingHearts.compareTo(Card.getCard(13, 3)), 0);
    assertEquals(QueenDiamonds.compareTo(Card.getCard(12, 2)), 0);
    assertEquals(JackClubs.compareTo(Card.getCard(11, 1)), 0);
    assertEquals(TwoClubs.compareTo(Card.getCard(2, 1)), 0);
    assertEquals(ThreeSpades.compareTo(Card.getCard(3, 4)), 0);
    assertEquals(FourDiamonds.compareTo(Card.getCard(4, 2)), 0);
    assertEquals(FiveHearts.compareTo(Card.getCard(5, 3)), 0);
  }

  @Test
  public void cardtoString() {
    assertEquals(AceSpades.cardToString(), "A♠");
    assertEquals(KingHearts.cardToString(), "K♥");
    assertEquals(QueenDiamonds.cardToString(), "Q♦");
    assertEquals(JackClubs.cardToString(), "J♣");
    assertEquals(TwoClubs.cardToString(), "2♣");
    assertEquals(ThreeSpades.cardToString(), "3♠");
    assertEquals(FourDiamonds.cardToString(), "4♦");
    assertEquals(FiveHearts.cardToString(), "5♥");
  }

  @Test
  public void retrieveSuitWorks() {
    assertEquals(AceSpades.retrieveSuit(), Suit.Spades);
    assertEquals(KingHearts.retrieveSuit(), Suit.Hearts);
    assertEquals(QueenDiamonds.retrieveSuit(), Suit.Diamonds);
    assertEquals(JackClubs.retrieveSuit(), Suit.Clubs);
  }

  @Test
  public void retrieveValueWorks() {
    assertEquals(TwoClubs.retrieveValue(), Value.Two);
    assertEquals(ThreeSpades.retrieveValue(), Value.Three);
    assertEquals(FourDiamonds.retrieveValue(), Value.Four);
    assertEquals(FiveHearts.retrieveValue(), Value.Five);
  }

}
