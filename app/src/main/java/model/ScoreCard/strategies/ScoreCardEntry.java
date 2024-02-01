package model.ScoreCard.strategies;

import java.util.List;

public abstract class ScoreCardEntry implements ScoreCardEntryInterface {
  private String name;
  private boolean isCompleted = false;
  private int score = 0;

  public ScoreCardEntry(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isCompleted() {
    return isCompleted;
  }

  @Override
  public int getScore() {
    return score;
  }

  @Override
  public void setScore(int score) {
    this.isCompleted = true;
    this.score = score;
  }

  @Override
  public abstract void setScoreFromDices(List<Integer> diceValues);
}
