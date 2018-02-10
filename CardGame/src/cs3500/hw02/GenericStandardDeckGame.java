package cs3500.hw02;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a game
 */
public class GenericStandardDeckGame implements GenericCardGameModel<Card> {

  // Made public for WhistModel class to use too
  protected ArrayList<Hand> hands = new ArrayList<Hand>();
  protected int numPlayers;

  /**
   * Empty constructor for a game
   */
  public GenericStandardDeckGame() {
    this.hands = new ArrayList<Hand>();
  }


  /**
   * Checks to see if a list has any duplicate cards
   *
   * @param cards list of cards
   * @return Returns true if there is a duplicate among the list of cards, false if there isn't
   * Changed to List<Card> from ArrayList<Card> to use in Trick class
   */
  public static boolean duplicateCard(List<Card> cards) {
    boolean duplicate = false;
    for (int i = 0; i < cards.size(); i++) {
      for (int j = 0; j < cards.size(); j++) {
        if ((i != j) && (cards.get(i).compareTo(cards.get(j)) == 0)) {
          return duplicate = true;
        }
      }
    }
    return duplicate;
  }

  /**
   * Get a deck of all the valid playing cards for this game. Different card games start with
   * different decks. This method will return a deck of valid playing cards in no particular order.
   *
   * A deck is invalid if it does not contain all the cards it should, or it contains duplicate
   * cards, or it contains invalid cards (e.g. invalid suit and value)
   *
   * @return a List of K objects, where K is the type of card
   */
  @Override
  public List<Card> getDeck() {
    ArrayList<Card> deck = new ArrayList<Card>();
    for (int i = 1; i < 5; i++) {
      for (int j = 14; j > 1; j--) {
        deck.add(Card.getCard(j, i));
        //     value = Card.getCard(j, i).value.toString();
        //     suit = Card.getCard(j, i).suit.toString();
      }
    }
    if (duplicateCard(deck)) {
      throw new IllegalArgumentException("deck contains duplicates");
    } else if (deck.size() != 52) {
      throw new IllegalArgumentException("deck does not have 52 cards");
    } else {
      Collections.sort(deck);
      return deck;
    }

  }

  /**
   * Start playing with the given deck of cards and the given number of players. This method
   * distributes the deck AS IT IS GIVEN amongst the players, in round-robin fashion e.g. if there
   * are 3 players and 52 cards in the deck, player 0 will get card nos 0,3,6,9,.. and player 1
   * will get card nos. 1,4,7,10,... and so on.
   *
   * The deck is to be distributed in the exact sequence as it is provided. Thus, NO SHUFFLING or
   * REORDERING is allowed.
   *
   * If a deck is to be shuffled, it must be shuffled before calling this method.
   *
   * @param numPlayers the number of players playing this game
   * @param deck       the deck of cards to be distributed among the players to start the game
   * @throws IllegalArgumentException if the number of players is invalid, or if the deck is
   *                                  invalid. The definition of an invalid deck depends on the
   *                                  implementation
   */
  @Override
  public void startPlay(int numPlayers, List<Card> deck) {
    ArrayList<Card> deck2 = new ArrayList<Card>();
    int T = deck.size(); // deck size
    int P = numPlayers; // # of players
    int RN = 52; // remaining cards
    int PN; // at least how many cards per player
    int counter = 0;
    // Added to startPlay to throw an exception when 0 players or less is passed into startPlay
    // Used in WhistModelTest too
    if (numPlayers <= 0) {
      throw new IllegalArgumentException("Cannot have 0 or negative players");
    } else {
      PN = T / P;
      RN = T % P;
      // does not bother running rest of startPlay if deck is invalid
      if (deck.size() != 52) {
        throw new IllegalArgumentException("bad deck");
      }
      if (duplicateCard(deck2)) {
        throw new IllegalArgumentException("bad deck");
      }
      for (int i = 0; i < P; i++) {
        hands.add(new Hand(new ArrayList<Card>()));
      }
      for (int i = 0; i < PN; i++) {
        for (int j = 0; j < P; j++) {
          hands.get(j).addCard(deck.get(counter));
          counter = counter + 1;
        }
      }
      for (int i = 0; i < P; i++) {
        if (RN == 0)
          break;
        hands.get(i).addCard(deck.get(deck.size() - RN));
        RN = RN - 1;
      }
    }
  }

  /**
   * This method will return a string that, when displayed, gives the current state of the game.
   *
   * This string should be in the following format({S} is space, {NL} is a newline, text in italics
   * are comments and do not appear in the string). Replace placeholders with actual data as
   * applicable:
   * <pre>
   * Number of players:{S}X{NL}
   * Player{S}1:{S}P11,{S}P12,{S}P13, ...{NL} <i>(Player 1 cards in sorted order)</i>
   * Player{S}2:{S}P21,{S}P22,{S}P23, ...{NL} <i>(Player 2 cards in sorted order)</i>
   * ...
   * Player{S}X:{S}PX1,{S}PX2,{S}PX3, ...{NL} <i>(Player X cards in sorted order)</i>
   * </pre>
   *
   * @return a string that represents the current state of the game
   */
  @Override
  public String getGameState() {
    String result = "";
    result = result + "Number of players: " + hands.size() + "\n";
    for (int i = 0; i < hands.size(); i++) {
      result = result + "Player " + Integer.toString(i + 1) + ":" +
              hands.get(i).handToString() + "\n";

    }
    return result;
  }

}
