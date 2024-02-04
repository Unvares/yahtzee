package model.ScoreCard.strategies;

import java.util.List;

/**
 * This class represents the Villa entry in the score card.
 */
public class VillaEntry extends ScoreCardEntry {

  /**
   * Constructor for VillaEntry.
   * Initializes the entry with the given name.
   *
   * @param name The name of the entry.
   */
  public VillaEntry(String name) {
    super(name);
  }

  /**
   * Sets the score from the given dice values.
   * The score is the sum of the dice values if there is a villa (two three of a
   * kinds),
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

    boolean hasVilla = hasVilla(counts);

    if (hasVilla) {
      int sum = 0;
      for (int diceValue : diceValues) {
        sum += diceValue;
      }
      setScore(sum);
    } else {
      setScore(0);
    }
  }

  /**
   * Checks if there is a villa (two three of a kinds) in the given counts.
   *
   * @param counts The counts of the dice values.
   * @return True if there is a villa, false otherwise.
   */
  private boolean hasVilla(int[] counts) {
    int countThreeOfAKind = 0;

    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= 3) {
        countThreeOfAKind++;
      }
    }
    return countThreeOfAKind == 2;
  }

}
