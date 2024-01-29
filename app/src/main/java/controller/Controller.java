package controller;

import utils.InputHandler;
import utils.ControllerName;
import view.View;

public abstract class Controller implements ControllerInterface {
  private View view;
  private InputHandler inputHandler = InputHandler.getInstance();
  private String state;

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

  @Override
  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String getState() {
    return state;
  }

  protected abstract ControllerName getNewController(InputHandler inputHandler);

}
