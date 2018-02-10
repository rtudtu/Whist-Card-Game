package cs3500.hw03;


public interface IWhistController {

  /**
   * Start the provided game model
   *
   * @param numPlayers Number of players in the game
   * @throws IllegalArgumentException if the model is null or number of players passed to it is
   *                                  invalid.
   */
  <K> void playGame(CardGameModel<K> game, int numPlayers) throws IllegalArgumentException;

}
