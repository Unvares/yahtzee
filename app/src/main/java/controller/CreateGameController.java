package controller;

import view.CreateGameView;

import java.util.ArrayList;
import java.util.List;

import model.GameData;
import model.Player;
import utils.InputHandler;
import utils.ControllerName;

public class CreateGameController extends Controller {
  GameData gameData;

  public CreateGameController(GameData gameData) {
    super(new CreateGameView(gameData));
    this.gameData = gameData;
  }

  @Override
  protected ControllerName getNewState(InputHandler inputHandler) {
    List<String> optionsList = getOptionsList();
    String prompt = "Your choice: ";
    int choice = inputHandler.getIntInput(optionsList, prompt);

    switch (choice) {
      case 1:
        if (!hasTooManyPlayers()) {
          String name = inputHandler.getStringInput("Enter name: ");
          boolean isHuman = inputHandler.getBooleanInput("Is human? (y/n): ");

          gameData.addPlayer(new Player(name, isHuman));
        }

        return ControllerName.GAME_CREATE;
      case 2:
        if (hasPlayers()) {
          int index = inputHandler.getIntInput("Enter player number: ");

          while (index < 1 || index > gameData.getPlayers().size()) {
            System.out.println("Invalid player number. Please try again.");
            index = inputHandler.getIntInput("Enter player number: ");
          }

          gameData.removePlayer(index - 1);
        }

        return ControllerName.GAME_CREATE;
      case 3:
        if (hasEnoughPlayers()) {
          return ControllerName.GAME_PLAY;
        }
        return ControllerName.GAME_CREATE;
      case 4:
        return ControllerName.MENU;
      default:
        return ControllerName.INVALID;
    }
  }

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

  private boolean hasPlayers() {
    return gameData.getPlayers().size() > 0;
  }

  private boolean hasEnoughPlayers() {
    return gameData.getPlayers().size() >= gameData.getMinPlayers();
  }

  private boolean hasTooManyPlayers() {
    return gameData.getPlayers().size() > gameData.getMaxPlayers();
  }

}
