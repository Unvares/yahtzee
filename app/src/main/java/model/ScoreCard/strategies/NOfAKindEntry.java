package model.ScoreCard.strategies;

import java.util.List;

public class NOfAKindEntry extends ScoreCardEntry {
  private int n;

  public NOfAKindEntry(String name, int n) {
    super(name);
    this.n = n;
  }

  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }

    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= n) {
        setScore((i + 1) * n);
        return;
      }
    }

    setScore(0);
  }

}
