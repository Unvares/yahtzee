package view;

public abstract class View implements ViewInterface {
  private String state;

  protected void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  @Override
  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String getState() {
    return state;
  }
}
