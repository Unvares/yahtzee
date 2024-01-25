package controller;

import java.util.HashSet;
import java.util.Set;

import model.GameData;
import utils.InputHandlerImpl;
import utils.State;
import view.GameView;

public class GameController extends Controller {
  private GameData gameData;

  public GameController(GameData gameData) {
    super(new GameView(gameData), State.GAME_PLAY);
    this.gameData = gameData;
  }

  @Override
  protected State getNewState() {
    int MAX_ROLLS = gameData.getMaxRolls();
    int rollCounter = gameData.getRollCounter();

    int choice = InputHandlerImpl.getInstance()
        .getIntInput("Your choice (rolls left " + (MAX_ROLLS - rollCounter) + "):");
    switch (choice) {
      case 1:
        if (gameData.canRoll()) {
          gameData.advanceRollCounter();

          int diceCount = gameData.getDiceCount();

          for (int i = 0; i < diceCount; i++) {
            int diceValue = rollDice();
            gameData.addDiceValue(diceValue);
          }
        }

        return State.GAME_PLAY;
      case 2:
        if (gameData.canDeleteDices()) {
          String prompt = "Enter which dices to discard (separated by comma): ";

          Set<Integer> indicesToRemove = new HashSet<>();

          String[] diceIndexArray = InputHandlerImpl.getInstance().getStringInput(prompt).split(",");
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

          gameData.removeDiceValues(indicesToRemove);
        }

        return State.GAME_PLAY;
      case 3:
        if (gameData.canEndTurn()) {
          return State.GAME_SCORE;
        }

        return State.GAME_PLAY;
      case 4:
        return State.MENU;
      default:
        return State.INVALID;
    }
  }

  private int rollDice() {
    return (int) (Math.random() * 6) + 1;
  }

}
