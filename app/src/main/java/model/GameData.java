package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameData {
  private final int MAX_ROLLS = 3;
  private final int MAX_DICES = 5;

  private int currentPlayerIndex = 0;
  private int rollCounter = 0;
  private List<Integer> currentDiceValues = new ArrayList<>();
  private Players players;

  public GameData(Players players) {
    this.players = players;
  }

  public Players getPlayers() {
    return players;
  }

  public void setPlayers(Players players) {
    this.players = players;
  }

  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  public void advanceCurrentPlayerIndex() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.getPlayers().size();
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

  public int getMaxDices() {
    return MAX_DICES;
  }

  public int getMaxRolls() {
    return MAX_ROLLS;
  }

}
