package controller;

import model.GameData;
import utils.InputHandler;
import utils.State;
import view.ScoreBoardView;

public class ScoreBoardController extends Controller {
  public ScoreBoardController(GameData gameData) {
    super(new ScoreBoardView(gameData), State.SCORE_BOARD);
  }

  @Override
  protected State getNewState(InputHandler inputHandler) {
    inputHandler.getAnyInput("Press any button to return to the main menu: ");
    return State.MENU;
  }
}
