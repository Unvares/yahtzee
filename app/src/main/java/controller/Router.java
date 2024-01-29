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

  private static List<ControllerName> statesList = new ArrayList<>(Arrays.asList(ControllerName.MENU));

  private GameData gameData = new GameData();

  public Router() {
    registry.registerController(ControllerName.MENU, new MenuController(gameData));
    registry.registerController(ControllerName.GAME_CREATE, new CreateGameController(gameData));
    registry.registerController(ControllerName.GAME_PLAY, new GameController(gameData));
    registry.registerController(ControllerName.GAME_SCORE_REGISTER, new ScoreCardRegisterController(gameData));
    registry.registerController(ControllerName.GAME_SCORE_VIEW, new ScoreCardViewController(gameData));
    registry.registerController(ControllerName.GAME_OVER, new GameOverController(gameData));
    registry.registerController(ControllerName.SCORE_BOARD, new ScoreBoardController(gameData));
  }

  public void run() {
    while (!statesList.isEmpty()) {
      cleanScreen();

      ControllerName state = Router.popState();

      if (state == ControllerName.EXIT) {
        break;
      }

      ControllerInterface controller = registry.getController(state);
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

  static protected void pushState(ControllerName state) {
    statesList.add(state);
  }

  static private ControllerName popState() {
    return statesList.remove(statesList.size() - 1);
  }

  private void cleanScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
