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

    ControllerName newController = getNewController(inputHandler);

    while (newController == ControllerName.INVALID) {
      System.out.println("Invalid choice. Please try again.");
      newController = getNewController(inputHandler);
    }

    Router.pushController(newController);
  }

  protected abstract ControllerName getNewController(InputHandler inputHandler);

}
