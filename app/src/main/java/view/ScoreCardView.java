package view;

import java.util.LinkedHashMap;

import model.GameData;
import model.ScoreCard;
import model.ScoreCardEntry;

public class ScoreCardView implements View {
  private GameData gameData;

  public ScoreCardView(GameData gameData) {
    this.gameData = gameData;
  }

  @Override
  public void display() {
    System.out.println("====================================");
    System.out.println();

    System.out.println("Score Card");
    System.out.println();
    System.out.println("Current Player: " + gameData.getCurrentPlayer().getName());
    System.out.println("Current Dices: " + gameData.getCurrentDiceValues());
    System.out.println();

    ScoreCard scoreCard = gameData.getCurrentPlayer().getScoreCard();

    int upperSectionTotal = scoreCard.getTotalScoreFromSection(true);
    int bonus = scoreCard.getBonus();
    int upperSectionTotalWithBonus = upperSectionTotal + bonus;
    System.out.println("Upper Section");
    printSection(scoreCard.getUpperSection());
    System.out.println("Total Score: " + upperSectionTotal);
    System.out.println("Bonus: " + bonus);
    System.out.println("Total: " + upperSectionTotalWithBonus);

    System.out.println();
    int lowerSectionTotal = scoreCard.getTotalScoreFromSection(false);
    System.out.println("Lower Section");
    printSection(scoreCard.getLowerSection());
    System.out.println("Total Score: " + lowerSectionTotal);

    int grandTotal = upperSectionTotalWithBonus + lowerSectionTotal;
    System.out.println();
    System.out.println("Upper Section Total: " + upperSectionTotalWithBonus);
    System.out.println("Lower Section Total: " + lowerSectionTotal);
    System.out.println("Grand Total: " + grandTotal);

    System.out.println("====================================");
  }

  private void printSection(LinkedHashMap<String, ScoreCardEntry> entryMap) {
    entryMap.forEach((key, scoreCardEntry) -> {
      String score = scoreCardEntry.isCompleted() ? ": " + scoreCardEntry.getScore() : "";
      System.out.println("- " + scoreCardEntry.getName() + score);
    });
  }

}
