package controller;

import utils.State;

public interface ControllerInterface {
  State getControllerState();

  void run();
}
