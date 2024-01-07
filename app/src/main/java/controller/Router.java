package controller;

import utils.ControllerRegistry;
import utils.InputHandlerImpl;
import utils.State;

public class Router {
  private ControllerRegistry registry = new ControllerRegistry();

  private State currentState = State.MENU;

  public Router() {
    registry.registerController(State.MENU, new MenuController());
  }

  public void run() {
    while (currentState != State.EXIT) {
      Controller controller = registry.getController(currentState);
      if (controller != null) {
        currentState = controller.run();
      } else {
        System.out.println("No controller found for state " + currentState);
        System.out.println("Exiting...");
        currentState = State.EXIT;
      }
    }

    InputHandlerImpl.getInstance().closeScanner();

  }
}
