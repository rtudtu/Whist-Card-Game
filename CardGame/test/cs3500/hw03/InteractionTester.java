package cs3500.hw03;


import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import cs3500.hw02.Card;

import static org.junit.Assert.assertEquals;

interface Interaction {
  void apply(StringBuilder in, StringBuilder out);}
class PrintInteraction implements Interaction {
  String[] lines;
  PrintInteraction(String... lines) {
    this.lines = lines;
  }
  public void apply(StringBuilder in, StringBuilder out) {
    for (String line : lines) {
      out.append(line).append("\n");}}}
class InputInteraction implements Interaction {
  String input;
  InputInteraction(String input) {
    this.input = input;
  }
  public void apply(StringBuilder in, StringBuilder out) {
    in.append(input);
  }}
public class InteractionTester {
  void testRun(CardGameModel<Card> model, int numPlayers, Interaction... interactions)
          throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();
    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }
    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();
    WhistController controller = new WhistController(input, actualOutput);
    controller.playGame(model, numPlayers);
    assertEquals(expectedOutput.toString(), actualOutput.toString());}
  @Test
  public void testRunWorks() throws IOException {
    InteractionTester t = new InteractionTester();
    WhistModel game = new WhistModel();
    List<Card> deck = game.getDeck();
    Collections.sort(deck);
    t.testRun(game, 4, new PrintInteraction("Number of players: 4\n" +
            "Player 1: 9♣, 4♣, 3♣, 2♣, 9♦, 5♦, J♥, 10♥, 9♥, 6♥, 9♠, 6♠, 3♠\n" +
            "Player 2: Q♣, 7♣, A♦, Q♦, Q♥, 8♥, 7♥, 5♥, A♠, Q♠, 8♠, 7♠, 4♠\n" +
            "Player 3: K♣, 10♣, 6♣, 5♣, J♦, 6♦, 3♦, 2♦, A♥, 3♥, K♠, J♠, 5♠\n" +
            "Player 4: A♣, J♣, 8♣, K♦, 10♦, 8♦, 7♦, 4♦, K♥, 4♥, 2♥, 10♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Player 4 score: 0\n" +
            "Turn: Player 1"), new InputInteraction("q"), new PrintInteraction("Game quit prematurely."));
  }

}