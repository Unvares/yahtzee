package controller;

import model.GameData;
import model.ScoreCardEntry;
import utils.InputHandler;
import utils.ControllerName;
import view.ScoreCardView;

public class ScoreCardRegisterController extends Controller {
  private GameData gameData;

  public ScoreCardRegisterController(GameData gameData) {
    super(new ScoreCardView(gameData));
    this.gameData = gameData;
  }

  @Override
  protected ControllerName getNewState(InputHandler inputHandler) {
    String choice = inputHandler.getStringInput("Enter name of the entry to fill: ").toLowerCase();
    try {
      ScoreCardEntry entry = gameData.getCurrentPlayer().getScoreCard().getScoreCardEntry(choice);
      if (entry.isCompleted() && !choice.equals("yahtzee")) {
        return ControllerName.INVALID;
      }
      entry.setScoreFromDices(gameData.getCurrentDiceValues());
      boolean gameHasEnded = gameData.nextTurn();
      if (gameHasEnded) {
        return ControllerName.GAME_OVER;
      }
    } catch (Exception e) {
      return ControllerName.INVALID;
    }
    return ControllerName.GAME_PLAY;
  }

}
