package view;

import model.AppData;
import model.GameData;
import model.ScoreCard.ScoreCard;

public class ScoreCardView extends View {
  private GameData gameData = AppData.getInstance().getGameData();

  public ScoreCardView() {
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
