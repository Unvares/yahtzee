package controller;

import java.util.ArrayList;
import java.util.List;
import model.AppData;
import model.GameData;
import model.Player;
import utils.ControllerName;
import utils.InputHandler;
import view.CreateGameView;

/**
 * Controller for creating a new game.
 */
public class CreateGameController extends Controller {
  private GameData gameData = AppData.getInstance().getGameData();

  /**
   * Constructor for CreateGameController.
   */
  public CreateGameController() {
    super(new CreateGameView());
  }

  /**
   * Gets the new controller based on user input.
   *
   * @param inputHandler The input handler for user input.
   *
   * @return The new controller.
   */
  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    List<String> optionsList = getOptionsList();
    String prompt = "Your choice: ";
    int choice = inputHandler.getIntInput(optionsList, prompt);

    switch (choice) {
      case 1:
        if (!hasTooManyPlayers()) {
          String name = inputHandler.getStringInput("Enter name: ");
          boolean isHuman = inputHandler.getBooleanInput("Is human? (y/n): ");

          gameData.addPlayer(new Player(name, isHuman, gameData.getVariation()));
        }
        break;
      case 2:
        if (hasPlayers()) {
          int index = inputHandler.getIntInput("Enter player number: ");

          while (index < 1 || index > gameData.getPlayers().size()) {
            System.out.println("Invalid player number. Please try again.");
            index = inputHandler.getIntInput("Enter player number: ");
          }

          gameData.removePlayer(index - 1);
        }
        break;
      case 3:
        if (hasEnoughPlayers()) {
          return ControllerName.GAME;
        }
        break;
      case 4:
        return ControllerName.MAIN_MENU;
      default:
        return ControllerName.INVALID;
    }
    return ControllerName.CREATE_GAME;
  }

  /**
   * Gets the list of options for the user.
   *
   * @return The list of options.
   */
  private List<String> getOptionsList() {
    List<String> optionsList = new ArrayList<>();

    if (!hasTooManyPlayers()) {
      optionsList.add("1. Create new character");
    }
    if (hasPlayers()) {
      optionsList.add("2. Delete existing character");
    }
    if (hasEnoughPlayers()) {
      optionsList.add("3. Start game");
    }
    optionsList.add("4. Return to main menu");

    return optionsList;
  }

  /**
   * Checks if there are any players.
   *
   * @return True if there are players, false otherwise.
   */
  private boolean hasPlayers() {
    return gameData.getPlayers().size() > 0;
  }

  /**
   * Checks if there are enough players.
   *
   * @return True if there are enough players, false otherwise.
   */
  private boolean hasEnoughPlayers() {
    return gameData.getPlayers().size() >= gameData.getMinPlayers();
  }

  /**
   * Checks if there are too many players.
   *
   * @return True if there are too many players, false otherwise.
   */
  private boolean hasTooManyPlayers() {
    return gameData.getPlayers().size() > gameData.getMaxPlayers();
  }
}
