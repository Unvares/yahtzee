package view;

public abstract class View implements ViewInterface {
  protected void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
