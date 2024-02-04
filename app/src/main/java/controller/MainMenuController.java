package controller;

import java.util.ArrayList;
import java.util.List;

import model.AppData;
import model.GameData;
import utils.InputHandler;
import utils.ControllerName;
import view.MenuView;

public class MainMenuController extends Controller {
  private GameData gameData = AppData.getInstance().getGameData();

  public MainMenuController() {
    super(new MenuView());
  }

  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    List<String> optionsList = getOptionsList();
    String prompt = "Your choice: ";
    int choice = inputHandler.getIntInput(optionsList, prompt);

    switch (choice) {
      case 1:
        gameData.reset();
        return ControllerName.CREATE_GAME;
      case 2:
        if (gameData.hasSavedGame()) {
          return ControllerName.GAME;
        }
        return ControllerName.MAIN_MENU;
      case 3:
        return ControllerName.SCOREBOARD;
      case 4:
        return ControllerName.EXIT;
      default:
        return ControllerName.INVALID;
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
