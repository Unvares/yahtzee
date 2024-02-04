package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.AppData;
import model.GameData;
import utils.InputHandler;
import utils.ControllerName;
import utils.ControllerRegistry;
import view.GameView;

/**
 * Controller for the game logic.
 */
public class GameController extends Controller {
  private GameData gameData = AppData.getInstance().getGameData();

  /**
   * Constructor for GameController.
   */
  public GameController() {
    super(new GameView());
  }

  /**
   * Gets the new controller based on user input.
   * 
   * @param inputHandler The input handler for user input.
   * @return The new controller.
   */
  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    if (gameData.hasGameEnded()) {
      return ControllerName.GAME_OVER;
    }
    return play(inputHandler);
  }

  /**
   * Handles the game logic based on user input.
   * 
   * @param inputHandler The input handler for user input.
   * @return The new controller.
   */
  private ControllerName play(InputHandler inputHandler) {
    List<String> optionsList = getOptionsList();
    String prompt = getPrompt();
    int choice = inputHandler.getIntInput(optionsList, prompt);

    switch (choice) {
      case 1:
        if (gameData.canRoll()) {
          gameData.advanceRollCounter();
          updateDiceValues();
        }
        break;
      case 2:
        if (gameData.canDeleteDices()) {
          Set<Integer> indicesToRemove = getIndicesToRemove(inputHandler);
          gameData.removeDiceValuesByIndices(indicesToRemove);
        }
        break;
      case 3:
        ControllerRegistry.getController(ControllerName.SCORECARD).setState("view");
        return ControllerName.SCORECARD;
      case 4:
        if (gameData.canEndTurn()) {
          gameData.nextTurn();
          ControllerRegistry.getController(ControllerName.SCORECARD).setState("register");
          return ControllerName.SCORECARD;
        }
        break;
      case 5:
        return ControllerName.MAIN_MENU;
      default:
        return ControllerName.INVALID;
    }
    return ControllerName.GAME;
  }

  /**
   * Gets the list of options for the user.
   * 
   * @return The list of options.
   */
  private List<String> getOptionsList() {
    List<String> optionsList = new ArrayList<>();

    if (gameData.canRoll()) {
      optionsList.add("1. Roll dice");
    }
    if (gameData.canDeleteDices()) {
      optionsList.add("2. Choose dices to discard");
    }
    optionsList.add("3. View score card");
    if (gameData.canEndTurn()) {
      optionsList.add("4. Score!");
    }
    optionsList.add("5. Save & Quit");

    return optionsList;
  }

  /**
   * Gets the prompt for the user.
   * 
   * @return The prompt.
   */
  private String getPrompt() {
    final int MAX_ROLLS = gameData.getMaxRolls();
    int rollCounter = gameData.getRollCounter();
    return "Your choice (rolls left " + (MAX_ROLLS - rollCounter) + "):";
  }

  /**
   * Rolls a dice.
   * 
   * @return The result of the dice roll.
   */
  private int rollDice() {
    return (int) (Math.random() * 6) + 1;
  }

  /**
   * Updates the dice values.
   */
  private void updateDiceValues() {
    int diceCount = gameData.getNumberOfDicesToRoll();

    for (int i = 0; i < diceCount; i++) {
      int diceValue = rollDice();
      gameData.addDiceValue(diceValue);
    }
  }

  /**
   * Gets the indices of the dices to remove.
   * 
   * @param inputHandler The input handler for user input.
   * @return The indices of the dices to remove.
   */
  private Set<Integer> getIndicesToRemove(InputHandler inputHandler) {
    String prompt = "Enter indices of dices to discard (separated by comma): ";
    String[] diceIndexArray = inputHandler.getStringInput(prompt).split(",");

    Set<Integer> indicesToRemove = new HashSet<>();
    for (String indexString : diceIndexArray) {
      try {
        int index = Integer.parseInt(indexString.trim()) - 1;
        if (index < gameData.getMaxDices() && index >= 0) {
          indicesToRemove.add(index);
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input.");
        continue;
      }
    }

    return indicesToRemove;
  }

}
