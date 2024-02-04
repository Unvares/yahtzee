package model.ScoreCard.strategies;

import java.util.List;

/**
 * This class represents the Tower entry in the score card.
 */
public class TowerEntry extends ScoreCardEntry {

  /**
   * Constructor for TowerEntry.
   * Initializes the entry with the given name.
   *
   * @param name The name of the entry.
   */
  public TowerEntry(String name) {
    super(name);
  }

  /**
   * Sets the score from the given dice values.
   * The score is the sum of the dice values if there is a tower (a three of a
   * kind and a pair),
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

    boolean hasTower = hasTower(counts);

    if (hasTower) {
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
   * Checks if there is a tower (a three of a kind and a pair) in the given
   * counts.
   *
   * @param counts The counts of the dice values.
   * @return True if there is a tower, false otherwise.
   */
  private boolean hasTower(int[] counts) {
    boolean hasThreeOfAKind = false;
    boolean hasAPair = false;

    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= 3 && !hasThreeOfAKind) {
        hasThreeOfAKind = true;
      } else if (counts[i] >= 2) {
        hasAPair = true;
        break;
      }
    }
    return hasThreeOfAKind && hasAPair;
  }

}
