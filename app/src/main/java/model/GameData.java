package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GameData {
  private final int MIN_PLAYERS = 2;
  private final int MAX_PLAYERS = 10;
  private final int MAX_ROLLS = 3;
  private final int MAX_DICES = 5;

  private int currentPlayerIndex = 0;
  private int rollCounter = 0;
  private List<Integer> currentDiceValues = new ArrayList<>();
  private List<Player> players = new ArrayList<>();

  private List<ScoreBoardEntry> scoreBoard = new ArrayList<>();
  private File scoreFile;

  public GameData() {
    scoreFile = new File("scores.txt");
    if (scoreFile.exists()) {
      try (Scanner scanner = new Scanner(scoreFile)) {
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          ScoreBoardEntry scoreBoardEntry = parseScoreBoardEntry(line);
          scoreBoard.add(scoreBoardEntry);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private ScoreBoardEntry parseScoreBoardEntry(String line) {
    ScoreBoardEntry scoreBoardEntry = new ScoreBoardEntry();
    String[] parts = line.split(";");

    if (parts.length < 2 || parts.length > 10) {
      return null;
    }

    for (String part : parts) {
      String[] playerData = part.split(",");

      if (playerData.length != 3) {
        continue;
      }

      String name = playerData[0].trim();
      boolean isHuman = Boolean.parseBoolean(playerData[1].trim());
      int score = Integer.parseInt(playerData[2].trim());

      Player player = new Player(name, isHuman, score);
      scoreBoardEntry.addPlayer(player);
    }
    return scoreBoardEntry;

  }

  public void reset() {
    currentPlayerIndex = 0;
    rollCounter = 0;
    currentDiceValues = new ArrayList<>();
    players = new ArrayList<>();
  }

  public boolean nextTurn() {
    if (players.stream().allMatch(Player::isFinished)) {
      return true;
    }

    currentDiceValues.clear();
    resetRollCounter();

    do {
      advanceCurrentPlayerIndex();
    } while (getCurrentPlayer().isFinished());
    return false;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void addPlayer(Player player) {
    players.add(player);
  }

  public void removePlayer(int index) {
    players.remove(index);
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  public void advanceCurrentPlayerIndex() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }

  public Player getCurrentPlayer() {
    return players.get(currentPlayerIndex);
  }

  public int getRollCounter() {
    return rollCounter;
  }

  public void advanceRollCounter() {
    rollCounter++;
  }

  public void resetRollCounter() {
    rollCounter = 0;
  }

  public List<Integer> getCurrentDiceValues() {
    return currentDiceValues;
  }

  public void addDiceValue(int diceValue) {
    currentDiceValues.add(diceValue);
  }

  public void removeDiceValues(Set<Integer> indicesToRemove) {
    List<Integer> newDiceValues = new ArrayList<>();
    for (int i = 0; i < currentDiceValues.size(); i++) {
      if (!indicesToRemove.contains(i)) {
        newDiceValues.add(currentDiceValues.get(i));
      }
    }

    currentDiceValues = newDiceValues;
  }

  public void clearDiceValues() {
    currentDiceValues = new ArrayList<>();
  }

  public int getDiceCount() {
    return MAX_DICES - currentDiceValues.size();
  }

  public boolean canRoll() {
    return rollCounter < MAX_ROLLS;
  }

  public boolean canDeleteDices() {
    return rollCounter > 0 && rollCounter < 3 && currentDiceValues.size() > 0;
  }

  public boolean canEndTurn() {
    return rollCounter > 0 && currentDiceValues.size() == 5;
  }

  public int getMinPlayers() {
    return MIN_PLAYERS;
  }

  public int getMaxPlayers() {
    return MAX_PLAYERS;
  }

  public int getMaxDices() {
    return MAX_DICES;
  }

  public int getMaxRolls() {
    return MAX_ROLLS;
  }

  public List<ScoreBoardEntry> getScoreBoard() {
    return scoreBoard;
  }

  public void addScoreBoardEntry(ScoreBoardEntry entry) {
    scoreBoard.add(entry);
    try (FileWriter writer = new FileWriter(scoreFile, true)) {
      writer.write(entry.toString() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
