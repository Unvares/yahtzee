package model;

import java.util.ArrayList;
import java.util.List;

public class Players {
  private List<Player> players = new ArrayList<>();;

  public Players() {
  }

  public void addPlayer(String name, boolean isHuman) {
    players.add(new Player(name, isHuman));
  }

  public void removePlayer(int index) {
    players.remove(index);
  }

  public List<Player> getPlayers() {
    return this.players;
  }
}
