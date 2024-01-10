package model;

public class MenuOptions {
  static private String[] options = {
      "Create New Game",
      "Load Game",
      "View Scores",
      "Exit"
  };

  static public String[] getOptions() {
    return options;
  }

}
