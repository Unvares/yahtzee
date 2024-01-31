package model.ScoreCard;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import model.ScoreCard.strategies.ChanceEntry;
import model.ScoreCard.strategies.FullHouseEntry;
import model.ScoreCard.strategies.NOfAKindEntry;
import model.ScoreCard.strategies.StraightEntry;
import model.ScoreCard.strategies.SumOfValuesEntry;
import model.ScoreCard.strategies.YahtzeeEntry;

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

  public String toCSV() {
    StringBuilder csvBuilder = new StringBuilder();
    LinkedHashMap<String, ScoreCardEntry> mergedMap = getMergedMap();
    for (Map.Entry<String, ScoreCardEntry> entry : mergedMap.entrySet()) {
      csvBuilder.append(entry.getValue().getScore()).append(",");
    }
    csvBuilder.setLength(csvBuilder.length() - 1);
    return csvBuilder.toString();
  }

  public static ScoreCard fromCSV(String csv) {
    String[] parts = csv.split(",");
    ScoreCard scoreCard = new ScoreCard();

    Iterator<Map.Entry<String, ScoreCardEntry>> iterator = scoreCard.getMergedMap().entrySet().iterator();
    for (int i = 0; i < parts.length; i++) {
      int score = Integer.parseInt(parts[i]);
      Map.Entry<String, ScoreCardEntry> entry = iterator.next();
      ScoreCardEntry scoreCardEntry = entry.getValue();
      scoreCardEntry.setScore(score);
    }

    return scoreCard;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    appendUpperSection(stringBuilder);
    appendLowerSection(stringBuilder);
    appendGrandTotal(stringBuilder);

    return stringBuilder.toString();
  }

  private void appendUpperSection(StringBuilder stringBuilder) {
    int upperSectionTotal = getTotalScoreFromSection(true);
    int bonus = getBonus();
    int upperSectionTotalWithBonus = upperSectionTotal + bonus;

    stringBuilder.append("Score Card\n");
    stringBuilder.append("Upper Section\n");
    stringBuilder.append(appendSection(getUpperSection()));
    stringBuilder.append("Total Score: " + upperSectionTotal + "\n");
    stringBuilder.append("Bonus: " + bonus + "\n");
    stringBuilder.append("Total: " + upperSectionTotalWithBonus + "\n");
    stringBuilder.append("\n");
  }

  private void appendLowerSection(StringBuilder stringBuilder) {
    int lowerSectionTotal = getTotalScoreFromSection(false);

    stringBuilder.append("Lower Section\n");
    stringBuilder.append(appendSection(getLowerSection()));
    stringBuilder.append("Total Score: " + lowerSectionTotal + "\n");
    stringBuilder.append("\n");
  }

  private String appendSection(LinkedHashMap<String, ScoreCardEntry> entryMap) {
    StringBuilder sectionStringBuilder = new StringBuilder();
    entryMap.forEach((key, scoreCardEntry) -> {
      String score = scoreCardEntry.isCompleted() ? ": " + scoreCardEntry.getScore() : "";
      sectionStringBuilder.append("- " + scoreCardEntry.getName() + score + "\n");
    });
    return sectionStringBuilder.toString();
  }

  private void appendGrandTotal(StringBuilder stringBuilder) {
    int upperSectionTotalWithBonus = getTotalScoreFromSection(true) + getBonus();
    int lowerSectionTotal = getTotalScoreFromSection(false);
    int grandTotal = upperSectionTotalWithBonus + lowerSectionTotal;

    stringBuilder.append("Upper Section Total: " + upperSectionTotalWithBonus + "\n");
    stringBuilder.append("Lower Section Total: " + lowerSectionTotal + "\n");
    stringBuilder.append("Grand Total: " + grandTotal + "\n");
    stringBuilder.append("\n");
  }

}
