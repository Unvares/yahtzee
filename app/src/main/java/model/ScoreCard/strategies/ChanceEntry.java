package model.ScoreCard.strategies;

import java.util.List;

/**
 * This class represents the Chance entry in the score card.
 */
public class ChanceEntry extends ScoreCardEntry {

  /**
   * Constructor for ChanceEntry.
   * Initializes the entry with the name "chance".
   */
  public ChanceEntry() {
    super("chance");
  }

  /**
   * Sets the score from the given dice values.
   * The score is the sum of all dice values.
   *
   * @param diceValues The values of the dices.
   */
  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int sum = 0;
    for (int diceValue : diceValues) {
      sum += diceValue;
    }
    setScore(sum);
  }
}
