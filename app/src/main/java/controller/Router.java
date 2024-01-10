package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Context;
import utils.ControllerRegistry;
import utils.InputHandlerImpl;
import utils.State;

public class Router {
  private ControllerRegistry registry = new ControllerRegistry();

  private static List<State> statesList = new ArrayList<>(Arrays.asList(State.MENU));

  Context context = new Context();

  public Router() {
    registry.registerController(State.MENU, new MenuController(context));
    registry.registerController(State.CREATE_GAME, new CreateGameController(context));
    registry.registerController(State.PLAY, new GameController(context));
  }

  public void run() {
    while (!statesList.isEmpty()) {
      cleanScreen();

      State state = Router.popState();
      if (state == State.EXIT) {
        break;
      }

      Controller controller = registry.getController(state);
      if (controller != null) {
        controller.run();
      } else {
        System.out.println("No controller found");
        System.out.println("Exiting...");
        break;
      }
    }

    System.out.println("See you next time!");
    InputHandlerImpl.getInstance().closeScanner();

  }

  static protected void pushState(State state) {
    statesList.add(state);
  }

  static private State popState() {
    return statesList.remove(statesList.size() - 1);
  }

  private void cleanScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
