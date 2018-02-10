package cs3500.hw02;

/**
 * Represents a Value of a card ranging from 2 - 14 2 being 2, 3 being 3, ... 10 being 10, 11 being
 * Jack, 12 being Queen, 13 being King, and 14 being Ace
 */
public enum Value {
  Two(2, "2"), Three(3, "3"), Four(4, "4"), Five(5, "5"), Six(6, "6"),
  Seven(7, "7"), Eight(8, "8"), Nine(9, "9"), Ten(10, "10"), Jack(11, "J"),
  Queen(12, "Q"), King(13, "K"), Ace(14, "A");

  private final int i;
  private final String token;

  /**
   * Constructs a Value given two parameters seen in the value of each suit above
   *
   * @param i     number Value is associated with
   * @param token String Value is associated with
   */
  Value(int i, String token) {
    this.i = i;
    this.token = token;
  }

  /**
   * Returns the value of this card
   *
   * @return the value of a card ranging from 2-14 inclusive
   */
  public int valueNum() {
    return this.i;
  }

  /**
   * Returns a String of the card's value
   *
   * @return the value of a card: For the values 1, 11, 12, and 13, return A, J, Q, and K
   * respectively, standing for Aces, Jacks, Queens, and Kings. For the values 2-10, return the
   * value in string form (instead of int)
   * @throws IllegalArgumentException if none of the cases match
   */
  @Override
  public String toString() {
    return this.token;
  }

  /**
   * Returns the Value associated with the num
   *
   * @param num int that is given to the cases
   * @return Returns a Value based on the number it is given
   */
  public static Value getValue(int num) {
    switch (num) {
      case 2:
        return Value.Two;
      case 3:
        return Value.Three;
      case 4:
        return Value.Four;
      case 5:
        return Value.Five;
      case 6:
        return Value.Six;
      case 7:
        return Value.Seven;
      case 8:
        return Value.Eight;
      case 9:
        return Value.Nine;
      case 10:
        return Value.Ten;
      case 11:
        return Value.Jack;
      case 12:
        return Value.Queen;
      case 13:
        return Value.King;
      case 14:
        return Value.Ace;
      default:
        throw new IllegalArgumentException("num must be 1-4");
    }
  }

}

