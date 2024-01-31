package model.ScoreCard.strategies;

import java.util.List;

import model.ScoreCard.ScoreCardEntry;

public class YahtzeeEntry extends ScoreCardEntry {
  private int yahtzeeCount = 0;

  public YahtzeeEntry(String name) {
    super(name);
  }

  @Override
  public void setScoreFromDices(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }

    boolean hasYahtzee = false;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] == 5) {
        hasYahtzee = true;
      }
    }

    if (hasYahtzee) {
      yahtzeeCount++;
      int yahtzeeScore = yahtzeeCount == 1 ? 50 : 100;
      setScore(getScore() + yahtzeeScore);
    } else {
      setScore(0);
    }
  }

}
