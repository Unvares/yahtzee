package model;

import java.util.HashMap;
import java.util.Map;

public class ScoreCard {
  private Map<String, ScoreCardEntry> entryMap = new HashMap<>();

  public ScoreCard() {
    registerScoreCardEntry("ones");
    registerScoreCardEntry("twos");
    registerScoreCardEntry("threes");
    registerScoreCardEntry("fours");
    registerScoreCardEntry("fives");
    registerScoreCardEntry("sixes");
    registerScoreCardEntry("three of a kind");
    registerScoreCardEntry("four of a kind");
    registerScoreCardEntry("full house");
    registerScoreCardEntry("small straight");
    registerScoreCardEntry("large straight");
    registerScoreCardEntry("yahtzee");
    registerScoreCardEntry("chance");
  }

  private void registerScoreCardEntry(String name) {
    entryMap.put(name, new ScoreCardEntry(name));
  }

  public Map<String, ScoreCardEntry> getEntries() {
    return entryMap;
  }

  public ScoreCardEntry getScoreCardEntry(String name) {
    return entryMap.get(name);
  }

  public int getTotalScore() {
    int totalScore = 0;
    for (Map.Entry<String, ScoreCardEntry> entry : entryMap.entrySet()) {
      ScoreCardEntry scoreCardEntry = entry.getValue();
      if (scoreCardEntry.isCompleted()) {
        totalScore += scoreCardEntry.getScore();
      }
    }

    return totalScore;
  }

  public boolean isCompleted() {
    for (Map.Entry<String, ScoreCardEntry> entry : entryMap.entrySet()) {
      ScoreCardEntry scoreCardEntry = entry.getValue();
      if (!scoreCardEntry.isCompleted()) {
        return false;
      }
    }

    return true;
  }

}
