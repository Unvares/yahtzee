package controller;

import java.util.ArrayList;
import java.util.List;
import model.AppData;
import utils.ControllerName;
import utils.InputHandler;
import utils.Variation;
import view.MenuView;

/**
 * Controller for the Variation Menu.
 */
public class VariationMenuController extends Controller {

  /**
   * Constructor for VariationMenuController.
   */
  public VariationMenuController() {
    super(new MenuView());
  }

  /**
   * Gets the new controller based on user input.
   *
   * @param inputHandler The input handler for user input.
   * @return The new controller.
   */
  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    AppData appData = AppData.getInstance();
    List<String> optionsList = getOptionsList();
    int choice = inputHandler.getIntInput(optionsList, "Choose a variation: ");
    switch (choice) {
      case 1:
        appData.setVariation(Variation.DEFAULT);
        appData.initScoreBoard(Variation.DEFAULT);
        break;
      case 2:
        appData.setVariation(Variation.MAXI);
        appData.initScoreBoard(Variation.MAXI);
        break;
      default:
        return ControllerName.INVALID;
    }
    return ControllerName.MAIN_MENU;
  }

  /**
   * Gets the list of options for the variation menu.
   *
   * @return The list of options.
   */
  protected List<String> getOptionsList() {
    List<String> optionsList = new ArrayList<>();

    optionsList.add("1. Default version (5 dices)");
    optionsList.add("2. Maxi Yahtzee (6 dices)");

    return optionsList;
  }

}
