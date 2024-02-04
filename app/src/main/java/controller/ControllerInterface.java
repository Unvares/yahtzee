package controller;

import view.ViewInterface;

/**
 * Interface for controllers in the application.
 */
public interface ControllerInterface {

  /**
   * Runs the controller, displays the view and handles input.
   */
  void run();

  /**
   * Sets the state of the controller.
   *
   * @param state The state to set.
   */
  public void setState(String state);

  /**
   * Gets the state of the controller.
   *
   * @return The state of the controller.
   */
  public String getState();

  /**
   * Gets the view associated with the controller.
   *
   * @return The view associated with the controller.
   */
  public ViewInterface getView();
}
