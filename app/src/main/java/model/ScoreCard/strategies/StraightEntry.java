package model.ScoreCard.strategies;

import java.util.List;

/**
 * This class represents the Straight entry in the score card.
 */
public class StraightEntry extends ScoreCardEntry {

  /**
   * Constructor for StraightEntry.
   * Initializes the entry with the given name.
   *
   * @param name The name of the entry.
   */
  public StraightEntry(String name) {
    super(name);
  }

  /**
   * Sets the score from the given dice values.
   * The score is 30 for a small straight (4 consecutive numbers),
   * 40 for a large straight (5 consecutive numbers),
   * 50 for a full straight (6 consecutive numbers),
   * and 0 otherwise.
   *
   * @param diceValues The values of the dices.
   */
  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }
    int count = 0;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0) {
        count++;
      }
    }
    if (getName() == "small straight" && count == 4) {
      setScore(30);
    } else if (getName() == "large straight" && count == 5) {
      setScore(40);
    } else if (getName() == "full straight" && count == 6) {
      setScore(50);
    } else {
      setScore(0);
    }
  }
}
