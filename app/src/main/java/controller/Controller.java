package controller;

import utils.State;
import view.View;

public abstract class Controller implements ControllerInterface {
  private View view;
  private State state;

  public Controller(View view, State state) {
    this.view = view;
    this.state = state;
  }

  @Override
  public void run() {
    view.display();

    State newState = getNewState();

    while (newState == State.INVALID) {
      System.out.println("Invalid choice. Please try again.");
      newState = getNewState();
    }

    Router.pushState(newState);
  }

  @Override
  public State getControllerState() {
    return state;
  }

  protected abstract State getNewState();

}
