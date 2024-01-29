package controller;

import utils.InputHandler;
import utils.ControllerName;
import view.View;

public abstract class Controller implements ControllerInterface {
  private View view;
  private InputHandler inputHandler = InputHandler.getInstance();

  public Controller(View view) {
    this.view = view;
  }

  @Override
  public void run() {
    view.display();

    ControllerName newState = getNewState(inputHandler);

    while (newState == ControllerName.INVALID) {
      System.out.println("Invalid choice. Please try again.");
      newState = getNewState(inputHandler);
    }

    Router.pushState(newState);
  }

  protected abstract ControllerName getNewState(InputHandler inputHandler);

}
