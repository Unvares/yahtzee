package controller;

import utils.InputHandler;
import utils.InputHandlerImpl;
import utils.State;
import view.MenuView;
import view.View;

public class MenuController implements Controller {
  private State state = State.MENU;
  private View view = new MenuView();

  public MenuController() {
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
    int choice = inputHandler.getIntInput("Your choice: ");
    switch (choice) {
      case 1:
        return State.GAME_CREATE;
      case 2:
        return State.GAME_LOAD;
      case 3:
        return State.VIEW_SCORES;
      case 4:
        return State.EXIT;
      default:
        return State.INVALID;
    }
  }

}
