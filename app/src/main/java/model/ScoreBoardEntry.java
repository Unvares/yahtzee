package model;

import java.util.List;

public class ScoreBoardEntry {
  private List<Player> players;

  public ScoreBoardEntry(List<Player> players) {
    this.players = players;
  }

  public List<Player> getPlayers() {
    return players;
  }

}
