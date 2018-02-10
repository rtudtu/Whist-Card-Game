package cs3500.hw03;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import cs3500.hw02.Card;

//This is the main method I used to test WhistModel and it's methods,
// and WhistController and it's method: playGame
public class TestController {
  // Starts a Whist Game
  public static void main(String[] args) {
    WhistModel whistGame = new WhistModel();
    List<Card> deck = whistGame.getDeck();
    Collections.sort(deck);

    new WhistController(new InputStreamReader(System.in), System.out).playGame(whistGame, 5);
  }
}