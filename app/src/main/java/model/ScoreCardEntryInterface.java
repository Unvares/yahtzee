package model;

import java.util.List;

public interface ScoreCardEntryInterface {

  public String getName();

  public boolean isCompleted();

  public int getScore();

  public void setScore(int score);

  public void setScoreFromDices(List<Integer> diceValues);

}
