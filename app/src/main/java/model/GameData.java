package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import utils.DeepCopyUtil;
import utils.Variation;

/**
 * This class represents the game data for a Yahtzee game.
 */
public class GameData {
  private Variation variation = Variation.DEFAULT;
  private static final int minPlayers = 2;
  private static final int maxPlayers = 5;
  private static final int maxRolls = 3;
  private int maxDices = 5;

  /**
   * Returns the current game variation.
   *
   * @return The current game variation.
   */
  public Variation getVariation() {
    return variation;
  }

  /**
   * Sets the game variation.
   *
   * @param variation The game variation to be set.
   */
  public void setVariation(Variation variation) {
    switch (variation) {
      case DEFAULT:
        maxDices = 5;
        break;
      case MAXI:
        maxDices = 6;
        break;
      default:
        throw new IllegalArgumentException("Invalid variation");
    }
    this.variation = variation;
  }

  /**
   * Returns the minimum number of players.
   *
   * @return The minimum number of players.
   */
  public int getMinPlayers() {
    return minPlayers;
  }

  /**
   * Returns the maximum number of players.
   *
   * @return The maximum number of players.
   */
  public int getMaxPlayers() {
    return maxPlayers;
  }

  /**
   * Returns the maximum number of dices.
   *
   * @return The maximum number of dices.
   */
  public int getMaxDices() {
    return maxDices;
  }

  /**
   * Returns the maximum number of rolls.
   *
   * @return The maximum number of rolls.
   */
  public int getMaxRolls() {
    return maxRolls;
  }

  private List<Player> players = new ArrayList<>();
  private int currentPlayerIndex = 0;
  private int rollCounter = 0;
  private List<Integer> currentDiceValues = new ArrayList<>();

  /**
   * Resets the game data.
   */
  public void reset() {
    currentPlayerIndex = 0;
    rollCounter = 0;
    currentDiceValues = new ArrayList<>();
    players = new ArrayList<>();
  }

  /**
   * Advances to the next turn.
   */
  public void nextTurn() {
    currentDiceValues.clear();
    resetRollCounter();

    do {
      advanceCurrentPlayerIndex();
    } while (getCurrentPlayer().hasFilledScoreCard() && !hasGameEnded());
  }

  /**
   * Checks if the game has ended.
   *
   * @return True if the game has ended, false otherwise.
   */
  public boolean hasGameEnded() {
    if (players.stream().allMatch(Player::hasFilledScoreCard)) {
      return true;
    }
    return false;
  }

  /**
   * Returns a deep copy of the list of players.
   *
   * @return A deep copy of the list of players.
   */
  public List<Player> getPlayers() {
    List<Player> newPlayersList = new ArrayList<>();
    for (Player player : players) {
      newPlayersList.add(DeepCopyUtil.deepCopy(player));
    }
    return newPlayersList;
  }

  /**
   * Adds a player to the game.
   *
   * @param player The player to be added.
   */
  public void addPlayer(Player player) {
    players.add(player);
  }

  /**
   * Removes a player from the game.
   *
   * @param index The index of the player to be removed.
   */
  public void removePlayer(int index) {
    players.remove(index);
  }

  /**
   * Returns the index of the current player.
   *
   * @return The index of the current player.
   */
  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  /**
   * Advances the index of the current player.
   */
  public void advanceCurrentPlayerIndex() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }

  /**
   * Returns the current player.
   *
   * @return The current player.
   */
  public Player getCurrentPlayer() {
    return players.get(currentPlayerIndex);
  }

  /**
   * Returns the roll counter.
   *
   * @return The roll counter.
   */
  public int getRollCounter() {
    return rollCounter;
  }

  /**
   * Advances the roll counter.
   */
  public void advanceRollCounter() {
    rollCounter++;
  }

  /**
   * Resets the roll counter.
   */
  public void resetRollCounter() {
    rollCounter = 0;
  }

  /**
   * Returns the current dice values.
   *
   * @return The current dice values.
   */
  public List<Integer> getCurrentDiceValues() {
    return new ArrayList<>(currentDiceValues);
  }

  /**
   * Adds a dice value.
   *
   * @param diceValue The dice value to be added.
   */
  public void addDiceValue(int diceValue) {
    currentDiceValues.add(diceValue);
  }

  /**
   * Removes dice values by indices.
   *
   * @param indicesToRemove The indices of the dice values to be removed.
   */
  public void removeDiceValuesByIndices(Set<Integer> indicesToRemove) {
    List<Integer> newDiceValues = new ArrayList<>();
    for (int i = 0; i < currentDiceValues.size(); i++) {
      if (!indicesToRemove.contains(i)) {
        newDiceValues.add(currentDiceValues.get(i));
      }
    }

    currentDiceValues = newDiceValues;
  }

  /**
   * Clears the dice values.
   */
  public void clearDiceValues() {
    currentDiceValues = new ArrayList<>();
  }

  /**
   * Returns the number of dices to roll.
   *
   * @return The number of dices to roll.
   */
  public int getNumberOfDicesToRoll() {
    return maxDices - currentDiceValues.size();
  }

  /**
   * Checks if the player can roll.
   *
   * @return True if the player can roll, false otherwise.
   */
  public boolean canRoll() {
    return rollCounter < maxRolls && getNumberOfDicesToRoll() > 0;
  }

  /**
   * Checks if the player can delete dices.
   *
   * @return True if the player can delete dices, false otherwise.
   */
  public boolean canDeleteDices() {
    return rollCounter > 0 && rollCounter < 3 && currentDiceValues.size() > 0;
  }

  /**
   * Checks if the player can end the turn.
   *
   * @return True if the player can end the turn, false otherwise.
   */
  public boolean canEndTurn() {
    return rollCounter > 0 && currentDiceValues.size() == 5;
  }

  /**
   * Checks if the game has been saved.
   *
   * @return True if the game has been saved, false otherwise.
   */
  public boolean hasSavedGame() {
    return players.size() >= minPlayers;
  }

}
