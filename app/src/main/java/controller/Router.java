package controller;

import model.State;

public class Router {
  Controller menuController = new MenuController();
  State currentState = State.MENU;

  public Router() {

  }

  public void run() {
    while (currentState != State.EXIT) {
      switch (currentState) {
        case EXIT:
          System.out.println("EXIT case");
          break;
        case INVALID:
          System.out.println("INVALID case");
          break;
        case LOAD_GAME:
          System.out.println("LOAD_GAME case");
          break;
        case MENU:
          currentState = menuController.run();
          break;
        case START_GAME:
          System.out.println("START_GAME case");
          break;
        case VIEW_SCORES:
          System.out.println("VIEW_SCORES case");
          break;
        default:
          System.out.println("DEFAULT case");
          break;
      }
    }
  }
}
