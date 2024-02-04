package model.ScoreCard.strategies;

import java.util.List;

/**
 * This class represents the N of a kind entry in the score card.
 */
public class NOfAKindEntry extends ScoreCardEntry {
  private int n;

  /**
   * Constructor for NOfAKindEntry.
   * Initializes the entry with the given name and n.
   *
   * @param name The name of the entry.
   * @param n    The number of a kind.
   */
  public NOfAKindEntry(String name, int n) {
    super(name);
    this.n = n;
  }

  /**
   * Sets the score from the given dice values.
   * The score is the sum of the n of a kind dice values, 0 otherwise.
   *
   * @param diceValues The values of the dices.
   */
  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }

    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= n) {
        setScore((i + 1) * n);
        return;
      }
    }

    setScore(0);
  }

}
