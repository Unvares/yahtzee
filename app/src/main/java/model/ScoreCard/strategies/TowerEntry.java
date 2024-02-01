package model.ScoreCard.strategies;

import java.util.List;

public class TowerEntry extends ScoreCardEntry {

  public TowerEntry(String name) {
    super(name);
  }

  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }

    boolean hasTower = hasTower(counts);

    if (hasTower) {
      setScore(25);
    } else {
      setScore(0);
    }
  }

  private boolean hasTower(int[] counts) {
    boolean hasThreeOfAKind = false;
    boolean hasAPair = false;

    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= 3 && !hasThreeOfAKind) {
        hasThreeOfAKind = true;
      } else if (counts[i] >= 2) {
        hasAPair = true;
        break;
      }
    }
    return hasThreeOfAKind && hasAPair;
  }

}
