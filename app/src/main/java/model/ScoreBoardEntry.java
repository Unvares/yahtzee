package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import utils.DeepCopyUtil;
import utils.Variation;

/**
 * This class represents an entry in the scoreboard of the Yahtzee game.
 */
public class ScoreBoardEntry implements Serializable {
  private List<Player> players;

  /**
   * Default constructor for ScoreBoardEntry.
   * Initializes an empty list of players.
   */
  public ScoreBoardEntry() {
    players = new ArrayList<>();
  }

  /**
   * Constructor for ScoreBoardEntry.
   * Initializes the list of players with the given list.
   *
   * @param players The list of players to be added to the scoreboard entry.
   */
  public ScoreBoardEntry(List<Player> players) {
    this.players = new ArrayList<>();
    for (Player player : players) {
      this.players.add(DeepCopyUtil.deepCopy(player));
    }
  }

  /**
   * Returns the list of players in this scoreboard entry.
   *
   * @return The list of players.
   */
  public List<Player> getPlayers() {
    List<Player> copy = new ArrayList<>();
    for (Player player : players) {
      copy.add(DeepCopyUtil.deepCopy(player));
    }
    return copy;
  }

  /**
   * Adds a player to the list of players in this scoreboard entry.
   *
   * @param player The player to be added.
   */
  public void addPlayer(Player player) {
    players.add(player);
  }

  /**
   * Converts this scoreboard entry to a CSV string.
   *
   * @return The CSV string representation of this scoreboard entry.
   */
  public String toCsv() {
    StringBuilder csvBuilder = new StringBuilder();
    for (Player player : players) {
      csvBuilder.append(player.toCsv() + ";");
    }
    return csvBuilder.toString();
  }

  /**
   * Creates a ScoreBoardEntry from a CSV string.
   *
   * @param csv       The CSV string to be converted.
   * @param variation The variation of the game.
   * @return The ScoreBoardEntry created from the CSV string.
   */
  public static ScoreBoardEntry fromCsv(String csv, Variation variation) {
    String[] parts = csv.split(";");
    List<Player> players = new ArrayList<>();
    for (int i = 0; i < parts.length; i++) {
      players.add(Player.fromCsv(parts[i], variation));
    }
    return new ScoreBoardEntry(players);
  }

}
