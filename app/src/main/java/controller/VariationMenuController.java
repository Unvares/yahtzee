package controller;

import java.util.ArrayList;
import java.util.List;

import model.AppData;
import utils.ControllerName;
import utils.InputHandler;
import utils.Variation;
import view.MenuView;

public class VariationMenuController extends Controller {
  private AppData appData;

  public VariationMenuController(AppData appData) {
    super(new MenuView());
    this.appData = appData;
  }

  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
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

  protected List<String> getOptionsList() {
    List<String> optionsList = new ArrayList<>();

    optionsList.add("1. Default version (5 dices)");
    optionsList.add("2. Maxi Yahtzee (6 dices)");

    return optionsList;
  }

}
