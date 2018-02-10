import org.junit.Assert;
import org.junit.Test;

import cs3500.hw02.Value;

import static org.junit.Assert.assertEquals;

// Test Value methods
public class ValueTest {

  @Test
  public void valueNumWorks() {
    Assert.assertEquals(Value.Two.valueNum(), 2);
    assertEquals(Value.Three.valueNum(), 3);
    assertEquals(Value.Four.valueNum(), 4);
    assertEquals(Value.Five.valueNum(), 5);
    assertEquals(Value.Six.valueNum(), 6);
    assertEquals(Value.Seven.valueNum(), 7);
    assertEquals(Value.Eight.valueNum(), 8);
    assertEquals(Value.Nine.valueNum(), 9);
    assertEquals(Value.Ten.valueNum(), 10);
    assertEquals(Value.Jack.valueNum(), 11);
    assertEquals(Value.Queen.valueNum(), 12);
    assertEquals(Value.King.valueNum(), 13);
    assertEquals(Value.Ace.valueNum(), 14);
  }

  @Test
  public void valueToStringWorks() {
    assertEquals(Value.Two.toString(), "2");
    assertEquals(Value.Three.toString(), "3");
    assertEquals(Value.Four.toString(), "4");
    assertEquals(Value.Five.toString(), "5");
    assertEquals(Value.Six.toString(), "6");
    assertEquals(Value.Seven.toString(), "7");
    assertEquals(Value.Eight.toString(), "8");
    assertEquals(Value.Nine.toString(), "9");
    assertEquals(Value.Ten.toString(), "10");
    assertEquals(Value.Jack.toString(), "J");
    assertEquals(Value.Queen.toString(), "Q");
    assertEquals(Value.King.toString(), "K");
    assertEquals(Value.Ace.toString(), "A");
  }

  @Test
  public void getValueWorks() {
    assertEquals(Value.getValue(2), Value.Two);
    assertEquals(Value.getValue(3), Value.Three);
    assertEquals(Value.getValue(4), Value.Four);
    assertEquals(Value.getValue(5), Value.Five);
    assertEquals(Value.getValue(6), Value.Six);
    assertEquals(Value.getValue(7), Value.Seven);
    assertEquals(Value.getValue(8), Value.Eight);
    assertEquals(Value.getValue(9), Value.Nine);
    assertEquals(Value.getValue(10), Value.Ten);
    assertEquals(Value.getValue(11), Value.Jack);
    assertEquals(Value.getValue(12), Value.Queen);
    assertEquals(Value.getValue(13), Value.King);
    assertEquals(Value.getValue(14), Value.Ace);
  }

}
