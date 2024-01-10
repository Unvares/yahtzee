package controller;

import java.util.Arrays;
import java.util.List;

import utils.ControllerRegistry;
import utils.InputHandlerImpl;
import utils.State;

public class Router {
  private ControllerRegistry registry = new ControllerRegistry();

  private static List<State> statesList = Arrays.asList(State.MENU);

  public Router() {
    registry.registerController(State.MENU, new MenuController());
    registry.registerController(State.CREATE_GAME, new CreateGameController());
  }

  public void run() {
    while (!statesList.isEmpty()) {
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
}
