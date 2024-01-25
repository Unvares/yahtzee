package model;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardEntry {
  private List<Player> players;

  public ScoreBoardEntry() {
    players = new ArrayList<>();
  }

  public ScoreBoardEntry(List<Player> players) {
    this.players = players;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void addPlayer(Player player) {
    players.add(player);
  }

}
