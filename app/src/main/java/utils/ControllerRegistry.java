package utils;

import java.util.HashMap;
import java.util.Map;

import controller.ControllerInterface;

public class ControllerRegistry {
  private Map<ControllerName, ControllerInterface> controllerMap = new HashMap<>();

  public ControllerRegistry() {
  }

  public void registerController(ControllerName state, ControllerInterface controller) {
    controllerMap.put(state, controller);
  }

  public ControllerInterface getController(ControllerName state) {
    return controllerMap.get(state);
  }
}
