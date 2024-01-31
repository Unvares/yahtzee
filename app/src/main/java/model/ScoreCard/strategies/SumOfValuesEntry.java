package model.ScoreCard.strategies;

import java.util.List;

import model.ScoreCard.ScoreCardEntry;

public class SumOfValuesEntry extends ScoreCardEntry {
  private int value;

  public SumOfValuesEntry(String name, int value) {
    super(name);
    this.value = value;
  }

  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int sum = 0;
    for (int diceValue : diceValues) {
      if (diceValue == value) {
        sum += diceValue;
      }
    }
    setScore(sum);
  }
}
