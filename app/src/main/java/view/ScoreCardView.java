package view;

import java.util.Map;

import model.GameData;
import model.ScoreCard;
import model.ScoreCardEntry;

public class ScoreCardView implements View {
  private GameData gameData;

  public ScoreCardView(GameData gameData) {
    this.gameData = gameData;
  }

  public void display() {
    System.out.println("====================================");
    System.out.println();

    System.out.println("Score Card");
    System.out.println("Current Player: " + gameData.getCurrentPlayer().getName());
    System.out.println("Current Dices: " + gameData.getCurrentDiceValues());
    System.out.println();

    ScoreCard scoreCard = gameData.getCurrentPlayer().getScoreCard();
    Map<String, ScoreCardEntry> entries = scoreCard.getEntries();
    int currentScore = 0;

    for (Map.Entry<String, ScoreCardEntry> entry : entries.entrySet()) {
      ScoreCardEntry scoreCardEntry = entry.getValue();
      if (!scoreCardEntry.isCompleted()) {
        System.out.println("- " + scoreCardEntry.getName());
      } else {
        currentScore += scoreCardEntry.getScore();
      }
    }

    System.out.println();
    System.out.println("Current Score: " + currentScore);

    System.out.println("====================================");
  }

}
