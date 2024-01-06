package controller;

import java.util.Arrays;
import java.util.List;

import model.MenuOption;
import view.MenuView;

public class MenuController {
  private List<MenuOption> options = Arrays.asList(
      new MenuOption("Start New Game"),
      new MenuOption("Load Game"),
      new MenuOption("View Scores"),
      new MenuOption("Exit"));

  private MenuView view;

  public MenuController(MenuView view) {
    this.view = view;
  }

  public void displayMenu() {
    view.display(options);
  }
}
