package utils;

import java.util.HashMap;
import java.util.Map;

import controller.ControllerInterface;

public class ControllerRegistry {
  private Map<State, ControllerInterface> controllerMap = new HashMap<>();

  public ControllerRegistry() {
  }

  public void registerController(State state, ControllerInterface controller) {
    controllerMap.put(state, controller);
  }

  public ControllerInterface getController(State state) {
    return controllerMap.get(state);
  }
}
