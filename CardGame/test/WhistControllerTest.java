import org.junit.Test;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

import cs3500.hw03.WhistController;
import cs3500.hw03.WhistModel;

//Tests for WhistController
public class WhistControllerTest {

  //q = letter q, 0 = number, \n = simulates an <Enter>
  private String quitGame = "q";
  private String onePlay = "0\n";

  //Tests to see if throwing a null game into playGame's CardGameModel argument will throw an
  //IllegalArgumentException
  @Test(expected = IllegalArgumentException.class)
  public void nullGameModelThrowsException() {
    StringBuffer sb = new StringBuffer();
    StringReader sr = new StringReader(quitGame);
    WhistModel game = null;
    WhistController whistControl = new WhistController(sr, sb);
    try {
      whistControl.playGame(game, 4);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("invalid game model");
    }
  }

  //Tests to see if giving playGame's numPlayers argument an invalid number of players will throw
  //an IllegalArgumentException.
  @Test(expected = IllegalArgumentException.class)
  public void negativeNumPlayersThrowsException() {
    StringBuffer sb = new StringBuffer();
    StringReader sr = new StringReader(quitGame);
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    try {
      whistControl.playGame(game, -2);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid number of players");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void HighNumPlayersThrowsException() {
    StringBuffer sb = new StringBuffer();
    StringReader sr = new StringReader(quitGame);
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    try {
      whistControl.playGame(game, 106);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid number of players");
    }
  }

  //The String quitGame enters a q into the game, and it should output "Game quit prematurely" at
  //one point or another
  @Test
  public void quitGameWorks() {
    StringBuffer sb = new StringBuffer();
    StringReader sr = new StringReader(quitGame);
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Game quit prematurely."));
  }

  //The String playQuitGame enters a 0 then a q into the game, checking that a game still quits
  //prematurely after a play is made
  @Test
  public void playQuitGameWorks() {
    StringBuffer sb = new StringBuffer();
    String playQuitGame = "0 q";
    StringReader sr = new StringReader(playQuitGame); //String we pass in to StringReader
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Game quit prematurely."));
  }

  @Test
  public void oneHandTwoWins() {
    StringBuffer sb = new StringBuffer();
    String playerTwoWin = "1 0 0 0 q";
    StringReader sr = new StringReader(playerTwoWin);
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Number of players: 4"));
    assertTrue(sb.toString().contains("Player 1:"));
    assertTrue(sb.toString().contains("Player 2:"));
    assertTrue(sb.toString().contains("Player 3:"));
    assertTrue(sb.toString().contains("Player 4:"));
    assertTrue(sb.toString().contains("Player 1 score: "));
    assertTrue(sb.toString().contains("Player 2 score: 1"));
    assertTrue(sb.toString().contains("Player 3 score: "));
    assertTrue(sb.toString().contains("Player 4 score: "));

  }

  @Test
  public void oneHandThreeWins() {
    StringBuffer sb = new StringBuffer();
    String playerThreeWin = "1 1 0 0 q";
    StringReader sr = new StringReader(playerThreeWin);
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Number of players: 4"));
    assertTrue(sb.toString().contains("Player 1:"));
    assertTrue(sb.toString().contains("Player 2:"));
    assertTrue(sb.toString().contains("Player 3:"));
    assertTrue(sb.toString().contains("Player 4:"));
    assertTrue(sb.toString().contains("Player 1 score: "));
    assertTrue(sb.toString().contains("Player 2 score: "));
    assertTrue(sb.toString().contains("Player 3 score: 1"));
    assertTrue(sb.toString().contains("Player 4 score: "));

  }

  @Test
  public void oneHandFourWins() {
    StringBuffer sb = new StringBuffer();
    String playerFourWin = "1 1 1 0 q";
    StringReader sr = new StringReader(playerFourWin);
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Number of players: 4"));
    assertTrue(sb.toString().contains("Player 1:"));
    assertTrue(sb.toString().contains("Player 2:"));
    assertTrue(sb.toString().contains("Player 3:"));
    assertTrue(sb.toString().contains("Player 4:"));
    assertTrue(sb.toString().contains("Player 1 score: "));
    assertTrue(sb.toString().contains("Player 2 score: "));
    assertTrue(sb.toString().contains("Player 3 score: "));
    assertTrue(sb.toString().contains("Player 4 score: 1"));

  }

  //The String fourplay enters a 0 then the <Enter> key 52 times until the game is over. So the
  //StringBuffer should contain the scores and the special message that a player won and
  //the game is over.
  @Test
  public void fourPlayWorks() {
    StringBuffer sb = new StringBuffer();
    String fourplay = "0 0 0 0 0 0 0 0 0 0 0 0 0 " +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 " +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 " +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 ";
    StringReader sr = new StringReader(fourplay);
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Number of players: 4"));
    assertTrue(sb.toString().contains("Player 1:"));
    assertTrue(sb.toString().contains("Player 2:"));
    assertTrue(sb.toString().contains("Player 3:"));
    assertTrue(sb.toString().contains("Player 4:"));
    assertTrue(sb.toString().contains("Player 1 score: 13"));
    assertTrue(sb.toString().contains("Player 2 score: "));
    assertTrue(sb.toString().contains("Player 3 score: "));
    assertTrue(sb.toString().contains("Player 4 score: "));
    assertTrue(sb.toString().contains("Game over. Player 1 won"));
  }

  //The String fiveplay enters a 0 then the <Enter> key 52 times until the game is over. So the
  //StringBuffer should contain the scores and the special message that a player won and
  //the game is over.
  @Test
  public void fivePlayWorks() {
    StringBuffer sb = new StringBuffer();
      //Same contents as fourplay, just changed name for clearness
  String fiveplay = "0 0 0 0 0 0 0 0 0 0 0 0 0 " +
          "0 0 0 0 0 0 0 0 0 0 0 0 0 " +
          "0 0 0 0 0 0 0 0 0 0 0 0 0 " +
          "0 0 0 0 0 0 0 0 0 0 0 0 0 ";
    StringReader sr = new StringReader(fiveplay);
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 5);

    assertTrue(sb.toString().contains("Number of players: 5"));
    assertTrue(sb.toString().contains("Player 1:"));
    assertTrue(sb.toString().contains("Player 2:"));
    assertTrue(sb.toString().contains("Player 3:"));
    assertTrue(sb.toString().contains("Player 4:"));
    assertTrue(sb.toString().contains("Player 5:"));
    assertTrue(sb.toString().contains("Player 1 score: 11"));
    assertTrue(sb.toString().contains("Player 2 score: "));
    assertTrue(sb.toString().contains("Player 3 score: "));
    assertTrue(sb.toString().contains("Player 4 score: "));
    assertTrue(sb.toString().contains("Player 5 score: "));
    assertTrue(sb.toString().contains("Game over. Player 1 won"));
  }

  @Test
  public void playerTwoWins() {
    StringBuffer sb = new StringBuffer();
    //Same contents as fourplay, just changed name for clearness
    String fiveplay = "1 0 0 0 0 0 0 0 0 1 0 0 0 " +
            "0 0 1 0 0 0 0 0 0 0 0 1 0 " +
            "0 0 0 1 0 0 0 0 1 0 0 0 0 " +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 ";
    StringReader sr = new StringReader(fiveplay);
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 5);

    assertTrue(sb.toString().contains("Number of players: 5"));
    assertTrue(sb.toString().contains("Player 1:"));
    assertTrue(sb.toString().contains("Player 2:"));
    assertTrue(sb.toString().contains("Player 3:"));
    assertTrue(sb.toString().contains("Player 4:"));
    assertTrue(sb.toString().contains("Player 5:"));
    assertTrue(sb.toString().contains("Player 1 score: 4"));
    assertTrue(sb.toString().contains("Player 2 score: 7"));
    assertTrue(sb.toString().contains("Player 3 score: "));
    assertTrue(sb.toString().contains("Player 4 score: "));
    assertTrue(sb.toString().contains("Player 5 score: "));
    assertTrue(sb.toString().contains("Game over. Player 2 won"));
  }


  //Test that a bad inputs such as a letter that is not the letter q will return the exception that
  //tells the user "Try again. Invalid input."
  @Test
  public void wrongInputsLetter1() {
    String badInput = "a\nq\n";
    StringBuffer sb = new StringBuffer();
    StringReader sr = new StringReader(badInput); //String we pass in to StringReader
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Try again. Invalid input."));
  }

  @Test
  public void wrongInputsLetter2() {
    String badInput = "A\nq\n";
    StringBuffer sb = new StringBuffer();
    StringReader sr = new StringReader(badInput); //String we pass in to StringReader
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Try again. Invalid input."));
  }

  @Test
  public void wrongInputsLetter3() {
    String badInput = "Q\nq\n";
    StringBuffer sb = new StringBuffer();
    StringReader sr = new StringReader(badInput); //String we pass in to StringReader
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Try again. Invalid input."));
  }

  @Test
  public void wrongInputsLetter4() {
    String badInput = "ACEOFSPADES\nq\n";
    StringBuffer sb = new StringBuffer();
    StringReader sr = new StringReader(badInput); //String we pass in to StringReader
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Try again. Invalid input."));
  }

  //Tests to see if playGame can catch an exception in the play method
  @Test
  public void playExceptionCaught() {
    String badInput = "1111\nq\n";
    StringBuffer sb = new StringBuffer();
    StringReader sr = new StringReader(badInput); //String we pass in to StringReader
    WhistModel game = new WhistModel();
    WhistController whistControl = new WhistController(sr, sb);
    whistControl.playGame(game, 4);

    assertTrue(sb.toString().contains("Try again. Invalid input."));
    assertTrue(sb.toString().contains("cardIdx must be between 0 (first card) and the size of " +
            "your hand"));
  }

}
