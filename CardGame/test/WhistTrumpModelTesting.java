import cs3500.hw02.*;
import cs3500.hw03.WhistModel;
import cs3500.hw04.WhistTrumpModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WhistTrumpModelTesting {

  WhistTrumpModel game = new WhistTrumpModel();
  List<Card> deck = game.getDeck();
  ArrayList<String> winningPlayers = new ArrayList<String>();

  //Test Exceptions for Play Method (Basically same as WhistModel's tests):

  //Tests to see if the current player not being the playerNo throws an Exception
  //-Throw and exception when the method play passes in a number that is not the currentPlayer into
  // playerNo
  @Test(expected = IllegalArgumentException.class)
  public void currentPlayerNotPlayerNo() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    //Current player is 0, but throws exception when playerNo is not 0:
    fourplayer.play(2, 1);
  }

  //Tests to see if the cardIdx given is not within the hands.get(playerNo).size()
  //-Throws an exception when the method play passes in a number that is not within the size of
  // the player's hand into cardIdx
  @Test(expected = IllegalArgumentException.class)
  public void invalidCardIdxLow() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    //Current size of player 0's hand is 0 - 12, but throws exception when cardIdx is given
    //something lower
    fourplayer.play(0, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidCardIdxHigh() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    //Current size of player 0's hand is 0 - 12, but throws exception when cardIdx is given
    //something higher
    fourplayer.play(0, 13);
  }

  //Tests to see if the personNo given is not within the hands.size()
  //-Throws an exception when the method play passes in a number that is not between 0 and the size
  // of the number of hands into playerNo
  @Test(expected = IllegalArgumentException.class)
  public void invalidPersonNoLow() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    //Current size of hands is from 0 - 3, but throws an exception when personNo is given
    //something lower
    fourplayer.play(-2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPersonNoHigh() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    //Current size of hands is from 0 - 3, but throws an exception when personNo is given
    //something higher
    fourplayer.play(20, 0);
  }

  //Tests to see if playing a card after the game is over throws an Exception
  //-Throws an exception when the play method is called and the isGameOver method is true
  @Test(expected = IllegalArgumentException.class)
  public void playWhenGameIsOver() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 4; j++) {
        fourplayer.play(j, 0);
      }
    }
    assertEquals(true, fourplayer.isGameOver());
    assertEquals("Number of players: 4\n" +
                    "Player 1:\n" +
                    "Player 2:\n" +
                    "Player 3:\n" +
                    "Player 4:\n" +
                    "Player 1 score: 13\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Game over. Player 1 won.",
            fourplayer.getGameState());
    fourplayer.play(0, 1);
  }

  //Tests to see if getting the currentPlayer throws an Exception
  //-Throws an exception when the getCurrentPlayer method is called after the isGameOver method is
  // true
  @Test(expected = IllegalArgumentException.class)
  public void getCurrentPlayerWhenGameIsOver() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 4; j++) {
        fourplayer.play(j, 0);
      }
    }
    assertEquals(true, fourplayer.isGameOver());
    assertEquals("Number of players: 4\n" +
                    "Player 1:\n" +
                    "Player 2:\n" +
                    "Player 3:\n" +
                    "Player 4:\n" +
                    "Player 1 score: 13\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Game over. Player 1 won.",
            fourplayer.getGameState());
    fourplayer.getCurrentPlayer();
  }

  // Test to see if startPlay throws an exception if it's given 0 or less players
  @Test(expected = IllegalArgumentException.class)
  public void startWithNoPlayers() {
    WhistModel empty = new WhistModel();
    List<Card> deck = empty.getDeck();
    empty.startPlay(0, deck);
  }

  //Test to see if getGameState returns "Number of playrs: 0" when there are no players in an
  //empty game
  @Test
  public void getGameStateWith0Players() {
    WhistModel emptygame = new WhistModel(); // test getGameState only

    assertEquals("Number of players: 0", emptygame.getGameState());
  }

  // Throws an Illegal Argument Exception if a player tries to play a trump
  @Test(expected = IllegalArgumentException.class)
  public void fourPlayerCannotPlayTrump() {
    WhistTrumpModel fourplayer = new WhistTrumpModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    assertEquals(0, fourplayer.getCurrentPlayer());
    assertEquals("Number of players: 4\n" +
                    "Player 1: A♣, 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: K♣, 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: Q♣, 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    fourplayer.play(0, 4);
    fourplayer.play(1, 0); //Tries to play Trump Suit (♣) when trickSuit is ♦ and this player
    //has cards that match the trickSuit
  }

  @Test(expected = IllegalArgumentException.class)
  public void threePlayerCannotPlayTrump() {
    WhistTrumpModel threeplayer = new WhistTrumpModel();
    List<Card> deck = threeplayer.getDeck();
    threeplayer.startPlay(3, deck);
    assertEquals("Number of players: 3\n" +
            "Player 1: A♣, J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, " +
            "8♠, 5♠, 2♠\n" +
            "Player 2: K♣, 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, " +
            "10♠, 7♠, 4♠\n" +
            "Player 3: Q♣, 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, " +
            "9♠, 6♠, 3♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump Suit: ♣", threeplayer.getGameState());
    threeplayer.play(0, 5);
    threeplayer.play(1, 0); //Tries to play Trump Suit (♣) when trickSuit is ♦ and this player
    //has cards that match the trickSuit
  }

  // Wrong card, but not the Trump card
  @Test(expected = IllegalArgumentException.class)
  public void fourPlayerCannotPlayCard() {
    WhistTrumpModel fourplayer = new WhistTrumpModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    assertEquals("Number of players: 4\n" +
                    "Player 1: A♣, 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: K♣, 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: Q♣, 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    fourplayer.play(0, 0);
    fourplayer.play(1, 5); // Tries to play 6♦ when trickSuit is ♣
  }

  //Test that a trump suit beats the trick suit
  @Test
  public void trumpSuitBeatsTrickSuit() {
    WhistTrumpModel fourplayer = new WhistTrumpModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    assertEquals("Number of players: 4\n" +
                    "Player 1: A♣, 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: K♣, 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: Q♣, 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 0);
    fourplayer.play(1, 1);
    fourplayer.play(2, 1);
    fourplayer.play(3, 1);
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    assertEquals("Number of players: 4\n" +
                    "Player 1: 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 2\n" +
                    "Player 2 score: 1\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 2\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 1);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 1);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 1);
    assertEquals("Number of players: 4\n" +
                    "Player 1: 2♣, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 2\n" +
                    "Player 2 score: 4\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 2\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    assertEquals(1, fourplayer.getCurrentPlayer()); //Player 2's Turn
    fourplayer.play(1, 0); //Player 2 plays a 2♦ - Trick is Diamonds
    fourplayer.play(2, 0); //Player 3 and 4 don't have a card matching the trick suit or trump suit
    fourplayer.play(3, 0); //so they have to forfeit the trick, they just play A♥ and K♥
    fourplayer.play(0, 0); //Player 1 plays a 2♣, which is clubs, the Trump Suit
    //Player 1 should win
    assertEquals("Number of players: 4\n" +
                    "Player 1: Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 3\n" +
                    "Player 2 score: 4\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    assertEquals(0, fourplayer.getCurrentPlayer()); //Player 1's Turn

  }

  //Test a higher value Trump suit beats a lower value Trump sui
  @Test
  public void trumpSuitBeatsTrumpSuit() {
    WhistTrumpModel fiveplayer = new WhistTrumpModel();
    List<Card> deck = fiveplayer.getDeck();
    fiveplayer.startPlay(5, deck);
    assertEquals("Number of players: 5\n" +
                    "Player 1: A♣, 9♣, 4♣, Q♦, 7♦, 2♦, 10♥, 5♥, K♠, 8♠, 3♠\n" +
                    "Player 2: K♣, 8♣, 3♣, J♦, 6♦, A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
                    "Player 3: Q♣, 7♣, 2♣, 10♦, 5♦, K♥, 8♥, 3♥, J♠, 6♠\n" +
                    "Player 4: J♣, 6♣, A♦, 9♦, 4♦, Q♥, 7♥, 2♥, 10♠, 5♠\n" +
                    "Player 5: 10♣, 5♣, K♦, 8♦, 3♦, J♥, 6♥, A♠, 9♠, 4♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Player 5 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            fiveplayer.getGameState());
    fiveplayer.play(0, 0);
    fiveplayer.play(1, 0);
    fiveplayer.play(2, 0);
    fiveplayer.play(3, 1);
    fiveplayer.play(4, 1);
    fiveplayer.play(0, 0);
    fiveplayer.play(1, 0);
    fiveplayer.play(2, 1);
    fiveplayer.play(3, 0);
    fiveplayer.play(4, 0);
    assertEquals("Number of players: 5\n" +
                    "Player 1: 4♣, Q♦, 7♦, 2♦, 10♥, 5♥, K♠, 8♠, 3♠\n" +
                    "Player 2: 3♣, J♦, 6♦, A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
                    "Player 3: 7♣, 10♦, 5♦, K♥, 8♥, 3♥, J♠, 6♠\n" +
                    "Player 4: A♦, 9♦, 4♦, Q♥, 7♥, 2♥, 10♠, 5♠\n" +
                    "Player 5: K♦, 8♦, 3♦, J♥, 6♥, A♠, 9♠, 4♠\n" +
                    "Player 1 score: 1\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 1\n" +
                    "Player 5 score: 0\n" +
                    "Turn: Player 4\n" +
                    "Trump Suit: ♣",
            fiveplayer.getGameState());
    fiveplayer.play(3, 0);
    fiveplayer.play(4, 0);
    fiveplayer.play(0, 1);
    fiveplayer.play(1, 1);
    fiveplayer.play(2, 1);
    fiveplayer.play(3, 0);
    fiveplayer.play(4, 0);
    fiveplayer.play(0, 1);
    fiveplayer.play(1, 1);
    fiveplayer.play(2, 1);
    assertEquals("Number of players: 5\n" +
                    "Player 1: 4♣, 2♦, 10♥, 5♥, K♠, 8♠, 3♠\n" +
                    "Player 2: 3♣, A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
                    "Player 3: 7♣, K♥, 8♥, 3♥, J♠, 6♠\n" +
                    "Player 4: 4♦, Q♥, 7♥, 2♥, 10♠, 5♠\n" +
                    "Player 5: 3♦, J♥, 6♥, A♠, 9♠, 4♠\n" +
                    "Player 1 score: 1\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 3\n" +
                    "Player 5 score: 0\n" +
                    "Turn: Player 4\n" +
                    "Trump Suit: ♣",
            fiveplayer.getGameState());
    assertEquals(3, fiveplayer.getCurrentPlayer());
    fiveplayer.play(3, 0); //Fourth player's turn, so he plays 4♦ and tricksuit is now ♦
    fiveplayer.play(4, 0); //Fifth player must play his only ♦, a 3♦
    fiveplayer.play(0, 1); //First player has a card with the Trump's Suit, but cannot play it
    //since he has a ♦
    fiveplayer.play(1, 0); //Second player does not have a diamond, so plays his 3♣, which beats
    //out the current winning card (4♦) since clubs is the current
    //trump suit and trumps beat any card that is not a higher value trump
    fiveplayer.play(2, 0); //Third player does not have a diamond, so plays his 7♣, which would
    //beat out the trick's suit, and the current winning card which is
    //player 2's 3♣. The 3♣ is a trump, but so is 7♣, and the 7♣ has a
    //higher value 7 > 3.
    //Higher Trump wins so player 3 should win the trick and gain a point:
    assertEquals("Number of players: 5\n" +
                    "Player 1: 4♣, 10♥, 5♥, K♠, 8♠, 3♠\n" +
                    "Player 2: A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
                    "Player 3: K♥, 8♥, 3♥, J♠, 6♠\n" +
                    "Player 4: Q♥, 7♥, 2♥, 10♠, 5♠\n" +
                    "Player 5: J♥, 6♥, A♠, 9♠, 4♠\n" +
                    "Player 1 score: 1\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 1\n" +
                    "Player 4 score: 3\n" +
                    "Player 5 score: 0\n" +
                    "Turn: Player 3\n" +
                    "Trump Suit: ♣",
            fiveplayer.getGameState());
  }

  //Test lower value Trump suit does not beat a higher value Trump suit
  @Test
  public void trumpSuitDoesNotBeatTrumpSuit() {
    WhistTrumpModel fiveplayer = new WhistTrumpModel();
    List<Card> deck = fiveplayer.getDeck();
    fiveplayer.startPlay(5, deck);
    assertEquals("Number of players: 5\n" +
                    "Player 1: A♣, 9♣, 4♣, Q♦, 7♦, 2♦, 10♥, 5♥, K♠, 8♠, 3♠\n" +
                    "Player 2: K♣, 8♣, 3♣, J♦, 6♦, A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
                    "Player 3: Q♣, 7♣, 2♣, 10♦, 5♦, K♥, 8♥, 3♥, J♠, 6♠\n" +
                    "Player 4: J♣, 6♣, A♦, 9♦, 4♦, Q♥, 7♥, 2♥, 10♠, 5♠\n" +
                    "Player 5: 10♣, 5♣, K♦, 8♦, 3♦, J♥, 6♥, A♠, 9♠, 4♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Player 5 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            fiveplayer.getGameState());
    fiveplayer.play(0, 0);
    fiveplayer.play(1, 0);
    fiveplayer.play(2, 0);
    fiveplayer.play(3, 0);
    fiveplayer.play(4, 0);
    fiveplayer.play(0, 1);
    fiveplayer.play(1, 1);
    fiveplayer.play(2, 1);
    fiveplayer.play(3, 0);
    fiveplayer.play(4, 0);
    assertEquals("Number of players: 5\n" +
                    "Player 1: 9♣, Q♦, 7♦, 2♦, 10♥, 5♥, K♠, 8♠, 3♠\n" +
                    "Player 2: 8♣, J♦, 6♦, A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
                    "Player 3: 7♣, 10♦, 5♦, K♥, 8♥, 3♥, J♠, 6♠\n" +
                    "Player 4: A♦, 9♦, 4♦, Q♥, 7♥, 2♥, 10♠, 5♠\n" +
                    "Player 5: K♦, 8♦, 3♦, J♥, 6♥, A♠, 9♠, 4♠\n" +
                    "Player 1 score: 1\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 1\n" +
                    "Player 5 score: 0\n" +
                    "Turn: Player 4\n" +
                    "Trump Suit: ♣",
            fiveplayer.getGameState());
    fiveplayer.play(3, 0);
    fiveplayer.play(4, 0);
    fiveplayer.play(0, 1);
    fiveplayer.play(1, 1);
    fiveplayer.play(2, 1);
    fiveplayer.play(3, 0);
    fiveplayer.play(4, 0);
    fiveplayer.play(0, 1);
    fiveplayer.play(1, 1);
    fiveplayer.play(2, 1);
    assertEquals("Number of players: 5\n" +
                    "Player 1: 9♣, 2♦, 10♥, 5♥, K♠, 8♠, 3♠\n" +
                    "Player 2: 8♣, A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
                    "Player 3: 7♣, K♥, 8♥, 3♥, J♠, 6♠\n" +
                    "Player 4: 4♦, Q♥, 7♥, 2♥, 10♠, 5♠\n" +
                    "Player 5: 3♦, J♥, 6♥, A♠, 9♠, 4♠\n" +
                    "Player 1 score: 1\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 3\n" +
                    "Player 5 score: 0\n" +
                    "Turn: Player 4\n" +
                    "Trump Suit: ♣",
            fiveplayer.getGameState());
    assertEquals(3, fiveplayer.getCurrentPlayer());
    fiveplayer.play(3, 0); //Fourth player's turn, so he plays 4♦ and tricksuit is now ♦
    fiveplayer.play(4, 0); //Fifth player must play his only ♦, a 3♦
    fiveplayer.play(0, 1); //First player has a card with the Trump's Suit, but cannot play it
    //since he has a ♦
    fiveplayer.play(1, 0); //Second player does not have a diamond, so plays his 8♣, which beats
    //out the current winning card (4♦) by value and suit, although only
    //the suit matters here since the Trump suit beats any trick suit
    fiveplayer.play(2, 0); //Third player does not have a diamond, so plays his 7♣, which would
    //beat out the trick's suit, but is not of a larger value than the 8♣
    //played by player 2.
    //Higher Trump wins so player 2 should win the trick and gain a point:
    assertEquals("Number of players: 5\n" +
                    "Player 1: 9♣, 10♥, 5♥, K♠, 8♠, 3♠\n" +
                    "Player 2: A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
                    "Player 3: K♥, 8♥, 3♥, J♠, 6♠\n" +
                    "Player 4: Q♥, 7♥, 2♥, 10♠, 5♠\n" +
                    "Player 5: J♥, 6♥, A♠, 9♠, 4♠\n" +
                    "Player 1 score: 1\n" +
                    "Player 2 score: 1\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 3\n" +
                    "Player 5 score: 0\n" +
                    "Turn: Player 2\n" +
                    "Trump Suit: ♣",
            fiveplayer.getGameState());
    assertEquals(1, fiveplayer.getCurrentPlayer());
  }

  //Makes sure the new Trump Model does not interfere with previous features such as in the game
  //still ends and ties still work (makes 2 winners)
  @Test
  public void trumpModelDoesNotInterefere() {
    WhistTrumpModel fourplayer = new WhistTrumpModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    assertEquals("Number of players: 4\n" +
                    "Player 1: A♣, 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: K♣, 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: Q♣, 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    //make player 1 win 5 times:
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    assertEquals("Number of players: 4\n" +
                    "Player 1: 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 5\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    // Make player 2 win 5 tricks:
    fourplayer.play(0, 1);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    assertEquals(1, fourplayer.getCurrentPlayer());
    fourplayer.play(1, 1);
    fourplayer.play(2, 1);
    fourplayer.play(3, 1);
    fourplayer.play(0, 2);
    assertEquals(1, fourplayer.getCurrentPlayer());
    fourplayer.play(1, 1);
    fourplayer.play(2, 1);
    fourplayer.play(3, 1);
    fourplayer.play(0, 2);
    assertEquals(1, fourplayer.getCurrentPlayer());
    fourplayer.play(1, 2);
    fourplayer.play(2, 2);
    fourplayer.play(3, 2);
    fourplayer.play(0, 3);
    assertEquals(1, fourplayer.getCurrentPlayer());
    fourplayer.play(1, 2);
    fourplayer.play(2, 2);
    fourplayer.play(3, 2);
    fourplayer.play(0, 3);
    assertEquals(1, fourplayer.getCurrentPlayer());
    // Make player 3 win 2 tricks:
    fourplayer.play(1, 1);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 1);
    assertEquals(2, fourplayer.getCurrentPlayer());
    fourplayer.play(2, 0);
    fourplayer.play(3, 1);
    fourplayer.play(0, 1);
    fourplayer.play(1, 1);
    assertEquals(2, fourplayer.getCurrentPlayer());
    // Make player 4 win 1 trick:
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    assertEquals(true, fourplayer.isGameOver());
    winningPlayers.add("1");
    winningPlayers.add("2");
    assertEquals(winningPlayers, fourplayer.determineWinningPlayer());
    assertEquals("Number of players: 4\n" +
                    "Player 1:\n" +
                    "Player 2:\n" +
                    "Player 3:\n" +
                    "Player 4:\n" +
                    "Player 1 score: 5\n" +
                    "Player 2 score: 5\n" +
                    "Player 3 score: 2\n" +
                    "Player 4 score: 1\n" +
                    "Game over. Player 1, 2 won.\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    assertEquals(fourplayer.isGameOver(), true);
  }

  //Makes sure the new Trump Model does not interfere with previous features such as in the game
  //still ends
  @Test
  public void trumpModelDoesNotInterefere2() {
    WhistTrumpModel sixplayer = new WhistTrumpModel();
    List<Card> deck = sixplayer.getDeck();
    sixplayer.startPlay(6, deck);

    assertEquals("Number of players: 6\n" +
                    "Player 1: A♣, 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠\n" +
                    "Player 2: K♣, 7♣, A♦, 8♦, 2♦, 9♥, 3♥, 10♠, 4♠\n" +
                    "Player 3: Q♣, 6♣, K♦, 7♦, A♥, 8♥, 2♥, 9♠, 3♠\n" +
                    "Player 4: J♣, 5♣, Q♦, 6♦, K♥, 7♥, A♠, 8♠, 2♠\n" +
                    "Player 5: 10♣, 4♣, J♦, 5♦, Q♥, 6♥, K♠, 7♠\n" +
                    "Player 6: 9♣, 3♣, 10♦, 4♦, J♥, 5♥, Q♠, 6♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Player 5 score: 0\n" +
                    "Player 6 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            sixplayer.getGameState());
    //Make player 1 win 8 times
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 6; j++) {
        sixplayer.play(j, 0);
      }
    }
    assertEquals("Number of players: 6\n" +
                    "Player 1: 5♠\n" +
                    "Player 2: 4♠\n" +
                    "Player 3: 3♠\n" +
                    "Player 4: 2♠\n" +
                    "Player 5:\n" +
                    "Player 6:\n" +
                    "Player 1 score: 8\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Player 5 score: 0\n" +
                    "Player 6 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            sixplayer.getGameState());
    sixplayer.play(0, 0);
    sixplayer.play(1, 0);
    sixplayer.play(2, 0);
    sixplayer.play(3, 0);
    assertEquals("Number of players: 6\n" +
                    "Player 1:\n" +
                    "Player 2:\n" +
                    "Player 3:\n" +
                    "Player 4:\n" +
                    "Player 5:\n" +
                    "Player 6:\n" +
                    "Player 1 score: 9\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Player 5 score: 0\n" +
                    "Player 6 score: 0\n" +
                    "Game over. Player 1 won.\n" +
                    "Trump Suit: ♣",
            sixplayer.getGameState());
    assertEquals(true, sixplayer.isGameOver());
  }

  //Makes a player other than Player 1 win
  @Test
  public void player2Wins() {
    WhistTrumpModel fourplayer = new WhistTrumpModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);

    assertEquals("Number of players: 4\n" +
                    "Player 1: A♣, 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: K♣, 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: Q♣, 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    fourplayer.play(0, 1);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    //Make player 2 win 11 more tricks
    for (int i = 0; i < 11; i++) {
      fourplayer.play(1, 0);
      fourplayer.play(2, 0);
      fourplayer.play(3, 0);
      fourplayer.play(0, 1);
    }
    assertEquals("Number of players: 4\n" +
                    "Player 1: A♣\n" +
                    "Player 2: 4♠\n" +
                    "Player 3: 3♠\n" +
                    "Player 4: 2♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 12\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 2\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    fourplayer.play(0, 0);
    assertEquals("Number of players: 4\n" +
                    "Player 1:\n" +
                    "Player 2:\n" +
                    "Player 3:\n" +
                    "Player 4:\n" +
                    "Player 1 score: 1\n" +
                    "Player 2 score: 12\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Game over. Player 2 won.\n" +
                    "Trump Suit: ♣",
            fourplayer.getGameState());
    assertEquals(true, fourplayer.isGameOver());
  }
}
