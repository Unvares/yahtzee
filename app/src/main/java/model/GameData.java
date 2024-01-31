package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.ScoreCard.strategies.Player;
import utils.Variation;

public class GameData {
  private Variation variation = Variation.DEFAULT;
  private final int MIN_PLAYERS = 2;
  private final int MAX_PLAYERS = 5;
  private final int MAX_ROLLS = 3;
  private int MAX_DICES = 5;

  public void setVariation(Variation variation) {
    switch (variation) {
      case DEFAULT:
        MAX_DICES = 5;
        break;
      case MAXI:
        MAX_DICES = 6;
        break;
      default:
        throw new IllegalArgumentException("Invalid variation");
    }
    this.variation = variation;
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

  private List<Player> players = new ArrayList<>();
  private int currentPlayerIndex = 0;
  private int rollCounter = 0;
  private List<Integer> currentDiceValues = new ArrayList<>();

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

  public void removeDiceValuesByIndices(Set<Integer> indicesToRemove) {
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

  public boolean hasSavedGame() {
    return players.size() >= MIN_PLAYERS;
  }

}
