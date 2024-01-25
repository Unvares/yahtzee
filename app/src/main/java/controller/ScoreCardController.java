package controller;

import model.GameData;
import model.ScoreCardEntry;
import utils.InputHandler;
import utils.State;
import view.ScoreCardView;

public class ScoreCardController extends Controller {
  private GameData gameData;

  public ScoreCardController(GameData gameData) {
    super(new ScoreCardView(gameData), State.GAME_SCORE);
    this.gameData = gameData;
  }

  @Override
  protected State getNewState(InputHandler inputHandler) {
    String choice = inputHandler.getStringInput("Enter name: ").toLowerCase();
    try {
      ScoreCardEntry entry = gameData.getCurrentPlayer().getScoreCard().getScoreCardEntry(choice);
      if (entry.isCompleted()) {
        return State.INVALID;
      }
      entry.setScore(choice, gameData.getCurrentDiceValues());
      boolean gameHasEnded = gameData.nextTurn();
      if (gameHasEnded) {
        return State.GAME_OVER;
      }
    } catch (Exception e) {
      return State.INVALID;
    }
    return State.GAME_PLAY;
  }

}
