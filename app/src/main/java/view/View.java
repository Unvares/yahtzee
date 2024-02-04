package view;

/**
 * This abstract class represents a generic view in the application.
 * It implements the ViewInterface and provides basic functionality for all
 * views.
 */
public abstract class View implements ViewInterface {
  /**
   * The state of the view.
   */
  private String state;

  /**
   * This method clears the console screen.
   */
  protected void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * This method sets the state of the view.
   * 
   * @param state The new state of the view.
   */
  @Override
  public void setState(String state) {
    this.state = state;
  }

  /**
   * This method returns the current state of the view.
   * 
   * @return The current state of the view.
   */
  @Override
  public String getState() {
    return state;
  }
}
