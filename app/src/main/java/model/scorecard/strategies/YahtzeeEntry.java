package model.scorecard.strategies;

import java.util.List;

/**
 * This class represents the Yahtzee entry in the score card.
 */
public class YahtzeeEntry extends ScoreCardEntry {
  private int yahtzeeCount = 0;

  /**
   * Constructor for YahtzeeEntry.
   * Initializes the entry with the given name.
   *
   * @param name The name of the entry.
   */
  public YahtzeeEntry(String name) {
    super(name);
  }

  /**
   * Sets the score from the given dice values.
   * The score is 50 for the first Yahtzee (5 of a kind),
   * 100 for each subsequent Yahtzee,
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

    boolean hasYahtzee = false;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] == 5) {
        hasYahtzee = true;
      }
    }

    if (hasYahtzee) {
      yahtzeeCount++;
      int yahtzeeScore = yahtzeeCount == 1 ? 50 : 100;
      setScore(getScore() + yahtzeeScore);
    } else {
      setScore(0);
    }
  }

}
