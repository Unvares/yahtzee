package model;

import utils.Variation;

public class AppData {
  private static AppData instance = null;
  private Variation variation = Variation.DEFAULT;
  private GameData gameData = new GameData();
  private ScoreBoard scoreBoard = new ScoreBoard();

  private AppData() {
  }

  public static AppData getInstance() {
    if (instance == null) {
      instance = new AppData();
    }
    return instance;
  }

  public Variation getVariation() {
    return variation;
  }

  public void setVariation(Variation variation) {
    this.variation = variation;
    gameData.setVariation(variation);
  }

  public GameData getGameData() {
    return gameData;
  }

  public void initScoreBoard(Variation variation) {
    scoreBoard = new ScoreBoard(variation);
  }

  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }
}
