package view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

    Set<Entry<String, ScoreCardEntry>> entrySet = scoreCard.getEntries().entrySet();
    for (Map.Entry<String, ScoreCardEntry> entry : entrySet) {
      ScoreCardEntry scoreCardEntry = entry.getValue();
      if (!scoreCardEntry.isCompleted()) {
        System.out.println("- " + scoreCardEntry.getName());
      }
    }

    System.out.println();
    System.out.println("Current Score: " + scoreCard.getTotalScore());

    System.out.println("====================================");
  }

}
