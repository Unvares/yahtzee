package controller;

import utils.InputHandler;
import utils.ControllerName;
import view.ViewInterface;

/**
 * Abstract class for controllers in the application.
 */
public abstract class Controller implements ControllerInterface {
  private ViewInterface view;
  private InputHandler inputHandler = InputHandler.getInstance();
  private String state;

  /**
   * Constructor for Controller with a view.
   * 
   * @param view The view associated with this controller.
   */
  public Controller(ViewInterface view) {
    this.view = view;
  }

  /**
   * Constructor for Controller with a view and a state.
   * 
   * @param view  The view associated with this controller.
   * @param state The state of this controller.
   */
  public Controller(ViewInterface view, String state) {
    this.view = view;
    view.setState(state);
  }

  /**
   * Runs the controller, displays the view and handles input.
   */
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

  /**
   * Sets the state of the controller.
   * 
   * @param state The state to set.
   */
  @Override
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets the state of the controller.
   * 
   * @return The state of the controller.
   */
  @Override
  public String getState() {
    return state;
  }

  /**
   * Gets the view associated with the controller.
   * 
   * @return The view associated with the controller.
   */
  @Override
  public ViewInterface getView() {
    return view;
  }

  /**
   * Abstract method to get the new controller based on input.
   * 
   * @param inputHandler The input handler to handle user input.
   * @return The new controller based on user input.
   */
  protected abstract ControllerName getNewController(InputHandler inputHandler);

}
