package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScoreCard {
  private LinkedHashMap<String, ScoreCardEntry> upperSectionMap = new LinkedHashMap<>();
  private LinkedHashMap<String, ScoreCardEntry> lowerSectionMap = new LinkedHashMap<>();

  public ScoreCard() {
    upperSectionMap.put("ones", new SumOfValuesEntry("ones", 1));
    upperSectionMap.put("twos", new SumOfValuesEntry("twos", 2));
    upperSectionMap.put("threes", new SumOfValuesEntry("threes", 3));
    upperSectionMap.put("fours", new SumOfValuesEntry("fours", 4));
    upperSectionMap.put("fives", new SumOfValuesEntry("fives", 5));
    upperSectionMap.put("sixes", new SumOfValuesEntry("sixes", 6));

    lowerSectionMap.put("three of a kind", new NOfAKindEntry("three of a kind", 3));
    lowerSectionMap.put("four of a kind", new NOfAKindEntry("four of a kind", 4));
    lowerSectionMap.put("full house", new FullHouseEntry("full house"));
    lowerSectionMap.put("small straight", new StraightEntry("small straight", true));
    lowerSectionMap.put("large straight", new StraightEntry("large straight", false));
    lowerSectionMap.put("yahtzee", new YahtzeeEntry("yahtzee"));
    lowerSectionMap.put("chance", new ChanceEntry());
  }

  public LinkedHashMap<String, ScoreCardEntry> getUpperSection() {
    return upperSectionMap;
  }

  public LinkedHashMap<String, ScoreCardEntry> getLowerSection() {
    return lowerSectionMap;
  }

  public ScoreCardEntry getScoreCardEntry(String name) {
    return getMergedMap().get(name);
  }

  public int getTotalScoreFromSection(boolean isUpperSection) {
    LinkedHashMap<String, ScoreCardEntry> sectionMap = isUpperSection ? upperSectionMap : lowerSectionMap;

    int totalScore = 0;
    for (Map.Entry<String, ScoreCardEntry> entry : sectionMap.entrySet()) {
      ScoreCardEntry scoreCardEntry = entry.getValue();
      if (scoreCardEntry.isCompleted()) {
        totalScore += scoreCardEntry.getScore();
      }
    }

    return totalScore;
  }

  public int getBonus() {
    int upperSectionTotalScore = getTotalScoreFromSection(true);
    return upperSectionTotalScore >= 63 ? 35 : 0;
  }

  public int getTotalScore() {
    return getTotalScoreFromSection(true) + getTotalScoreFromSection(false);
  }

  public boolean isCompleted() {
    LinkedHashMap<String, ScoreCardEntry> mergedMap = getMergedMap();

    for (Map.Entry<String, ScoreCardEntry> entry : mergedMap.entrySet()) {
      ScoreCardEntry scoreCardEntry = entry.getValue();
      if (!scoreCardEntry.isCompleted()) {
        return false;
      }
    }
    return true;
  }

  private LinkedHashMap<String, ScoreCardEntry> getMergedMap() {
    LinkedHashMap<String, ScoreCardEntry> mergedMap = new LinkedHashMap<>();
    mergedMap.putAll(upperSectionMap);
    mergedMap.putAll(lowerSectionMap);
    return mergedMap;
  }

}
