package model.ScoreCard.strategies;

import java.util.List;

import model.ScoreCard.ScoreCardEntry;

public class StraightEntry extends ScoreCardEntry {
  private boolean isSmall;

  public StraightEntry(String name, boolean isSmall) {
    super(name);
    this.isSmall = isSmall;
  }

  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }
    int count = 0;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0) {
        count++;
      }
    }
    if (isSmall && count == 4) {
      setScore(30);
    } else if (!isSmall && count == 5) {
      setScore(40);
    } else {
      setScore(0);
    }
  }
}
