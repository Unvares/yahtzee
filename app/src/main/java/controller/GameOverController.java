package controller;

import model.GameData;
import model.ScoreBoardEntry;
import utils.InputHandler;
import utils.InputHandlerImpl;
import utils.State;
import view.GameOverView;
import view.View;

public class GameOverController implements Controller {
  private State state = State.GAME_OVER;
  private GameData gameData;
  private View view;

  public GameOverController(GameData gameData) {
    this.gameData = gameData;
    view = new GameOverView(gameData);
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
    gameData.addScoreBoardEntry(new ScoreBoardEntry(gameData.getPlayers()));
    return State.MENU;
  }
}
