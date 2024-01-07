package controller;

import java.util.HashMap;
import java.util.Map;

import model.State;

public class ControllerRegistry {
  private Map<State, Controller> controllerMap = new HashMap<>();

  public ControllerRegistry() {
  }

  public void registerController(State state, Controller controller) {
    controllerMap.put(state, controller);
  }

  public Controller getController(State state) {
    return controllerMap.get(state);
  }
}
