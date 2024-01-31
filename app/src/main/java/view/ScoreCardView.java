package view;

import model.GameData;
import model.ScoreCard.ScoreCard;

public class ScoreCardView extends View {
  private GameData gameData;

  public ScoreCardView(GameData gameData) {
    this.gameData = gameData;
  }

  @Override
  public void display() {
    clearScreen();
    System.out.println("Current Player: " + gameData.getCurrentPlayer().getName());
    System.out.println("Current Dices: " + gameData.getCurrentDiceValues());
    System.out.println();

    ScoreCard scoreCard = gameData.getCurrentPlayer().getScoreCard();
    System.out.println(scoreCard.toString());
  }
}
