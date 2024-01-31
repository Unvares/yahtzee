package controller;

import view.ViewInterface;

public interface ControllerInterface {
  void run();

  public void setState(String state);

  public String getState();

  public ViewInterface getView();
}
