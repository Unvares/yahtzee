package utils;

/**
 * This enum represents the different types of controllers in the application.
 */
public enum ControllerName {
  /**
   * Represents the controller responsible for exiting the application.
   */
  EXIT,

  /**
   * Represents the controller responsible for creating a new game.
   */
  CREATE_GAME,

  /**
   * Represents the controller responsible for handling the end of a game.
   */
  GAME_OVER,

  /**
   * Represents the controller responsible for managing the game.
   */
  GAME,

  /**
   * Represents an invalid state of any controller.
   */
  INVALID,

  /**
   * Represents the controller responsible for managing the main menu.
   */
  MAIN_MENU,

  /**
   * Represents the controller responsible for managing the scoreboard.
   */
  SCOREBOARD,

  /**
   * Represents the controller responsible for managing the scorecard.
   */
  SCORECARD,

  /**
   * Represents the controller responsible for managing the game variation menu.
   */
  VARIATION_MENU,
}
