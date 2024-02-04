package controller;

import java.util.ArrayList;
import java.util.List;
import utils.ControllerName;
import utils.InputHandler;
import view.ScoreBoardView;

/**
 * Controller for the ScoreBoard.
 */
public class ScoreBoardController extends Controller {

  /**
   * Constructor for ScoreBoardController.
   */
  public ScoreBoardController() {
    super(new ScoreBoardView(), "menu");
    setState("menu");
  }

  /**
   * Gets the new controller based on user input.
   *
   * @param inputHandler The input handler for user input.
   * @return The new controller.
   */
  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    if (getState().equals("menu")) {
      List<String> optionsList = getOptionsList();
      int choice = inputHandler.getIntInput(optionsList, "Enter your choice: ");
      switch (choice) {
        case 1:
          setState("simplified");
          getView().setState(getState());
          break;
        case 2:
          setState("detailed");
          getView().setState(getState());
          break;
        case 3:
          return ControllerName.MAIN_MENU;
        default:
          return ControllerName.INVALID;
      }
      return ControllerName.SCOREBOARD;
    } else {
      inputHandler.getAnyInput("Press enter to return to the main menu: ");
      setState("menu");
      getView().setState(getState());
      return ControllerName.MAIN_MENU;
    }
  }

  /**
   * Gets the list of options for the scoreboard.
   *
   * @return The list of options.
   */
  private List<String> getOptionsList() {
    List<String> optionsList = new ArrayList<>();

    optionsList.add("1. Simplified");
    optionsList.add("2. Detailed");
    optionsList.add("3. Return to Main Menu");

    return optionsList;
  }
}
