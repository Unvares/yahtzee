package view;

import model.AppData;
import model.GameData;
import model.scorecard.ScoreCard;

/**
 * This class represents the view for the Score Card.
 */
public class ScoreCardView extends View {
  /**
   * The game data.
   */
  private GameData gameData = AppData.getInstance().getGameData();

  /**
   * Constructor for ScoreCardView.
   */
  public ScoreCardView() {
  }

  /**
   * This method displays the Score Card view.
   */
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
