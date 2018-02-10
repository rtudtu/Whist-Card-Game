package cs3500.hw04;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw03.WhistController;

public class TestTrumpController {
  // Tests a Whist Trump Game
  public static void main(String[] args) {
    WhistTrumpModel whistGame = new WhistTrumpModel();
    List<Card> deck = whistGame.getDeck();
    Collections.sort(deck);

    new WhistController(new InputStreamReader(System.in), System.out).playGame(whistGame, 4);
  }
}
