package model.ScoreCard.strategies;

import java.util.List;

/**
 * This class represents the Sum of Values entry in the score card.
 */
public class SumOfValuesEntry extends ScoreCardEntry {
  private int value;

  /**
   * Constructor for SumOfValuesEntry.
   * Initializes the entry with the given name and value.
   *
   * @param name  The name of the entry.
   * @param value The value to be summed.
   */
  public SumOfValuesEntry(String name, int value) {
    super(name);
    this.value = value;
  }

  /**
   * Sets the score from the given dice values.
   * The score is the sum of the dice values that are equal to the value.
   *
   * @param diceValues The values of the dices.
   */
  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int sum = 0;
    for (int diceValue : diceValues) {
      if (diceValue == value) {
        sum += diceValue;
      }
    }
    setScore(sum);
  }
}
