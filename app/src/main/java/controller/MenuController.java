package controller;

import java.util.ArrayList;
import java.util.List;

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
    List<String> optionsList = getOptionsList();
    String prompt = "Your choice: ";
    int choice = inputHandler.getIntInput(optionsList, prompt);

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

  private List<String> getOptionsList() {
    List<String> optionsList = new ArrayList<>();

    optionsList.add("1. Create New Game");
    if (gameData.hasSavedGame()) {
      optionsList.add("2. Load Game");
    }
    optionsList.add("3. View Score Board");
    optionsList.add("4. Exit");

    return optionsList;
  }

}
