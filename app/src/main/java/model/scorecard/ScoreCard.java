package model.scorecard;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import model.scorecard.strategies.ChanceEntry;
import model.scorecard.strategies.FullHouseEntry;
import model.scorecard.strategies.NofaKindEntry;
import model.scorecard.strategies.ScoreCardEntry;
import model.scorecard.strategies.StraightEntry;
import model.scorecard.strategies.SumOfValuesEntry;
import model.scorecard.strategies.VillaEntry;
import model.scorecard.strategies.YahtzeeEntry;
import utils.Variation;

/**
 * This class represents the score card for a Yahtzee game.
 */
public class ScoreCard implements Serializable {
  private int bonusThreshold = 63;
  private int bonusPoints = 35;
  private LinkedHashMap<String, ScoreCardEntry> upperSectionMap = new LinkedHashMap<>();
  private LinkedHashMap<String, ScoreCardEntry> lowerSectionMap = new LinkedHashMap<>();

  /**
   * Constructor for ScoreCard.
   * Initializes the score card with the given game variation.
   *
   * @param variation The game variation.
   */
  public ScoreCard(Variation variation) {
    if (variation == Variation.MAXI) {
      bonusThreshold = 75;
      bonusPoints = 50;
    }

    upperSectionMap.put("ones", new SumOfValuesEntry("ones", 1));
    upperSectionMap.put("twos", new SumOfValuesEntry("twos", 2));
    upperSectionMap.put("threes", new SumOfValuesEntry("threes", 3));
    upperSectionMap.put("fours", new SumOfValuesEntry("fours", 4));
    upperSectionMap.put("fives", new SumOfValuesEntry("fives", 5));
    upperSectionMap.put("sixes", new SumOfValuesEntry("sixes", 6));

    lowerSectionMap.put("three of a kind", new NofaKindEntry("three of a kind", 3));
    lowerSectionMap.put("four of a kind", new NofaKindEntry("four of a kind", 4));
    if (variation == Variation.MAXI) {
      lowerSectionMap.put("five of a kind", new NofaKindEntry("five of a kind", 5));
    }
    lowerSectionMap.put("full house", new FullHouseEntry("full house"));
    if (variation == Variation.MAXI) {
      lowerSectionMap.put("villa", new VillaEntry("villa"));
      lowerSectionMap.put("tower", new VillaEntry("tower"));
    }
    lowerSectionMap.put("small straight", new StraightEntry("small straight"));
    lowerSectionMap.put("large straight", new StraightEntry("large straight"));
    if (variation == Variation.MAXI) {
      lowerSectionMap.put("full straight", new StraightEntry("full straight"));
    }
    lowerSectionMap.put("yahtzee", new YahtzeeEntry("yahtzee"));
    lowerSectionMap.put("chance", new ChanceEntry());
  }

  /**
   * Returns the score card entry with the given name.
   *
   * @param name The name of the score card entry.
   * @return The score card entry with the given name.
   */
  public ScoreCardEntry getScoreCardEntry(String name) {
    return getMergedMap().get(name);
  }

  /**
   * Returns the total score from the given section.
   *
   * @param isUpperSection Whether the section is the upper section.
   * @return The total score from the given section.
   */
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

  /**
   * Returns the bonus score.
   *
   * @return The bonus score.
   */
  public int getBonus() {
    int upperSectionTotalScore = getTotalScoreFromSection(true);
    return upperSectionTotalScore >= bonusThreshold ? bonusPoints : 0;
  }

  /**
   * Returns the total score.
   *
   * @return The total score.
   */
  public int getTotalScore() {
    return getTotalScoreFromSection(true) + getTotalScoreFromSection(false);
  }

  /**
   * Returns whether the score card is completed.
   *
   * @return Whether the score card is completed.
   */
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

  /**
   * Returns a merged map of the upper and lower sections.
   *
   * @return A merged map of the upper and lower sections.
   */
  private LinkedHashMap<String, ScoreCardEntry> getMergedMap() {
    LinkedHashMap<String, ScoreCardEntry> mergedMap = new LinkedHashMap<>();
    mergedMap.putAll(upperSectionMap);
    mergedMap.putAll(lowerSectionMap);
    return mergedMap;
  }

  /**
   * Converts the score card to a CSV string.
   *
   * @return The CSV string representation of the score card.
   */
  public String toCsv() {
    StringBuilder csvBuilder = new StringBuilder();
    LinkedHashMap<String, ScoreCardEntry> mergedMap = getMergedMap();
    for (Map.Entry<String, ScoreCardEntry> entry : mergedMap.entrySet()) {
      csvBuilder.append(entry.getValue().getScore()).append(",");
    }
    csvBuilder.setLength(csvBuilder.length() - 1);
    return csvBuilder.toString();
  }

  /**
   * Returns a string representation of the score card.
   *
   * @return A string representation of the score card.
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    appendUpperSection(stringBuilder);
    appendLowerSection(stringBuilder);
    appendGrandTotal(stringBuilder);

    return stringBuilder.toString();
  }

  /**
   * Appends the upper section to the given string builder.
   *
   * @param stringBuilder The string builder to append to.
   */
  private void appendUpperSection(StringBuilder stringBuilder) {
    int upperSectionTotal = getTotalScoreFromSection(true);
    int bonus = getBonus();
    int upperSectionTotalWithBonus = upperSectionTotal + bonus;

    stringBuilder.append("Score Card\n");
    stringBuilder.append("Upper Section\n");
    stringBuilder.append(appendSection(upperSectionMap));
    stringBuilder.append("Total Score: " + upperSectionTotal + "\n");
    stringBuilder.append("Bonus: " + bonus + "\n");
    stringBuilder.append("Total: " + upperSectionTotalWithBonus + "\n");
    stringBuilder.append("\n");
  }

  /**
   * Appends the lower section to the given string builder.
   *
   * @param stringBuilder The string builder to append to.
   */
  private void appendLowerSection(StringBuilder stringBuilder) {
    int lowerSectionTotal = getTotalScoreFromSection(false);

    stringBuilder.append("Lower Section\n");
    stringBuilder.append(appendSection(lowerSectionMap));
    stringBuilder.append("Total Score: " + lowerSectionTotal + "\n");
    stringBuilder.append("\n");
  }

  /**
   * Appends the given section to a string.
   *
   * @param entryMap The section to append.
   * @return The string representation of the section.
   */
  private String appendSection(LinkedHashMap<String, ScoreCardEntry> entryMap) {
    StringBuilder sectionStringBuilder = new StringBuilder();
    entryMap.forEach((key, scoreCardEntry) -> {
      String score = scoreCardEntry.isCompleted() ? ": " + scoreCardEntry.getScore() : "";
      sectionStringBuilder.append("- " + scoreCardEntry.getName() + score + "\n");
    });
    return sectionStringBuilder.toString();
  }

  /**
   * Appends the grand total to the given string builder.
   *
   * @param stringBuilder The string builder to append to.
   */
  private void appendGrandTotal(StringBuilder stringBuilder) {
    int upperSectionTotalWithBonus = getTotalScoreFromSection(true) + getBonus();
    int lowerSectionTotal = getTotalScoreFromSection(false);
    int grandTotal = upperSectionTotalWithBonus + lowerSectionTotal;

    stringBuilder.append("Upper Section Total: " + upperSectionTotalWithBonus + "\n");
    stringBuilder.append("Lower Section Total: " + lowerSectionTotal + "\n");
    stringBuilder.append("Grand Total: " + grandTotal + "\n");
  }

  /**
   * Creates a ScoreCard from a CSV string.
   *
   * @param csv       The CSV string to be converted.
   * @param variation The variation of the game.
   * @return The ScoreCard created from the CSV string.
   */
  public static ScoreCard fromCsv(String csv, Variation variation) {
    String[] parts = csv.split(",");
    ScoreCard scoreCard = new ScoreCard(variation);

    Iterator<Map.Entry<String, ScoreCardEntry>> iterator = scoreCard.getMergedMap().entrySet().iterator();
    for (int i = 0; i < parts.length; i++) {
      int score = Integer.parseInt(parts[i]);
      Map.Entry<String, ScoreCardEntry> entry = iterator.next();
      ScoreCardEntry scoreCardEntry = entry.getValue();
      scoreCardEntry.setScore(score);
    }

    return scoreCard;
  }

}
