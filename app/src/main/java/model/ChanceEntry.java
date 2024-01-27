package model;

import java.util.List;

public class ChanceEntry extends ScoreCardEntry {

  public ChanceEntry() {
    super("chance");
  }

  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int sum = 0;
    for (int diceValue : diceValues) {
      sum += diceValue;
    }
    setScore(sum);
  }
}
