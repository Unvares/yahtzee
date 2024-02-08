package model.scorecard.strategies;

import java.io.Serializable;
import java.util.List;

/**
 * This abstract class represents a score card entry in the game of Yahtzee.
 * It implements the ScoreCardEntryInterface.
 */
public abstract class ScoreCardEntry implements ScoreCardEntryInterface, Serializable {
  private String name;
  private boolean isCompleted = false;
  private int score = 0;

  /**
   * Constructor for ScoreCardEntry.
   * Initializes the entry with the given name.
   *
   * @param name The name of the entry.
   */
  public ScoreCardEntry(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the score card entry.
   *
   * @return The name of the score card entry.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns whether the score card entry is completed.
   *
   * @return True if the score card entry is completed, false otherwise.
   */
  @Override
  public boolean isCompleted() {
    return isCompleted;
  }

  /**
   * Returns the score of the score card entry.
   *
   * @return The score of the score card entry.
   */
  @Override
  public int getScore() {
    return score;
  }

  /**
   * Sets the score of the score card entry and marks it as completed.
   *
   * @param score The score to be set.
   */
  @Override
  public void setScore(int score) {
    this.isCompleted = true;
    this.score = score;
  }

  /**
   * Abstract method to set the score from the given dice values.
   *
   * @param diceValues The values of the dices.
   */
  @Override
  public abstract void setScoreFromDices(List<Integer> diceValues);
}
