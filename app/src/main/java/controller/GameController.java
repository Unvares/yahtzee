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

public class GameController extends Controller {
  private GameData gameData;

  public GameController(AppData appData) {
    super(new GameView(appData.getGameData()));
    this.gameData = appData.getGameData();
  }

  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    if (gameData.hasGameEnded()) {
      return ControllerName.GAME_OVER;
    }
    return play(inputHandler);
  }

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

  private String getPrompt() {
    final int MAX_ROLLS = gameData.getMaxRolls();
    int rollCounter = gameData.getRollCounter();
    return "Your choice (rolls left " + (MAX_ROLLS - rollCounter) + "):";
  }

  private int rollDice() {
    return (int) (Math.random() * 6) + 1;
  }

  private void updateDiceValues() {
    int diceCount = gameData.getNumberOfDicesToRoll();

    for (int i = 0; i < diceCount; i++) {
      int diceValue = rollDice();
      gameData.addDiceValue(diceValue);
    }
  }

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
