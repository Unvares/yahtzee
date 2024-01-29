package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.GameData;
import utils.ControllerRegistry;
import utils.InputHandler;
import utils.ControllerName;

public class Router {
  private ControllerRegistry registry = new ControllerRegistry();

  private static List<ControllerName> controllersQueue = new ArrayList<>(Arrays.asList(ControllerName.MENU));

  private GameData gameData = new GameData();

  public Router() {
    registry.registerController(ControllerName.MENU, new MainMenuController(gameData));
    registry.registerController(ControllerName.GAME_CREATE, new CreateGameController(gameData));
    registry.registerController(ControllerName.GAME_PLAY, new GameController(gameData));
    registry.registerController(ControllerName.GAME_SCORE_REGISTER, new ScoreCardRegisterController(gameData));
    registry.registerController(ControllerName.GAME_SCORE_VIEW, new ScoreCardViewController(gameData));
    registry.registerController(ControllerName.GAME_OVER, new GameOverController(gameData));
    registry.registerController(ControllerName.SCORE_BOARD, new ScoreBoardController(gameData));
  }

  public void run() {
    while (!controllersQueue.isEmpty()) {
      cleanScreen();

      ControllerName controllerName = Router.popController();

      if (controllerName == ControllerName.EXIT) {
        break;
      }

      ControllerInterface controller = registry.getController(controllerName);
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

  private void cleanScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
