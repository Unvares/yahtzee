package model;

import java.util.List;

public class FullHouseEntry extends ScoreCardEntry {
  public FullHouseEntry(String name) {
    super(name);
  }

  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }

    boolean hasTwoOfAKind = hasNOfAKind(counts, 2);
    boolean hasThreeOfAKind = hasNOfAKind(counts, 3);

    if (hasTwoOfAKind && hasThreeOfAKind) {
      setScore(25);
    } else {
      setScore(0);
    }
  }

  private boolean hasNOfAKind(int[] counts, int n) {
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= n) {
        return true;
      }
    }
    return false;
  }

}
