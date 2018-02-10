package cs3500.game;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw03.WhistController;
import cs3500.hw03.WhistModel;

/**
 * Created by Richard on 8/8/2016.
 */
public class TestGame {
  // Starts a single player Whist Game against X number of AI players
  public static void main(String[] args) {
    WhistTrumpGameModel whistGame = new WhistTrumpGameModel();
    List<Card> deck = whistGame.getDeck();
    Collections.sort(deck);

    new WhistGameController(new InputStreamReader(System.in), System.out).playGame(whistGame, 4);
  }
}
