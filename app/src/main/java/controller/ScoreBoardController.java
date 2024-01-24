package controller;

import model.GameData;
import utils.InputHandler;
import utils.InputHandlerImpl;
import utils.State;
import view.ScoreBoardView;
import view.View;

public class ScoreBoardController implements Controller {
  private State state = State.SCORE_BOARD;
  private View view;

  public ScoreBoardController(GameData gameData) {
    this.view = new ScoreBoardView(gameData);
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
    inputHandler.getIntInput("Press any button to return to the main menu: ");
    return State.MENU;
  }
}
