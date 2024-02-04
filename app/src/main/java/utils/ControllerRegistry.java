package utils;

import controller.ControllerInterface;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a registry for all controllers in the application.
 * It provides methods to register and retrieve controllers.
 */
public class ControllerRegistry {
  /**
   * A map to store all registered controllers.
   */
  private static Map<ControllerName, ControllerInterface> controllerMap = new HashMap<>();

  /**
   * Default constructor for ControllerRegistry.
   */
  public ControllerRegistry() {
  }

  /**
   * This method registers a controller with a specific name.
   *
   * @param controllerName The name of the controller.
   * @param controller     The controller to be registered.
   */
  public static void registerController(ControllerName controllerName, ControllerInterface controller) {
    controllerMap.put(controllerName, controller);
  }

  /**
   * This method retrieves a controller by its name.
   *
   * @param controllerName The name of the controller to be retrieved.
   * @return The controller associated with the given name.
   */
  public static ControllerInterface getController(ControllerName controllerName) {
    return controllerMap.get(controllerName);
  }
}
