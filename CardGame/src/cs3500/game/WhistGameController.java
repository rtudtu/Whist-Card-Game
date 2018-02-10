package cs3500.game;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Richard on 8/8/2016.
 */
public class WhistGameController implements IWhistGameController {
  Readable rd;
  Appendable ap;
  WhistTrumpGameModel game;

  // Constructor for the Whistcontroller - uses Readable and Appendable
  public WhistGameController(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;
  }

  // The outputString that appends a string onto the Appendable
  void outputString(String string) {
    try {
      this.ap.append(string);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void playGame(WhistTrumpGameModel game, int numPlayers) {
    int counter = 0;
    this.game = game;
    Scanner input = new Scanner(rd);
    game.startPlay(numPlayers, game.getDeck());

    //Throw exception if CardGameModel given is null
    if (game == null) {
      throw new IllegalArgumentException("Invalid model");
    }
    //Throw exception if invalid number of players is given:
    //-negative, 0, 1, or over 52 (not reasonable to play a game with this many players. Any more
    // will result in some players not having any cards to start with.
    //-game must start with at least 2 players
    if (numPlayers <= 1 || numPlayers > 52) {
      throw new IllegalArgumentException("Invalid number of players");
    }
    // While the game is not over, do this:
    while (!game.isGameOver()) {
      if(game.getCurrentPlayer() == 0) {
        this.outputString(game.getGameState() + "\n");
        // If the next element is an int:
        if (input.hasNextInt()) {
          int num1;
          num1 = input.nextInt();
          try {
            game.playerPlay(game.getCurrentPlayer(), num1);
          } catch (IllegalArgumentException e) {
            this.outputString("Try again. Invalid input. " + e.getMessage() + "\n");
          }
          this.outputString("Player " + (game.getCurrentPlayer()) + " has played " + game.printableCard + "\n");
        } else {
          String str1 = input.next();
          // If the next element is q:
          if (str1.equals("q")) {
            this.outputString("Game quit prematurely.");
            return;
          } else {
            // If the next element is not a number or q:
            this.outputString("Try again. Invalid input. Must enter a number or the letter q\n");
          }
        }
      } else {
        this.outputString(game.getGameState() + "\n");
        //TODO: MAKE IT SAY WHAT CARD THE AI HAS PLAYED
        game.AIPlay(game.getCurrentPlayer());
        if (game.getCurrentPlayer() + numPlayers == numPlayers) {
          this.outputString("Player " + numPlayers + " has played " + game.printableCard + "\n");
        } else {
          this.outputString("Player " + (game.getCurrentPlayer()) + " has played " + game.printableCard + "\n");
        }
      }

    }
    //When the game is over, output the getGameState with the secret message
    this.outputString(game.getGameState() + "\n");
  }
}
