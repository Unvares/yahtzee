package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    if (!scoreFile.exists()) {
      try {
        scoreFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      loadScoreBoard();
    }
  }

  public void loadScoreBoard() {
    try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        ScoreBoardEntry entry = ScoreBoardEntry.fromCSV(line);
        scoreBoard.add(entry);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void reset() {
    currentPlayerIndex = 0;
    rollCounter = 0;
    currentDiceValues = new ArrayList<>();
    players = new ArrayList<>();
  }

  public void nextTurn() {
    currentDiceValues.clear();
    resetRollCounter();

    do {
      advanceCurrentPlayerIndex();
    } while (getCurrentPlayer().hasFilledScoreCard() && !hasGameEnded());
  }

  public boolean hasGameEnded() {
    if (players.stream().allMatch(Player::hasFilledScoreCard)) {
      return true;
    }
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

  public int getNumberOfDicesToRoll() {
    return MAX_DICES - currentDiceValues.size();
  }

  public boolean canRoll() {
    return rollCounter < MAX_ROLLS && getNumberOfDicesToRoll() > 0;
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

  public boolean hasSavedGame() {
    return players.size() >= MIN_PLAYERS;
  }

  public void addScoreBoardEntry(ScoreBoardEntry entry) {
    scoreBoard.add(entry);
    try (FileWriter writer = new FileWriter(scoreFile, true)) {
      writer.write(entry.toCSV() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
