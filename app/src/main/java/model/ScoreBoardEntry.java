package model;

import java.util.ArrayList;
import java.util.List;

import utils.Variation;

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

  public String toCSV() {
    StringBuilder csvBuilder = new StringBuilder();
    for (Player player : players) {
      csvBuilder.append(player.toCSV() + ";");
    }
    return csvBuilder.toString();
  }

  public static ScoreBoardEntry fromCSV(String csv, Variation variation) {
    String[] parts = csv.split(";");
    List<Player> players = new ArrayList<>();
    for (int i = 0; i < parts.length; i++) {
      players.add(Player.fromCSV(parts[i], variation));
    }
    return new ScoreBoardEntry(players);
  }

}
