package controller;

import model.State;

/**
 * Each controller has a private method indicating its associated state.
 */

public interface Controller {
  State getControllerState();

  State run();
}
