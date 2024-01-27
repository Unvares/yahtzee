package controller;

import model.GameData;
import model.ScoreCardEntry;
import utils.InputHandler;
import utils.State;
import view.ScoreCardView;

public class ScoreCardRegisterController extends Controller {
  private GameData gameData;

  public ScoreCardRegisterController(GameData gameData) {
    super(new ScoreCardView(gameData), State.GAME_SCORE_REGISTER);
    this.gameData = gameData;
  }

  @Override
  protected State getNewState(InputHandler inputHandler) {
    String choice = inputHandler.getStringInput("Enter name of the entry to fill: ").toLowerCase();
    try {
      ScoreCardEntry entry = gameData.getCurrentPlayer().getScoreCard().getScoreCardEntry(choice);
      if (entry.isCompleted()) {
        return State.INVALID;
      }
      entry.setScoreFromDices(gameData.getCurrentDiceValues());
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
