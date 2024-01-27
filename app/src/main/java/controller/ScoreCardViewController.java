package controller;

import model.GameData;
import utils.InputHandler;
import utils.State;
import view.ScoreCardView;

public class ScoreCardViewController extends Controller {
  public ScoreCardViewController(GameData gameData) {
    super(new ScoreCardView(gameData), State.GAME_SCORE_VIEW);
  }

  @Override
  protected State getNewState(InputHandler inputHandler) {
    inputHandler.getAnyInput("Press enter to return to the game");
    return State.GAME_PLAY;
  }
}
