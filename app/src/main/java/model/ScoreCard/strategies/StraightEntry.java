package model.ScoreCard.strategies;

import java.util.List;

public class StraightEntry extends ScoreCardEntry {
  public StraightEntry(String name) {
    super(name);
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
    if (getName() == "small straight" && count == 4) {
      setScore(30);
    } else if (getName() == "large straight" && count == 5) {
      setScore(40);
    } else if (getName() == "full straight" && count == 6) {
      setScore(50);
    } else {
      setScore(0);
    }
  }
}
