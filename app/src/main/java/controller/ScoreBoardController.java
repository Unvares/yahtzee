package controller;

import utils.InputHandler;

import java.util.ArrayList;
import java.util.List;

import utils.ControllerName;
import view.ScoreBoardView;

public class ScoreBoardController extends Controller {
  public ScoreBoardController() {
    super(new ScoreBoardView(), "menu");
    setState("menu");
  }

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

  private List<String> getOptionsList() {
    List<String> optionsList = new ArrayList<>();

    optionsList.add("1. Simplified");
    optionsList.add("2. Detailed");
    optionsList.add("3. Return to Main Menu");

    return optionsList;
  }
}
