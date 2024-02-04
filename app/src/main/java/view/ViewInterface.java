package view;

/**
 * This interface represents a generic view in the application.
 */
public interface ViewInterface {

  /**
   * This method is used to display the view.
   */
  void display();

  /**
   * This method is used to get the current state of the view.
   *
   * @return The current state of the view.
   */
  public String getState();

  /**
   * This method is used to set the state of the view.
   *
   * @param state The new state of the view.
   */
  public void setState(String state);
}
