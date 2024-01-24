package controller;

import model.GameData;
import model.ScoreCardEntry;
import utils.InputHandler;
import utils.InputHandlerImpl;
import utils.State;
import view.ScoreCardView;
import view.View;

public class ScoreCardController implements Controller {
  private State state = State.SCORE;
  private View view;
  private GameData gameData;

  public ScoreCardController(GameData gameData) {
    this.gameData = gameData;
    view = new ScoreCardView(gameData);
  }

  @Override
  public void run() {
    view.display();

    State newState = getChoice();

    while (newState == State.INVALID) {
      System.out.println("Invalid choice. Please try again.");
      newState = getChoice();
    }

    Router.pushState(newState);
  }

  @Override
  public State getControllerState() {
    return this.state;
  }

  public State getChoice() {
    InputHandler inputHandler = InputHandlerImpl.getInstance();
    String choice = inputHandler.getStringInput("Enter name: ").toLowerCase();
    try {
      ScoreCardEntry entry = gameData.getCurrentPlayer().getScoreCard().getScoreCardEntry(choice);
      if (entry.isCompleted()) {
        return State.INVALID;
      }
      entry.setScore(choice, gameData.getCurrentDiceValues());
      gameData.nextTurn();
    } catch (Exception e) {
      return State.INVALID;
    }
    return State.PLAY;
  }

}
