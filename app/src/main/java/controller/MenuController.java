package controller;

import model.GameData;
import utils.InputHandler;
import utils.State;
import view.MenuView;

public class MenuController extends Controller {
  private GameData gameData;

  public MenuController(GameData gameData) {
    super(new MenuView(gameData), State.MENU);
    this.gameData = gameData;
  }

  @Override
  protected State getNewState(InputHandler inputHandler) {
    int choice = inputHandler.getIntInput("Your choice: ");
    switch (choice) {
      case 1:
        gameData.reset();
        return State.GAME_CREATE;
      case 2:
        if (gameData.hasSavedGame()) {
          return State.GAME_PLAY;
        }
        return State.MENU;
      case 3:
        return State.SCORE_BOARD;
      case 4:
        return State.EXIT;
      default:
        return State.INVALID;
    }
  }

}
