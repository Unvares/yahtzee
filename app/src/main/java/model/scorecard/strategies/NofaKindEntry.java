package model.scorecard.strategies;

import java.util.List;

/**
 * This class represents the N of a kind entry in the score card.
 */
public class NofaKindEntry extends ScoreCardEntry {
  private int numberOfKind;

  /**
   * Constructor for NOfAKindEntry.
   * Initializes the entry with the given name and numberOfKind.
   *
   * @param name         The name of the entry.
   * @param numberOfKind The number of a kind.
   */
  public NofaKindEntry(String name, int numberOfKind) {
    super(name);
    this.numberOfKind = numberOfKind;
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
      if (counts[i] >= numberOfKind) {
        setScore((i + 1) * numberOfKind);
        return;
      }
    }

    setScore(0);
  }

}
