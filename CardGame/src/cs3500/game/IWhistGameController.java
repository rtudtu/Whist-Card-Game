package cs3500.game;

import cs3500.hw03.CardGameModel;

/**
 * Created by Richard on 8/8/2016.
 */
public interface IWhistGameController {

  /**
   * Start the provided game model
   *
   * @param numPlayers Number of players in the game
   * @throws IllegalArgumentException if the model is null or number of players passed to it is
   *                                  invalid.
   */
  void playGame(WhistTrumpGameModel game, int numPlayers) throws IllegalArgumentException;

}