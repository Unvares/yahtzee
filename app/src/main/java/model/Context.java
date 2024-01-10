package model;

public class Context {

  private Players players = new Players();

  public Context() {
  }

  public Players getPlayers() {
    return players;
  }

  public void setPlayers(Players players) {
    this.players = players;
  }

}
