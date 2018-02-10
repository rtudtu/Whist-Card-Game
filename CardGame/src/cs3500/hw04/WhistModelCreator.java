package cs3500.hw04;


import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;

/**
 * Represents a WhistModel "factory" or creator
 */
public class WhistModelCreator {

  //enum for ModelType - can only be two types
  public enum ModelType {
    TRUMP, NOTRUMP
  }

  /**
   * Creates
   * @param type
   * @return
   */
  public static CardGameModel create(ModelType type) {
    if (type.equals(ModelType.TRUMP)) {
      return new WhistTrumpModel();
    } else { // If type.equals(ModelType.NOTRUMP))
      return new WhistModel();
    }
    //Should not throw an exception as ModelType is an enum and can only be 2 different types
  }
}

