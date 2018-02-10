import org.junit.Test;

import static cs3500.hw02.GenericStandardDeckGame.duplicateCard;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import cs3500.hw02.Card;
import cs3500.hw02.GenericStandardDeckGame;
import cs3500.hw02.Suit;
import cs3500.hw02.Value;

// test the game
public class GenericStandardDeckGameTest {

  //List<Card> deck = new ArrayList<Card>();

  // example games and decks
  GenericStandardDeckGame game = new GenericStandardDeckGame();
  List<Card> deck = game.getDeck();
  GenericStandardDeckGame badgame = new GenericStandardDeckGame();
  List<Card> baddeck = badgame.getDeck();
  // example Cards
  Card AceSpades = new Card(Value.Ace, Suit.Spades);
  Card KingHearts = new Card(Value.King, Suit.Hearts);
  Card QueenDiamonds = new Card(Value.Queen, Suit.Diamonds);
  Card JackClubs = new Card(Value.Jack, Suit.Clubs);
  Card TwoClubs = new Card(Value.Two, Suit.Clubs);
  Card ThreeSpades = new Card(Value.Three, Suit.Spades);
  ArrayList<Card> AKQJ = new ArrayList<Card>();
  ArrayList<Card> HandOne = new ArrayList<Card>();

  @Test
  public void duplicateCardWorks() {
    AKQJ.add(AceSpades);
    AKQJ.add(KingHearts);
    AKQJ.add(QueenDiamonds);
    AKQJ.add(JackClubs);
    assertEquals(duplicateCard(AKQJ), false);
    HandOne.add(ThreeSpades);
    HandOne.add(ThreeSpades);
    HandOne.add(JackClubs);
    HandOne.add(TwoClubs);
    assertEquals(duplicateCard(HandOne), true);
  }

  @Test
  public void getDeckWorks() {
    Collections.sort(deck);
    String output = "";
    for (int i = 0; i < 52; i++) {
      output = output + deck.get(i).value.toString() + deck.get(i).suit.toString() + " ";
    }

    assertEquals(output,
            "A♣ K♣ Q♣ J♣ 10♣ 9♣ 8♣ 7♣ 6♣ 5♣ 4♣ 3♣ 2♣ " +
                    "A♦ K♦ Q♦ J♦ 10♦ 9♦ 8♦ 7♦ 6♦ 5♦ 4♦ 3♦ 2♦ " +
                    "A♥ K♥ Q♥ J♥ 10♥ 9♥ 8♥ 7♥ 6♥ 5♥ 4♥ 3♥ 2♥ " +
                    "A♠ K♠ Q♠ J♠ 10♠ 9♠ 8♠ 7♠ 6♠ 5♠ 4♠ 3♠ 2♠ ");


  }


  @Test(expected = IllegalArgumentException.class)
  public void startPlayWithBadDeck() {
    baddeck.add(AceSpades);
    badgame.startPlay(2, baddeck);
  }


  @Test
  public void getGameStateWorks() {
    GenericStandardDeckGame emptygame = new GenericStandardDeckGame(); // test getGameState only

    assertEquals("Number of players: 0\n", emptygame.getGameState());
  }

  @Test
  public void startPlayWorks() {
    GenericStandardDeckGame twoplayer = new GenericStandardDeckGame();
    List<Card> sortedDeck = twoplayer.getDeck();
    Collections.sort(sortedDeck); // modifies "sortedDeck" so it is easier to test
    twoplayer.startPlay(2, sortedDeck);
    GenericStandardDeckGame threeplayer = new GenericStandardDeckGame();
    List<Card> sortedDeck2 = threeplayer.getDeck();
    Collections.sort(sortedDeck2); // modifies "sortedDeck2" so it is easier to test
    threeplayer.startPlay(3, sortedDeck2);

    assertEquals("Number of players: 2\n" +
                    "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, " +
                    "Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
                    "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, " +
                    "J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n",
            twoplayer.getGameState());
    assertEquals("Number of players: 3\n" +
                    "Player 1: A♣, J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, " +
                    "A♠, J♠, 8♠, 5♠, 2♠\n" +
                    "Player 2: K♣, 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, " +
                    "K♠, 10♠, 7♠, 4♠\n" +
                    "Player 3: Q♣, 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, " +
                    "Q♠, 9♠, 6♠, 3♠\n",
            threeplayer.getGameState());
  }

  /*
  @Test(expected = IllegalArgumentException.class)
  public void getGameStateWrong() {
    // must have:
    // badgame.startPlay(<Number of players>, badgame.getDeck());
    // to work with getGameState otherwise there is no gameState to get
    badgame.getGameState();
  }
  */

  //Calls getGameState after catching an exception that startPlay threw
  //Test added after reviewing self-evaluation
  @Test(expected = IllegalArgumentException.class)
  public void getGameStateWithException() {
    GenericStandardDeckGame badGame = new GenericStandardDeckGame();
    List<Card> badDeck = badGame.getDeck();
    badDeck.add(AceSpades);
    try {
      badGame.startPlay(3, badDeck);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("bad deck");
    }
  }

  //Gets a deck from my getDeck, passes as-is into startPlay, and calls getGameState
  //to verify that getDeck outputted all 52 valid cards
  //Test added after reviewing self-evaluation
  @Test
  public void getDeckAsIs() {
    GenericStandardDeckGame game = new GenericStandardDeckGame();
    List<Card> shuffledDeck = game.getDeck();
    game.startPlay(3, shuffledDeck);
    for (Card c: shuffledDeck) {
      assertTrue(game.getGameState().contains(c.cardToString()));
    }
    assertTrue(game.getGameState().contains("Player 1:"));
    assertTrue(game.getGameState().contains("Player 2:"));
    assertTrue(game.getGameState().contains("Player 3:"));
    assertTrue(game.getGameState().contains("Number of players: 3"));

  }

}
