package controller;

import utils.InputHandler;
import utils.State;
import view.View;

public abstract class Controller implements ControllerInterface {
  private View view;
  private State state;
  private InputHandler inputHandler = InputHandler.getInstance();

  public Controller(View view, State state) {
    this.view = view;
    this.state = state;
  }

  @Override
  public void run() {
    view.display();

    State newState = getNewState(inputHandler);

    while (newState == State.INVALID) {
      System.out.println("Invalid choice. Please try again.");
      newState = getNewState(inputHandler);
    }

    Router.pushState(newState);
  }

  @Override
  public State getControllerState() {
    return state;
  }

  protected abstract State getNewState(InputHandler inputHandler);

}
