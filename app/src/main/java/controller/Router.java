package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.ControllerRegistry;
import utils.InputHandler;
import utils.ControllerName;

public class Router {
  private static List<ControllerName> controllersQueue = new ArrayList<>(Arrays.asList(ControllerName.VARIATION_MENU));

  public Router() {
    ControllerRegistry.registerController(ControllerName.CREATE_GAME, new CreateGameController());
    ControllerRegistry.registerController(ControllerName.GAME, new GameController());
    ControllerRegistry.registerController(ControllerName.GAME_OVER, new GameOverController());
    ControllerRegistry.registerController(ControllerName.MAIN_MENU, new MainMenuController());
    ControllerRegistry.registerController(ControllerName.SCOREBOARD, new ScoreBoardController());
    ControllerRegistry.registerController(ControllerName.SCORECARD, new ScoreCardController());
    ControllerRegistry.registerController(ControllerName.VARIATION_MENU, new VariationMenuController());
  }

  public void run() {
    while (!controllersQueue.isEmpty()) {
      ControllerName controllerName = Router.popController();

      if (controllerName == ControllerName.EXIT) {
        break;
      }

      ControllerInterface controller = ControllerRegistry.getController(controllerName);
      if (controller != null) {
        controller.run();
      } else {
        System.out.println("No controller found");
        System.out.println("Exiting...");
        break;
      }
    }

    System.out.println("See you next time!");
    InputHandler.getInstance().closeScanner();

  }

  static protected void pushController(ControllerName controller) {
    controllersQueue.add(controller);
  }

  static private ControllerName popController() {
    return controllersQueue.remove(controllersQueue.size() - 1);
  }
}
