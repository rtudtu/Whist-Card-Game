package cs3500.hw02;

/**
 * Represents a Suit of a card that can be a Club, Diamond, Heart, or Spade 1 being Club, 2 being
 * Diamond, 3 being Heart, and 4 being Spade
 */
public enum Suit {
        // value of each suit
        Clubs(1, "♣"), Diamonds(2, "♦"), Hearts(3, "♥"), Spades(4, "♠");

//Changed to public so I can get the suit
public final int i;
public final String token;

        /**
         * Constructs a Suit given two parameters seen in the value of each suit above
         *
         * @param i     number Suit is associated with
         * @param token String Suit is associated with
         */
        Suit(int i, String token) {
        this.i = i;
        this.token = token;
        }

/**
 * Returns the suit of this card
 *
 * @return the suit of a card ranging from 1 - 4 inclusive
 */
public int suitNum() {
        return this.i;
        }

/**
 * Returns a String of the card's suit given one of the 4 constants
 *
 * @return one of the strings: ♣, ♦, ♥, ♠.
 * @throws IllegalArgumentException if none of the cases match
 */
@Override
public String toString() {
        return this.token;
        }

/**
 * Returns the Suit associated with the num
 *
 * @param num int that is given to the cases
 * @return Returns a Suit based on the number it is given
 */
public static Suit getSuit(int num) {
        switch (num) {
        case 1:
        return Suit.Clubs;
        case 2:
        return Suit.Diamonds;
        case 3:
        return Suit.Hearts;
        case 4:
        return Suit.Spades;
default:
        throw new IllegalArgumentException("num must be 1-4");
        }
        }

        }
