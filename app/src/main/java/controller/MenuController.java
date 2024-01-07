package controller;

import model.State;
import view.MenuView;

public class MenuController implements Controller {
  private MenuView view = new MenuView();

  public MenuController() {

  }

  public State run() {
    this.view.display();

    State newState = getMenuChoice();

    while (newState == State.INVALID) {
      System.out.println("Invalid choice. Please try again.");
      newState = getMenuChoice();
    }

    return newState;

  }

  public State getMenuChoice() {
    int choice = InputHandlerImpl.getInstance().getIntInput();
    switch (choice) {
      case 1:
        return State.START_GAME;
      case 2:
        return State.LOAD_GAME;
      case 3:
        return State.VIEW_SCORES;
      case 4:
        return State.EXIT;
      default:
        return State.INVALID;
    }
  }

}
