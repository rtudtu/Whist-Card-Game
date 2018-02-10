package cs3500.hw03;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.Hand;
import cs3500.hw02.Suit;
import cs3500.hw02.GenericStandardDeckGame;

/**
 * Represents a game that extends the methods of GenericStandardDeckGame
 */
public class WhistModel extends GenericStandardDeckGame implements CardGameModel<Card> {

  //keeping track of some fields:
  protected int currentPlayer = 0; // The current player (playerNo)
  protected int handsLeft = 0; // When some players run out of cards, but at least 2 still
  // have cards, handsLeft keeps track of how many hands with cards are left
  protected Hand currentHand; // added for convenience and readability
  protected Card currentCard; // added for convenience and readability
  protected int winningHand; // Keep track of the winning player to give them a point for winning
  protected Card winningCard; // Keep track of the winning Card so we can compare the
  // other player's Cards to this winningCard
  protected Suit trickSuit; // The suit of the current trick - set when the first player plays
  protected List<Card> trick = new ArrayList<Card>(); // The "pile" or trick deck - keeps track
  // of all the played cards in any given trick

  // empty constructor for WhistModel
  public WhistModel() {

  }

  public String trickToString() {
    String result = "";
    for (int i = 0; i < trick.size(); i++) {
      if (i != trick.size() - 1) {
        result = result + trick.get(i).cardToString() + " ";
      } else {
        result = result + trick.get(i).cardToString();
      }
    }
    return result;
  }

  /**
   * Returns an ArrayList of String based on which players have the highest score. Returns an
   * ArrayList of one String of the player with the most tricks won unless there is a tie. In the
   * case of a tie, determineWinningPlayer will return an ArrayList of String of all the players
   * who tied.
   *
   * @return an ArrayList<String> with the list of winners in there to be used in getGameState's
   * secret message
   */
  public ArrayList<String> determineWinningPlayer() {
    ArrayList<Integer> winningPlayers;
    ArrayList<String> winningPlayersString;
    winningPlayers = new ArrayList<Integer>();
    winningPlayersString = new ArrayList<String>();
    Hand winningPlayerHand;
    winningPlayerHand = hands.get(0);
    int winner = 0;
    // go through and replace the winner with another playerNo if their score beats the winner's
    // score - set them as the new winner and repeat
    for (int i = 1; i < hands.size(); i++) {
      if (hands.get(i).score > winningPlayerHand.score) {
        winningPlayerHand = hands.get(i);
        winner = i;
      }
    }
    // add the winner to the list of players with the highest scores
    winningPlayers.add(winner);
    // If there are any ties, add them onto the ArrayList of winningPlayers
    for (int i = 1; i < hands.size(); i++) {
      if (hands.get(i).score == winningPlayerHand.score && hands.get(winner) != hands.get(i)) {
        winningPlayers.add(i);
      }
    }
    //Turn the list of Integers to Strings and return the Strings at the end
    for (int i = 0; i < winningPlayers.size(); i++) {
      winningPlayersString.add(Integer.toString(winningPlayers.get(i) + 1));
    }
    return winningPlayersString;
  }

  @Override
  public void play(int playerNo, int cardIdx) {
    // If the game is over, no player can play a card
    if (isGameOver()) {
      throw new IllegalArgumentException("Game is over, no player may play any card");
    }
    // The playerNo cannot be anything but the currentPlayer - only the currentPlayer can use the
    // play method and actually play a card.
    if (currentPlayer != playerNo) {
      throw new IllegalArgumentException("Not this player's turn or invalid playerNo, " +
              "please check getCurrentPlayer");
    }
    // The cardIdx given cannot be negative or greater than the player's hand size
    if (cardIdx >= hands.get(playerNo).getHand().size() || cardIdx < 0) {
      throw new IllegalArgumentException("cardIdx must be between 0 (first card) " +
              "and the size of your hand");
    }
    if (playerNo > hands.size() || playerNo < 0) {
      throw new IllegalArgumentException("playerNo must be between 0 (first player) " +
              "and the size of the hands(number of players)");
    }
    //For faster typing and better readability:
    currentHand = hands.get(playerNo);
    currentCard = hands.get(playerNo).getHand().get(cardIdx);

    // If the trick size is 0, then it is the first player's turn and they may play any card
    // The played card's suit becomes the trick's suit. Only allowing other players to win by
    // playing the same suit
    if (trick.size() == 0) {
      currentHand.getHand().remove(currentCard);
      trick.add(currentCard);
      winningHand = playerNo;
      winningCard = currentCard;
      trickSuit = currentCard.retrieveSuit();
      // If the current player plays a card matching the suit of the trick, then add their card
      // to the trick "pile" and determine if that card's value is higher than the winningCard's
      // value
    } else if (currentCard.retrieveSuit() == trickSuit) {
      currentHand.getHand().remove(currentCard);
      trick.add(currentCard);
      if (currentCard.retrieveValue().valueNum() >
              winningCard.retrieveValue().valueNum()) {
        winningHand = playerNo;
        winningCard = currentCard;
      }
      // If the current player plays a card that does not match the suit of the trick, and they do
      // not have a card with the same suit as the trick's suit, then add their card to the trick
      // but do not give them a chance to win the trick - winningCard does not change
    } else if (currentCard.retrieveSuit() != trickSuit &&
            !currentHand.containsSuit(trickSuit)) {
      currentHand.getHand().remove(currentCard);
      trick.add(currentCard);
      // If the current player plays a card that does not match the suit of the trick, and they do
      // have a card with the same suit as the trick's suit, then throw an IllegalArgumentException
      // stating that they cannot discard that card if they have a card that can be played
    } else if (currentCard.retrieveSuit() != trickSuit &&
            currentHand.containsSuit(trickSuit)) {
      throw new IllegalArgumentException("Must play a card matching the trick's current suit");
    }
    // If the trick size is less than the hands.size, then that means there are still players
    // who haven't played yet. So advance the currentPlayer and mod it
    if (trick.size() < hands.size()) {
      currentPlayer = currentPlayer + 1;
      currentPlayer = currentPlayer % hands.size();
    }

    // If the trick's size reaches the hands size, then every player has played. Also, if the
    // winningHand's size is not empty, set the currentPlayer (next player's turn) to winningHand
    // If the winningHand does not have a card to play, the next if statement should run
    if (trick.size() >= hands.size() && hands.get(winningHand).getHand().size() != 0 &&
            hands.get(hands.size() - 1).getHand().size() > 0) {
      hands.get(winningHand).score = hands.get(winningHand).score + 1;
      currentPlayer = winningHand;
      trick = new ArrayList<Card>();
    }
    // If the winner of the previous trick has run out of cards but other players can still play,
    // we want to jump to the 0th player's turn - the 0th player has to have the same or more
    // cards than other players after every trick
    if (trick.size() >= hands.size() && hands.get(winningHand).getHand().size() == 0) {
      hands.get(winningHand).score = hands.get(winningHand).score + 1;
      currentPlayer = 0;
      trick = new ArrayList<Card>();
      for (int i = 0; i < hands.size(); i++) {
        if (hands.get(i).getHand().size() == 1) {
          handsLeft = handsLeft + 1;
        }
      }
    }
    // If it is the last trick (last player's hand size is 0), then do the same thing as the
    // previous if statement, we want handsLeft to start counting as it is the last trick
    if (trick.size() >= hands.size() && hands.get(hands.size() - 1).getHand().size() == 0) {
      hands.get(winningHand).score = hands.get(winningHand).score + 1;
      currentPlayer = 0;
      trick = new ArrayList<Card>();
      for (int i = 0; i < hands.size(); i++) {
        if (hands.get(i).getHand().size() == 1) {
          handsLeft = handsLeft + 1;
        }
      }
    }
    // If the trick size is equal to the handsLeft, and the trick size is not 0, then we want
    // to end the trick as all the remaining players with cards left has played.
    if (trick.size() == handsLeft && trick.size() != 0) {
      hands.get(winningHand).score = hands.get(winningHand).score + 1;
      trick = new ArrayList<Card>();
    }

  }

  @Override
  public int getCurrentPlayer() throws IllegalArgumentException {
    //If the game is over, getCurrentPlayer cannot get the current player, throws an exception
    if (isGameOver()) {
      throw new IllegalArgumentException("Game is over, cannot get a currentPlayer");
    }
    //If there are no players, cannot get a currentPlayer
    if (hands.size() == 0) {
      throw new IllegalArgumentException("There are no players");
    }
    //Simply return the field keeping track of the current player
    return currentPlayer;
  }

  @Override
  public boolean isGameOver() {
    int emptyHands = 0;
    // Increase the number of empty hands by 1 for each hand that is empty
    for (int i = 0; i < hands.size(); i++) {
      if (hands.get(i).getHand().size() == 0) {
        emptyHands = emptyHands + 1;
      }
    }
    // If there is less than 2 players with cards, then return true and the game is over
    if (emptyHands > hands.size() - 2 && trick.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String getGameState() {
    // result we print
    String result = "";
    // winner or list of winners with commas separating them
    String winners = "";
    // If we have no players, just output:
    if (hands.size() == 0) {
      return "Number of players: 0";
    } else {
      // Add the list of winning player(s) to the field winners
      for (int i = 0; i < determineWinningPlayer().size(); i++) {
        if (i != determineWinningPlayer().size() - 1) {
          winners = winners + determineWinningPlayer().get(i) + ", ";
        } else {
          winners = winners + determineWinningPlayer().get(i);
        }
      }
      // Format follows Assignment Instructions
      result = result + "Number of players: " + hands.size() + "\n";
      for (int i = 0; i < hands.size(); i++) {
        result = result + "Player " + Integer.toString(i + 1) + ":" +
                hands.get(i).handToString() + "\n";
      }
      for (int i = 0; i < hands.size(); i++) {
        result = result + "Player " + Integer.toString(i + 1) + " score: " +
                Integer.toString(hands.get(i).score) + "\n";
      }
      if (!isGameOver()) {
        result = result + "Turn: Player " + Integer.toString(getCurrentPlayer() + 1);
      } else {
        result = result + "Game over. Player " + winners + " won.";
      }
      return result;
    }
  }
}
