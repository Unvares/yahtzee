package controller;

import utils.InputHandler;
import model.AppData;
import utils.ControllerName;
import view.ScoreBoardView;

public class ScoreBoardController extends Controller {
  public ScoreBoardController(AppData appData) {
    super(new ScoreBoardView(appData));
  }

  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    inputHandler.getAnyInput("Press enter to return to the main menu: ");
    return ControllerName.MENU;
  }
}
