package controller;

import view.MenuView;

/**
 * Responsible for starting the application.
 */
public class App {
  /**
   * Application starting point.
   * 
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    MenuView menuView = new MenuView();
    MenuController menuController = new MenuController(menuView);

    menuController.displayMenu();
  }
}
