package model.scorecard.strategies;

import java.util.List;

/**
 * This class represents the Full House entry in the score card.
 */
public class FullHouseEntry extends ScoreCardEntry {

  /**
   * Constructor for FullHouseEntry.
   * Initializes the entry with the given name.
   *
   * @param name The name of the entry.
   */
  public FullHouseEntry(String name) {
    super(name);
  }

  /**
   * Sets the score from the given dice values.
   * The score is 25 if there is a three of a kind and a two of a kind, 0
   * otherwise.
   *
   * @param diceValues The values of the dices.
   */
  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }

    boolean hasTwoOfaKind = hasNofaKind(counts, 2);
    boolean hasThreeOfaKind = hasNofaKind(counts, 3);

    if (hasTwoOfaKind && hasThreeOfaKind) {
      setScore(25);
    } else {
      setScore(0);
    }
  }

  /**
   * Returns whether there is a n of a kind in the given counts.
   *
   * @param counts The counts of the dice values.
   * @param n      The number of a kind.
   * @return True if there is a n of a kind, false otherwise.
   */
  private boolean hasNofaKind(int[] counts, int n) {
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= n) {
        return true;
      }
    }
    return false;
  }

}
