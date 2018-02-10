package cs3500.hw03;


import cs3500.hw02.GenericCardGameModel;

public interface CardGameModel<K> extends GenericCardGameModel<K> {

  /**
   * Plays the card at the index cardIdx from the set of cards for player number playerNo. Both
   * player numbers and card indices begin at 0
   *
   * @param playerNo Player number that is playing the card
   * @param cardIdx  Card index that is being played
   * @throws IllegalArgumentException If the current card cannot be played, the given playerNo or
   *                                  cardIdx is invalid, or the game is already over, the method
   *                                  will throw an exception
   */
  void play(int playerNo, int cardIdx) throws IllegalArgumentException;

  /**
   * Returns the player whose turn it is to play - with player number beginning with 0
   *
   * @return an Integer based on which player's turn it is
   * @throws IllegalArgumentException if the game has ended and there is no currentplayer.
   *                                  isGameOver is true and no players can make a turn
   */
  int getCurrentPlayer() throws IllegalArgumentException;

  /**
   * This method returns a boolean based on the game's "state", whether it is over or not
   *
   * @return a boolean, true if game is over and false if the game is not over yet
   */
  boolean isGameOver();
}
