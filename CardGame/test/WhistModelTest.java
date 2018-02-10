import cs3500.hw02.*;
import cs3500.hw03.WhistModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

// test the WhistModel's methods
public class WhistModelTest {

  WhistModel game = new WhistModel();
  List<Card> deck = game.getDeck();
  ArrayList<String> winningPlayers = new ArrayList<String>();


  //Tests to see if the current player not being the playerNo throws an Exception
  //-Throw and exception when the method play passes in a number that is not the currentPlayer into
  // playerNo
  @Test(expected = IllegalArgumentException.class)
  public void currentPlayerNotPlayerNo() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    //Current player is 0, but throws exception when playerNo is not 0:
    fourplayer.play(1, 0);
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
    fourplayer.play(0, -1);
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
    fourplayer.play(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPersonNoHigh() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    //Current size of hands is from 0 - 3, but throws an exception when personNo is given
    //something higher
    fourplayer.play(10, 0);
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
    fourplayer.play(0, 0);
  }

  //Tests to see that a player playing a card that is a different suit from the trick suit throws
  //an exception if they have a card that is the same as the trick's suit that they can play
  //-Throws an exception when the play method is called on cards of invalid matching suits when
  // there exists other cards in that certain hand with matching suits to the trick's suit
  @Test(expected = IllegalArgumentException.class)
  public void playWrongCard() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    fourplayer.play(0, 0);
    fourplayer.play(1, 9); // second player tries to play the 9th card (index 10), a 3♥ when the
    // trick's suit is Clubs and Player 2 has Clubs he/she can play
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

  //Test that all methods in WhistModel generally work. Other tests will test more specific
  //instances such as if a player cannot play a card that has the same suit as the trick's suit
  @Test
  public void whistModelMethodsWork() {
    WhistModel fourplayer = new WhistModel();
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
                    "Turn: Player 1",
            fourplayer.getGameState());
    fourplayer.play(0, 0);
    assertEquals(1, fourplayer.getCurrentPlayer());
    assertEquals("Number of players: 4\n" +
                    "Player 1: 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: K♣, 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: Q♣, 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 2",
            fourplayer.getGameState());
    fourplayer.play(1, 0);
    assertEquals(2, fourplayer.getCurrentPlayer());
    assertEquals("Number of players: 4\n" +
                    "Player 1: 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: Q♣, 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 3",
            fourplayer.getGameState());
    fourplayer.play(2, 0);
    assertEquals(3, fourplayer.getCurrentPlayer());
    assertEquals("Number of players: 4\n" +
                    "Player 1: 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: J♣, 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 0\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 4",
            fourplayer.getGameState());
    fourplayer.play(3, 0);
    assertEquals(0, fourplayer.getCurrentPlayer());
    assertEquals("Number of players: 4\n" +
                    "Player 1: 10♣, 6♣, 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: 9♣, 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: 8♣, 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: 7♣, 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 1\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1",
            fourplayer.getGameState());
    assertEquals(false, fourplayer.isGameOver());
    // determines the current winning player. Usually used at the end of the game though,
    // to determine the winning player at the end of the game - when isGameOver is true
    winningPlayers.add("1");
    assertEquals(winningPlayers, fourplayer.determineWinningPlayer());
    // Tests to see if the player who plays the highest value card (other than the first player)
    // wins the trick if the card played matches the suit of the trick. Also tests to see if
    // the getCurrentPlayer returns the winner of the previous trick.
    fourplayer.play(0, 2);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    assertEquals(1, fourplayer.getCurrentPlayer());
    assertEquals("Number of players: 4\n" +
                    "Player 1: 10♣, 6♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: 5♣, A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: 4♣, K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: 3♣, Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 1\n" +
                    "Player 2 score: 1\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 2",
            fourplayer.getGameState());
    // Tests that determineWinningPlayer works with ties
    winningPlayers.add("2");
    assertEquals(winningPlayers, fourplayer.determineWinningPlayer());

  }

  //Tests to see if a player who plays a higher value card, but the wrong suit (discarded card)
  //does not win the trick, but the highest value trick suit wins the trick
  @Test
  public void testPlayingDiscardedCards() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
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
    // After 3 rounds:
    assertEquals("Number of players: 4\n" +
                    "Player 1: 2♣, J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: A♦, 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: K♦, 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: Q♦, 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 3\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1",
            fourplayer.getGameState());
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
    // After the round where player 1 plays a card and sets the trick suit to be Clubs, but the
    // other 3 players must "discard" a card as they do not have any Clubs. Tests to make sure
    // that player 1 wins even though the card player 1 played (2 of Clubs) has a lower value than
    // the other 3 players' cards (Ace, King, and Queen of Diamonds) - player 1's score should be 4
    assertEquals("Number of players: 4\n" +
                    "Player 1: J♦, 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: 10♦, 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: 9♦, 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: 8♦, 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 4\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1",
            fourplayer.getGameState());
  }

  //Tests for the isGameOver to be true when it's supposed to be true, and also tests the special
  //message in a case where one player wins
  @Test
  public void isGameOverAndGetGameStateWorks() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 4; j++) {
        fourplayer.play(j, 0);
      }
    }
    assertEquals(false, fourplayer.isGameOver());
    assertEquals("Number of players: 4\n" +
                    "Player 1: 5♠\n" +
                    "Player 2: 4♠\n" +
                    "Player 3: 3♠\n" +
                    "Player 4: 2♠\n" +
                    "Player 1 score: 12\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1",
            fourplayer.getGameState());
    fourplayer.play(0, 0);
    fourplayer.play(1, 0);
    assertEquals(false, fourplayer.isGameOver());
    assertEquals("Number of players: 4\n" +
                    "Player 1:\n" +
                    "Player 2:\n" +
                    "Player 3: 3♠\n" +
                    "Player 4: 2♠\n" +
                    "Player 1 score: 12\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 3",
            fourplayer.getGameState());
    fourplayer.play(2, 0);
    fourplayer.play(3, 0);
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
  }

  //Tests getGameState's special message returns String correctly on a tie
  //-Must "orchestrate" the game in a way so there is a tie
  @Test
  public void getGameStateOnATie() {
    WhistModel fourplayer = new WhistModel();
    List<Card> deck = fourplayer.getDeck();
    fourplayer.startPlay(4, deck);
    // Make player 1 win 5 tricks:
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 4; j++) {
        fourplayer.play(j, 0);
      }
    }
    assertEquals("Number of players: 4\n" +
                    "Player 1: 7♦, 3♦, Q♥, 8♥, 4♥, K♠, 9♠, 5♠\n" +
                    "Player 2: 6♦, 2♦, J♥, 7♥, 3♥, Q♠, 8♠, 4♠\n" +
                    "Player 3: 5♦, A♥, 10♥, 6♥, 2♥, J♠, 7♠, 3♠\n" +
                    "Player 4: 4♦, K♥, 9♥, 5♥, A♠, 10♠, 6♠, 2♠\n" +
                    "Player 1 score: 5\n" +
                    "Player 2 score: 0\n" +
                    "Player 3 score: 0\n" +
                    "Player 4 score: 0\n" +
                    "Turn: Player 1",
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
                    "Game over. Player 1, 2 won.",
            fourplayer.getGameState());

  }

  //Test the game with 3 players - where there is a case that the last player still has a card, but
  //no one else does so the game ends
  @Test
  public void whistGameWith3Players() {
    WhistModel threeplayer = new WhistModel();
    List<Card> deck = threeplayer.getDeck();
    threeplayer.startPlay(3, deck);
    assertEquals("Number of players: 3\n" +
            "Player 1: A♣, J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, " +
            "5♠, 2♠\n" +
            "Player 2: K♣, 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, " +
            "7♠, 4♠\n" +
            "Player 3: Q♣, 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, " +
            "6♠, 3♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Turn: Player 1", threeplayer.getGameState());
    assertEquals(false, threeplayer.isGameOver());
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 3; j++) {
        threeplayer.play(j, 0);
      }
    }
    threeplayer.play(0, 0);
    threeplayer.play(1, 0);
    threeplayer.play(2, 0);
    assertEquals(true, threeplayer.isGameOver());
    assertEquals("Number of players: 3\n" +
            "Player 1: 2♠\n" +
            "Player 2:\n" +
            "Player 3:\n" +
            "Player 1 score: 17\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Game over. Player 1 won.", threeplayer.getGameState());
  }

  //Test the game with 2 players
  @Test
  public void whistGameWith2Players() {
    WhistModel twoplayer = new WhistModel();
    List<Card> deck = twoplayer.getDeck();
    twoplayer.startPlay(2, deck);
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, " +
            "10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, " +
            "9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1", twoplayer.getGameState());
    assertEquals(false, twoplayer.isGameOver());
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 2; j++) {
        twoplayer.play(j, 0);
      }
    }
    assertEquals(true, twoplayer.isGameOver());
    assertEquals("Number of players: 2\n" +
            "Player 1:\n" +
            "Player 2:\n" +
            "Player 1 score: 26\n" +
            "Player 2 score: 0\n" +
            "Game over. Player 1 won.", twoplayer.getGameState());
  }

  //Test the game with 1 player - should be gameOver once it starts since there is less than
  //two players with available cards to play
  @Test
  public void whistGameWith1Player() {
    WhistModel oneplayer = new WhistModel();
    List<Card> deck = oneplayer.getDeck();
    oneplayer.startPlay(1, deck);
    assertEquals(true, oneplayer.isGameOver());
    assertEquals("Number of players: 1\n" +
            "Player 1: A♣, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♦, K♦, Q♦, " +
            "J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♥, K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, " +
            "6♥, 5♥, 4♥, 3♥, 2♥, A♠, K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Game over. Player 1 won.", oneplayer.getGameState());
  }

  //Test to see if after a player wins a trick, but ran out of cards, other players with cards can
  //still play - currentTurn should jump to player 0 who always has at least the same amount, or
  //in this case with 5 players, 1 more card than player 2,3, and 4. Then player 0 and 1 play, the
  //trick ends, point goes to either player 0 or 1 depending on their respective cards played, and
  //then
  @Test
  public void currentTurnJumpsToPlayer0() {
    WhistModel fiveplayer = new WhistModel();
    List<Card> deck = fiveplayer.getDeck();
    fiveplayer.startPlay(5, deck);
    assertEquals(false, fiveplayer.isGameOver());
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
            "Turn: Player 1", fiveplayer.getGameState());
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 5; j++) {
        fiveplayer.play(j, 0);
      }
    }
    assertEquals("Number of players: 5\n" +
            "Player 1: 8♠, 3♠\n" +
            "Player 2: 7♠, 2♠\n" +
            "Player 3: 6♠\n" +
            "Player 4: 5♠\n" +
            "Player 5: 4♠\n" +
            "Player 1 score: 9\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Player 4 score: 0\n" +
            "Player 5 score: 0\n" +
            "Turn: Player 1", fiveplayer.getGameState());

    //Make player 3, 4, or 5 win, and when they run out of cards, it's player 1's turn rather than
    //the player who ran out of cards:
    fiveplayer.play(0, 1);
    fiveplayer.play(1, 1);
    fiveplayer.play(2, 0);
    fiveplayer.play(3, 0);
    fiveplayer.play(4, 0);
    assertEquals(0, fiveplayer.getCurrentPlayer());
    assertEquals(false, fiveplayer.isGameOver());
    assertEquals("Number of players: 5\n" +
            "Player 1: 8♠\n" +
            "Player 2: 7♠\n" +
            "Player 3:\n" +
            "Player 4:\n" +
            "Player 5:\n" +
            "Player 1 score: 9\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 1\n" +
            "Player 4 score: 0\n" +
            "Player 5 score: 0\n" +
            "Turn: Player 1", fiveplayer.getGameState());
    fiveplayer.play(0, 0);
    fiveplayer.play(1, 0);
    assertEquals(true, fiveplayer.isGameOver());
    assertEquals("Number of players: 5\n" +
            "Player 1:\n" +
            "Player 2:\n" +
            "Player 3:\n" +
            "Player 4:\n" +
            "Player 5:\n" +
            "Player 1 score: 10\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 1\n" +
            "Player 4 score: 0\n" +
            "Player 5 score: 0\n" +
            "Game over. Player 1 won.", fiveplayer.getGameState());

  }

  //Test 5 player game
  @Test
  public void whistGameWith5Playrs() {
    WhistModel fiveplayer = new WhistModel();
    List<Card> deck = fiveplayer.getDeck();
    fiveplayer.startPlay(5, deck);
    assertEquals(false, fiveplayer.isGameOver());
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 5; j++) {
        fiveplayer.play(j, 0);
      }
    }
    fiveplayer.play(0, 0);
    fiveplayer.play(1, 0);
    fiveplayer.play(2, 0);
    fiveplayer.play(3, 0);
    fiveplayer.play(4, 0);
    assertEquals("Number of players: 5\n" +
            "Player 1: 3♠\n" +
            "Player 2: 2♠\n" +
            "Player 3:\n" +
            "Player 4:\n" +
            "Player 5:\n" +
            "Player 1 score: 10\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Player 4 score: 0\n" +
            "Player 5 score: 0\n" +
            "Turn: Player 1", fiveplayer.getGameState());
    fiveplayer.play(0, 0);
    fiveplayer.play(1, 0);
    assertEquals("Number of players: 5\n" +
            "Player 1:\n" +
            "Player 2:\n" +
            "Player 3:\n" +
            "Player 4:\n" +
            "Player 5:\n" +
            "Player 1 score: 11\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Player 4 score: 0\n" +
            "Player 5 score: 0\n" +
            "Game over. Player 1 won.", fiveplayer.getGameState());

  }

  //Test player 2 wins, but not player 1 (All previous tests had player 1 win or tie to win)
  @Test
  public void testRun() {
    WhistModel twoplayer = new WhistModel();
    List<Card> deck = twoplayer.getDeck();
    twoplayer.startPlay(2, deck);
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, " +
            "10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, " +
            "9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1", twoplayer.getGameState());
    assertEquals(false, twoplayer.isGameOver());
    twoplayer.play(0, 1);
    twoplayer.play(1, 0);
    for (int i = 0; i < 24; i++) {
      twoplayer.play(1, 0);
      twoplayer.play(0, 1);
    }
    twoplayer.play(1, 0);
    twoplayer.play(0, 0);
    assertEquals(true, twoplayer.isGameOver());
    assertEquals("Number of players: 2\n" +
            "Player 1:\n" +
            "Player 2:\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 26\n" +
            "Game over. Player 2 won.", twoplayer.getGameState());
  }

}



