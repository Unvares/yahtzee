package controller;

import model.GameData;
import utils.InputHandler;
import utils.ControllerName;
import view.ScoreBoardView;

public class ScoreBoardController extends Controller {
  public ScoreBoardController(GameData gameData) {
    super(new ScoreBoardView(gameData));
  }

  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    inputHandler.getAnyInput("Press enter to return to the main menu: ");
    return ControllerName.MENU;
  }
}
