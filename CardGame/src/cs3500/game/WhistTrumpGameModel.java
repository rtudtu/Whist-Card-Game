package cs3500.game;

import java.util.Random;

import cs3500.hw02.*;
import cs3500.hw03.*;
import cs3500.hw04.WhistTrumpModel;


public class WhistTrumpGameModel extends WhistTrumpModel implements CardGameModel<Card>{

  String printableCard = "";
  Suit trumpSuit;

  public WhistTrumpGameModel() {
    super();
    this.trumpSuit = getDeck().get(0).suit;
  }

  public void playerPlay(int playerNo, int cardIdx) {
    super.play(playerNo, cardIdx);
    printableCard = currentCard.value.toString() + currentCard.suit.token;
  }

  public void AIPlay(int playerNo) {
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
    if (playerNo > hands.size() || playerNo < 0) {
      throw new IllegalArgumentException("playerNo must be between 0 (first player) " +
              "and the size of the hands(number of players)");
    }

    int currentCardIdx = 0;
    currentHand = hands.get(playerNo);
    currentCard = new Card(Value.Two, Suit.Clubs);

    if (trick.size() == 0) {
      // TODO?: find the highest value card of any suit and play that card - change tricksuit to that current card's suit
      // For now, pick a random card and play it
      Random rand = new Random();
      int randNum = rand.nextInt(currentHand.getHand().size());
      currentCard = currentHand.getHand().get(randNum);
      currentCardIdx = randNum;
    } else {
      if (currentHand.containsSuit(trickSuit)) {
        for(int i = 0; i < currentHand.getHand().size(); i++) {
          if(currentHand.getHand().get(i).suit == trickSuit) {
            if(currentHand.getHand().get(i).value.valueNum() > winningCard.value.valueNum() && winningCard.suit == trickSuit) {
              if(currentHand.getHand().get(i).value.valueNum() > currentCard.value.valueNum()) {
                currentCard = currentHand.getHand().get(i);
                currentCardIdx = i;
              }
            }
          }
        }
        if(currentCard.value.valueNum() == 2 && currentCard.suit == Suit.Clubs) {
          //play one of the lowest value cards of the current trickSuit
          if(currentCard.value.valueNum() == 2 && currentCard.suit == Suit.Clubs) {
            currentCard = new Card(Value.Ace, Suit.Clubs);
          }
          for(int i = 0; i < currentHand.getHand().size(); i++) {
            if(currentHand.getHand().get(i).suit == trickSuit) {
              if(currentHand.getHand().get(i).value.valueNum() <= currentCard.value.valueNum()) {
                currentCard = currentHand.getHand().get(i);
                currentCardIdx = i;
              }
            }
          }
        }
      } else {
        if (currentHand.containsSuit(trumpSuit)) {
          if (winningCard.suit == trumpSuit) {
            //check if the cards in the hand of the trumpSuit are of a higher value of a current trumpSuit
            for(int i = 0; i < currentHand.getHand().size(); i++) {
              if(currentHand.getHand().get(i).suit == trumpSuit) {
                if(currentHand.getHand().get(i).value.valueNum() > winningCard.value.valueNum()) {
                  if(currentHand.getHand().get(i).value.valueNum() > currentCard.value.valueNum()) {
                    currentCard = currentHand.getHand().get(i);
                    currentCardIdx = i;
                  }
                }
              }
            }
          } else if (winningCard.suit != trumpSuit) {
            //play the highest value trumpSuit
            for(int i = 0; i < currentHand.getHand().size(); i++) {
              if(currentHand.getHand().get(i).suit == trumpSuit) {
                if(currentHand.getHand().get(i).value.valueNum() > currentCard.value.valueNum()) {
                  currentCard = currentHand.getHand().get(i);
                  currentCardIdx = i;
                }
              }
            }
            //TODO AI UPGRADE: THIS AI OPTION CAN BE IMPROVED BY PLAYING A LOWER TRUMPSUIT VALUE IF THE CURRENT AI PLAYER IS THE LAST PLAYER IN THE TRICK
          }
        } else {
          //unable to play a card of the trumpSuit or trickSuit so play one of the lowest value cards in the hand
          if(currentCard.value.valueNum() == 2 && currentCard.suit == Suit.Clubs) {
            currentCard = new Card(Value.Ace, Suit.Clubs);
          }
          for(int i = 0; i < currentHand.getHand().size(); i++) {
            if(currentHand.getHand().get(i).value.valueNum() <= currentCard.value.valueNum()) {
              currentCard = currentHand.getHand().get(i);
              currentCardIdx = i;
            }
          }
        }
      }
    }
    printableCard = currentCard.value.toString() + currentCard.suit.token;
    super.play(playerNo, currentCardIdx);

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
      result = result + "Player 1" + ":" + hands.get(0).handToString() + "\n";
      for (int i = 0; i < hands.size(); i++) {
        result = result + "Player " + Integer.toString(i + 1) + " score: " +
                Integer.toString(hands.get(i).score) + "\n";
      }
      if (!isGameOver()) {
        result = result + "Turn: Player " + Integer.toString(getCurrentPlayer() + 1) + "\n";
        result = result + "Current Trick: " + super.trickToString() + "\n";
        result = result + "Trump Suit: " + trumpSuit.toString();
      } else {
        result = result + "Game over. Player " + winners + " won.";
      }
      return result;
    }
  }

}
