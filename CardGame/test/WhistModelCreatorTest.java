import cs3500.hw02.*;
import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;
import cs3500.hw04.WhistModelCreator;
import cs3500.hw04.WhistTrumpModel;

import org.junit.Test;

import static org.junit.Assert.*;

public class WhistModelCreatorTest {

  @Test
  public void createTrumpModelWorks() {
    CardGameModel<Card> trumpModel = WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);
    assertTrue(trumpModel instanceof WhistTrumpModel);
  }

  @Test
  public void createWhistModelWorks() {
    CardGameModel<Card> whistModel = WhistModelCreator.create(WhistModelCreator.ModelType.NOTRUMP);
    assertTrue(whistModel instanceof WhistModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNullThrowsException() {
    CardGameModel<Card> whistModel = WhistModelCreator.create(null);
  }
}
