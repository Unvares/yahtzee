package controller;

import java.util.ArrayList;
import java.util.List;

import model.AppData;
import model.GameData;
import utils.InputHandler;
import utils.ControllerName;
import view.MenuView;

/**
 * Controller for the main menu of the game.
 */
public class MainMenuController extends Controller {
  private GameData gameData = AppData.getInstance().getGameData();

  /**
   * Constructor for MainMenuController.
   */
  public MainMenuController() {
    super(new MenuView());
  }

  /**
   * Gets the new controller based on user input.
   * 
   * @param inputHandler The input handler for user input.
   * @return The new controller.
   */
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

  /**
   * Gets the list of options for the main menu.
   * 
   * @return The list of options.
   */
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
