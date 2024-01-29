package controller;

import model.GameData;
import utils.InputHandler;
import utils.ControllerName;
import view.ScoreCardView;

public class ScoreCardViewController extends Controller {
  public ScoreCardViewController(GameData gameData) {
    super(new ScoreCardView(gameData));
  }

  @Override
  protected ControllerName getNewState(InputHandler inputHandler) {
    inputHandler.getAnyInput("Press enter to return to the game");
    return ControllerName.GAME_PLAY;
  }
}
