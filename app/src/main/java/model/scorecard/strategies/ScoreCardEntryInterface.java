package model.scorecard.strategies;

import java.util.List;

/**
 * This interface represents a score card entry in the game of Yahtzee.
 */
public interface ScoreCardEntryInterface {

  /**
   * Returns the name of the score card entry.
   *
   * @return The name of the score card entry.
   */
  public String getName();

  /**
   * Returns whether the score card entry is completed.
   *
   * @return True if the score card entry is completed, false otherwise.
   */
  public boolean isCompleted();

  /**
   * Returns the score of the score card entry.
   *
   * @return The score of the score card entry.
   */
  public int getScore();

  /**
   * Sets the score of the score card entry.
   *
   * @param score The score to be set.
   */
  public void setScore(int score);

  /**
   * Sets the score from the given dice values.
   *
   * @param diceValues The values of the dices.
   */
  public void setScoreFromDices(List<Integer> diceValues);

}
