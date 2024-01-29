package utils;

import java.util.HashMap;
import java.util.Map;

import controller.ControllerInterface;

public class ControllerRegistry {
  private Map<ControllerName, ControllerInterface> controllerMap = new HashMap<>();

  public ControllerRegistry() {
  }

  public void registerController(ControllerName controllerName, ControllerInterface controller) {
    controllerMap.put(controllerName, controller);
  }

  public ControllerInterface getController(ControllerName controllerName) {
    return controllerMap.get(controllerName);
  }
}
