package model.ScoreCard.strategies;

import java.util.List;

public class VillaEntry extends ScoreCardEntry {

  public VillaEntry(String name) {
    super(name);
  }

  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }

    boolean hasVilla = hasVilla(counts);

    if (hasVilla) {
      setScore(25);
    } else {
      setScore(0);
    }
  }

  private boolean hasVilla(int[] counts) {
    int countThreeOfAKind = 0;

    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= 3) {
        countThreeOfAKind++;
      }
    }
    return countThreeOfAKind == 2;
  }

}
