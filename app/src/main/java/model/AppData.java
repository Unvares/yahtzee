package model;

import java.io.Serializable;
import utils.Variation;

/**
 * This class represents the application data for the Yahtzee game.
 */
public class AppData implements Serializable {
  private static AppData instance = null;
  private Variation variation = Variation.DEFAULT;
  private GameData gameData = new GameData();
  private ScoreBoard scoreBoard = new ScoreBoard();

  /**
   * Private constructor for AppData.
   */
  private AppData() {
  }

  /**
   * Returns the singleton instance of AppData.
   *
   * @return The singleton instance of AppData.
   */
  public static AppData getInstance() {
    if (instance == null) {
      instance = new AppData();
    }
    return instance;
  }

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
    this.variation = variation;
    gameData.setVariation(variation);
  }

  /**
   * Returns the game data.
   *
   * @return The game data.
   */
  public GameData getGameData() {
    return gameData;
  }

  /**
   * Initializes the scoreboard with the given game variation.
   *
   * @param variation The game variation.
   */
  public void initScoreBoard(Variation variation) {
    scoreBoard = new ScoreBoard(variation);
  }

  /**
   * Returns the scoreboard.
   *
   * @return The scoreboard.
   */
  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }
}
